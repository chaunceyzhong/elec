package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import org.springframework.stereotype.Repository;

/**
 * @author chauncey 2018/1/9.
 */
@Repository(IElecUserDao.SERVICE_NAME)
public class IElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao {

}
