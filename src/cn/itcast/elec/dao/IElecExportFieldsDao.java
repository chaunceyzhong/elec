package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecExportFields;

/**
 * @author chauncey 2017/12/18.
 */
public interface IElecExportFieldsDao extends ICommonDao<ElecExportFields> {
    String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecExportFieldsDaoImpl";
}
