package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecSystemDDL;

import java.util.List;

/**
 * @author chauncey 2017/12/27.
 */
public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL> {

     String SERVICE_NAME = " cn.itcast.elec.dao.ElecSystemDDLDaoImpl";

    /**
     * 获取所有不同的数据类型
     * @return
     */
     List<ElecSystemDDL> findDistinctKeywod();

     String findDdlNameByKeywordAndDdlCode(String keyword, String ddlCode);
}
