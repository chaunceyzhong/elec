package cn.itcast.elec.dao.impl;

import cn.itcast.elec.dao.IElecExportFieldsDao;
import cn.itcast.elec.domain.ElecExportFields;
import org.springframework.stereotype.Repository;

/**
 * @author chauncey 2017/12/18.
 */
@Repository(IElecExportFieldsDao.SERVICE_NAME)
public class ElecExportFieldsDaoImpl extends CommonDaoImpl<ElecExportFields> implements IElecExportFieldsDao{

}
