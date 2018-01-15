package cn.itcast.elec.service.impl;

import cn.itcast.elec.dao.IElecExportFieldsDao;
import cn.itcast.elec.domain.ElecExportFields;
import cn.itcast.elec.service.IElecExportFieldsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chauncey 2017/12/18.
 */
@Service(IElecExportFieldsService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecExportFieldsServiceImpl implements IElecExportFieldsService{


    @Resource(name=IElecExportFieldsDao.SERVICE_NAME)
    private IElecExportFieldsDao elecExportFieldsDao;

    @Override public ElecExportFields findElecExportFields(String belongTo) {
        ElecExportFields elecExportFields = elecExportFieldsDao.findObjectByID(belongTo);
        return elecExportFields;
    }

    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
    @Override public void saveSetExportExcel(ElecExportFields elecExportFields) {
        elecExportFieldsDao.update(elecExportFields);
    }
}
