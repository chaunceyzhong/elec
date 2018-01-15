package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;
import org.springframework.stereotype.Repository;

/**
 * @author chauncey 2017/12/17.
 */
@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao{

}
