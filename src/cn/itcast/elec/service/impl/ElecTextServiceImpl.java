package cn.itcast.elec.service.impl;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
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

@Service(IElecTextService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecTextServiceImpl implements IElecTextService {

	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao elecTextDao;
	
	/**保存*/
	@Override @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(ElecText elecText) {
		elecTextDao.save(elecText);
	}
	
	/**指定页面传递的查询条件，查询对应的结果集信息，返回List<ElecText>*/
	/**
		SELECT * FROM elec_text o WHERE 1=1      #Dao层
		AND o.textName LIKE '%张%'           #Service层
		AND o.textRemark LIKE '%张%'         #Service层
		ORDER BY o.textDate ASC,o.textRemark DESC  #Service层
	 */
	@Override public List<ElecText> findCollectionByConditionNoPage(ElecText elecText) {
		//组织查询条件
		String condition = "";
		//存放可变参数？
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isNotBlank(elecText.getTextName())){
			condition += " and o.textName like ?";
			paramsList.add("%"+elecText.getTextName()+"%");
		}
		if(StringUtils.isNotBlank(elecText.getTextRemark())){
			condition += " and o.textRemark like ?";
			paramsList.add("%"+elecText.getTextRemark()+"%");
		}
		//将集合中存放的可变参数转换成数组
		Object [] params = paramsList.toArray();
		//使用集合存放排序的条件(有序排列)
		Map<String, String> orderby = new LinkedHashMap<>();
		orderby.put("o.textDate", "asc");
		orderby.put("o.textRemark", "desc");
		List<ElecText> list = elecTextDao.findCollectionByConditionNoPage(condition,params,orderby);
		return list;
	}
	
}
