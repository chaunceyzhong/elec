package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecCommonMsg;

/**
 * @author chauncey 2017/12/17.
 */
public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg> {

    String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecCommonMsgDaoImpl";
}
