package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecSystemDDL;

import java.util.List;

/**
 * @author chauncey 2017/12/27.
 */
public interface IElecSystemDDLService {
    String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecSystemDDLServiceImpl";

    List<ElecSystemDDL> findDistinctKeywod();

    List<ElecSystemDDL> findElecSystemDDLListByKeyword(String keyword);

    void saveElecSystemDDL(ElecSystemDDL elecSystemDDL);

}
