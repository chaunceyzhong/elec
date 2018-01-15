package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.domain.ElecSystemDDL;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chauncey 2017/12/27.
 */
@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL> implements
        IElecSystemDDLDao {

    @Override public List<ElecSystemDDL> findDistinctKeywod() {
        //结果集
        List<ElecSystemDDL> sysList = new ArrayList<>();
        String hql = "SELECT DISTINCT o.keyword FROM ElecSystemDDL o";
        List<Object> list = this.getHibernateTemplate().find(hql);
        //遍历
        if(list!=null && list.size()>0){
            for(Object o:list){
                ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
                elecSystemDDL.setKeyword(o.toString());
                sysList.add(elecSystemDDL);
            }
        }
        //		String hql = "SELECT DISTINCT new cn.itcast.elec.domain.ElecSystemDDL(o.keyword) FROM ElecSystemDDL o";
        //		sysList = this.getHibernateTemplate().find(hql);
        return sysList;
    }

    public String findDdlNameByKeywordAndDdlCode(final String keyword, final String ddlCode) {
        final String hql = "select o.ddlName from ElecSystemDDL o where o.keyword = ? and o.ddlCode = ?";
        List<Object> list =(List<Object>)this.getHibernateTemplate().execute(new HibernateCallback() {

            @Override public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                query.setParameter(0, keyword);
                query.setParameter(1, Integer.parseInt(ddlCode));
                query.setCacheable(true);//开启二级缓存
                return query.list();
            }

        });
        //数据项的值
        String ddlName = "";
        if(list!=null && list.size()>0){
            Object o = list.get(0);
            ddlName = o.toString();
        }
        return ddlName;
    }
}
