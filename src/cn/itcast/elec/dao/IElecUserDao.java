package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecUser;

import java.util.List;

/**
 * @author chauncey 2018/1/9.
 */
public interface IElecUserDao extends ICommonDao<ElecUser> {
    String SERVICE_NAME = "cn.itcast.elec.dao.IElecUserDaoImpl";

    /**
     * 根据用户名查询权限集合
     */
    List<Object> findPopedomByLogonName(String name);
}
