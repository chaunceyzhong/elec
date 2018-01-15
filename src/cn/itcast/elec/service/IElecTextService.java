package cn.itcast.elec.service;

import cn.itcast.elec.domain.ElecText;

import java.util.List;

public interface IElecTextService {

	 String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecTextServiceImpl";
	
	 void save(ElecText elecText);

	 List<ElecText> findCollectionByConditionNoPage(ElecText elecText);
}
