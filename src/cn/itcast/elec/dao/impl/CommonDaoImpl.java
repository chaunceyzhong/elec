package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.ICommonDao;
import cn.itcast.elec.util.GenericTypeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	Class entityClass = GenericTypeUtils.getGenericSuperClass(this.getClass());
	
	/**
	 * spring容器中定义
	 * <bean id="commonDao" class="cn.itcast.elec.dao.impl.CommonDaoImpl">
		<property name="sessionFactory" ref="sessionFactory"></property>
	   </bean>
	 */
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	/**保存*/
	@Override public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	/**更新*/
	@Override public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	/**使用主键ID查询对象*/
	@Override public T findObjectByID(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}
	
	/**删除（使用1个主键ID和多个主键ID的数组）*/
	@Override public void deleteBojectByIDs(Serializable... ids) {
		if(ids!=null && ids.length>0){
			for(Serializable id:ids){
				Object entity = this.findObjectByID(id);
				this.getHibernateTemplate().delete(entity);
			}
		}
	}
	
	/**删除（将对象封装成集合，使用集合删除集合中存放的所有对象）*/
	/**用法：将数据查询获取封装到List中，删除全部的list，先查询再删除*/
	@Override public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}
	
	/**指定页面传递的查询条件，查询对应的结果集信息，返回List<ElecText>，不分页*/
	/**
		SELECT * FROM elec_text o WHERE 1=1      #Dao层
		AND o.textName LIKE '%张%'           #Service层
		AND o.textRemark LIKE '%张%'         #Service层
		ORDER BY o.textDate ASC,o.textRemark DESC  #Service层
	 */
	@Override public List<T> findCollectionByConditionNoPage(String condition,
			final Object[] params, Map<String, String> orderby) {
		String hql = "select o from "+entityClass.getSimpleName()+" o where 1=1 ";
		//解析map集合，获取排序的语句
		String orderbyhql = this.orderby(orderby);
		final String finalHql = hql + condition + orderbyhql;
		//方式一
		/*List<T> list = (List<T>) this.getHibernateTemplate().find(finalHql, params);*/
		//方式二，使用hibernate模板提供的回调函数，回调Session
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
		});
		return list;
	}

	/**
	 * 解析map集合，获取排序的语句，ORDER BY o.textDate ASC,o.textRemark DESC
	 */
	private String orderby(Map<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			buffer.append(" order by ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(map.getKey()+" "+map.getValue()+",");
			}
			//删除最后一个逗号
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

}
