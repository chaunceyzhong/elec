package junit;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;

public class TestService {

	/**保存*/
	@Test
	public void save(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextName("测试Service名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Service备注");
		elecTextService.save(elecText);
	}
	
	/**模拟Action层，调用Service层*/
	@Test
	public void findCollectionByConditionNoPage(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
		ElecText elecText = new ElecText();
//		elecText.setTextName("张");
//		elecText.setTextRemark("张");
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecText);
		if(list!=null && list.size()>0){
			for(ElecText text:list){
				System.out.println(text.getTextName()+"    "+text.getTextDate()+"    "+text.getTextRemark());
			}
		}
		
	}
}
