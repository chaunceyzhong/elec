package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecRoleDao;
import cn.itcast.elec.domain.ElecRole;


@Repository(IElecRoleDao.SERVICE_NAME)
public class ElecRoleDaoImpl extends CommonDaoImpl<ElecRole> implements IElecRoleDao {

}
