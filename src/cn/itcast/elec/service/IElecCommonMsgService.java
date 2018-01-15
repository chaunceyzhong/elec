package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecCommonMsg;

/**
 * @author chauncey 2017/12/17.
 */
public interface IElecCommonMsgService {

    String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecCommonMsgService";

    ElecCommonMsg findElecCommonMsg();

    void saveElecCommonMsg(ElecCommonMsg elecCommonMsg);
}
