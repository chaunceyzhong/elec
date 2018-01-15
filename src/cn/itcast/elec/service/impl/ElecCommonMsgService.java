package cn.itcast.elec.service.impl;

import cn.itcast.elec.dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chauncey 2017/12/17.
 */
@Service(IElecCommonMsgService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecCommonMsgService implements IElecCommonMsgService {

    @Resource(name = IElecCommonMsgDao.SERVICE_NAME)
    private IElecCommonMsgDao elecCommonMsgDao;

    @Override public ElecCommonMsg findElecCommonMsg() {
        List<ElecCommonMsg> list =  elecCommonMsgDao.findCollectionByConditionNoPage("",null,null);
        ElecCommonMsg elecCommonMsg = null;
        if(list!=null && list.size()>0){
            elecCommonMsg = list.get(0);
        }
        return elecCommonMsg;
    }

    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED)
    @Override public void saveElecCommonMsg(ElecCommonMsg elecCommonMsg) {
        List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
        if(list!=null && list.size()>0 ){
            ElecCommonMsg commonMsg = list.get(0);
            elecCommonMsg.setComID(commonMsg.getComID());
            commonMsg.setDevRun(elecCommonMsg.getDevRun());
            commonMsg.setStationRun(elecCommonMsg.getStationRun());
            commonMsg.setCreateDate(new Date());
        }else{
            elecCommonMsg.setCreateDate(new Date());
            elecCommonMsgDao.save(elecCommonMsg);
        }
    }
}
