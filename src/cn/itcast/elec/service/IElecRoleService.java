package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecPopedom;
import cn.itcast.elec.domain.ElecRole;
import cn.itcast.elec.domain.ElecUser;

import java.util.List;

/**
 * @author chauncey 2018/1/15.
 */
public interface IElecRoleService {

    String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecRoleServiceImpl";

    List<ElecRole> findRoleList();

    List<ElecPopedom> findPopedomList();

    List<ElecPopedom> findPopedomListByRoleID(String roleID);

    List<ElecUser> findUserListByRoleID(String roleID);

    void saveRole(ElecPopedom elecPopedom);
}
