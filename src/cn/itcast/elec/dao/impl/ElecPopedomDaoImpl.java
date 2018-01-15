package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecPopedomDao;
import cn.itcast.elec.domain.ElecPopedom;


@Repository(IElecPopedomDao.SERVICE_NAME)
public class ElecPopedomDaoImpl extends CommonDaoImpl<ElecPopedom> implements IElecPopedomDao {

}
