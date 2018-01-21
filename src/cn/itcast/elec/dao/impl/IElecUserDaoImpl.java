package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author chauncey 2018/1/9.
 */
@Repository(IElecUserDao.SERVICE_NAME)
public class IElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao {

    /**
     * 根据用户名查询权限集合
     *
     * @param name
     */
    @Override public List<Object> findPopedomByLogonName(String name) {
        final String sql = "SELECT a.mid FROM elec_role_popedom a " +
                " INNER JOIN elec_user_role b ON a.roleID = b.roleID " +
                " INNER JOIN elec_user c ON b.userID = c.userID AND c.logonName=?" +
                " WHERE c.isDuty = '1'";
        List<Object> list = (List<Object>)this.getHibernateTemplate().execute(new HibernateCallback() {

            @Override public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                query.setParameter(0, name);
                return query.list();
            }

        });
        return list;

    }
}
