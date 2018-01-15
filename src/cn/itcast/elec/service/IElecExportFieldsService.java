package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecExportFields;

/**
 * @author chauncey 2017/12/18.
 */
public interface IElecExportFieldsService {

        String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecExportFieldsServiceImpl";

        ElecExportFields findElecExportFields(String belongTo);

        void saveSetExportExcel(ElecExportFields elecExportFields);
}
