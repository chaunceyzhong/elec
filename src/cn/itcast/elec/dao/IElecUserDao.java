package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecUser;

/**
 * @author chauncey 2018/1/9.
 */
public interface IElecUserDao extends ICommonDao<ElecUser> {
    String SERVICE_NAME = "cn.itcast.elec.dao.IElecUserDaoImpl";
}
