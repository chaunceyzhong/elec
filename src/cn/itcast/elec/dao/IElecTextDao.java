package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecText;

public interface IElecTextDao extends ICommonDao<ElecText> {

	String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecTextDaoImpl";
	
}
