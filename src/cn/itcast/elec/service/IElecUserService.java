package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecUser;

import java.util.List;

/**
 * @author chauncey 2018/1/9.
 */
public interface IElecUserService {

    String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecUserServiceImpl";

    List<ElecUser> findUserListByCondition(ElecUser elecUser);

    String checkUser(String logonName);

    void saveUser(ElecUser elecUser);

    ElecUser findElecUserByID(ElecUser elecUser);

    void deleteUserByIds(ElecUser elecUser);

    ElecUser findElecUserByLogonName(String logonName);

    /**
     * 根据用户名返回用户权限
     * @param name
     * @return
     */
    String findPopedomByLogonName(String name);
}
