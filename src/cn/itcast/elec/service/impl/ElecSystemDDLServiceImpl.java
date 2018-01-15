package cn.itcast.elec.service.impl;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.service.IElecSystemDDLService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chauncey 2017/12/27.
 */
@Service(IElecSystemDDLService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService{

    @Resource(name=IElecSystemDDLDao.SERVICE_NAME)
    private IElecSystemDDLDao elecSystemDDLDao;

    @Override public List<ElecSystemDDL> findDistinctKeywod() {
        List<ElecSystemDDL> list = elecSystemDDLDao.findDistinctKeywod();
        return list;
    }

    /**
     *  使用数据类型作为条件，查询数据字典中对应数据类型的集合
     * @param keyword
     * @return
     */
    @Override public List<ElecSystemDDL> findElecSystemDDLListByKeyword(String keyword) {

        String condition = "";
        List<Object> paramsList = new ArrayList<>();
        if(StringUtils.isNotBlank(keyword)){
            condition += " and o.keyword=?";
            paramsList.add(keyword);
        }
        Object [] params = paramsList.toArray();
        Map<String,String> orderby = new LinkedHashMap<>();
        orderby.put("o.ddlCode","asc");
        List<ElecSystemDDL> list = elecSystemDDLDao.findCollectionByConditionNoPage(condition,params,orderby);
        return list;
    }

    @Override @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
    public void saveElecSystemDDL(ElecSystemDDL elecSystemDDL) {
        //1：获取页面保存的3个参数的值
        //数据类型
        String keywordName = elecSystemDDL.getKeywordname();
        //用来判断执行的是新增一种数据类型，还是在已有数据类型的基础上进行修改和编辑（new/add)
        String typeflag = elecSystemDDL.getTypeflag();
        //数据项的值
        String []itemname = elecSystemDDL.getItemname();
        //使用typeflag作为判断业务的标识
        //如果typeflag.equal("new")，此时是新增一种数据类型
        if(typeflag!=null && typeflag.equals("new")){
            //遍历页面传递的数据项的值的数组，组织PO对象，执行新增的操作。
            this.saveSystemDDL(itemname,keywordName);
        }
        //如果typeflag.equal("add")，此时在已有的数据类型的基础上进行修改和编辑
        else{
            //先使用数据类型作为条件，查询对应数据类型下的列表（List<ElecSystemDDL>），执行删除，删除List
            List<ElecSystemDDL> list = this.findElecSystemDDLListByKeyword(keywordName);
            //删除对应数据类型的数据
            elecSystemDDLDao.deleteObjectByCollection(list);
            //遍历页面传递的数据项的值的数组，组织PO对象，执行新增的操作。
            this.saveSystemDDL(itemname,keywordName);
        }
    }

    /**遍历页面传递的数据项的值的数组，组织PO对象，执行新增的操作。*/
    private void saveSystemDDL(String[] itemname, String keywordName) {
        if(itemname!=null && itemname.length>0){
            for(int i=0;i<itemname.length;i++){
                ElecSystemDDL systemDDL = new ElecSystemDDL();
                systemDDL.setKeyword(keywordName);
                systemDDL.setDdlCode(i+1);
                systemDDL.setDdlName(itemname[i]);
                elecSystemDDLDao.save(systemDDL);
            }
        }
    }

}
