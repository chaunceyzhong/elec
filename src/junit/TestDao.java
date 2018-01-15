package junit;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;

public class TestDao {

	/**保存*/
	@Test
	public void save(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextName("测试Dao名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Dao备注");
		elecTextDao.save(elecText);
	}
	
	/**更新*/
	@Test
	public void update(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextID("402881e442599916014259991c450001");
		elecText.setTextName("更新名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("更新备注");
		elecTextDao.update(elecText);
	}
	
	/**使用主键ID查询对象*/
	@Test
	public void findObjectById(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		Serializable id = "402881e4425a729301425a734c2a0001";
		ElecText elecText = elecTextDao.findObjectByID(id);
		System.out.println(elecText.getTextName()+"    "+elecText.getTextDate()+"    "+elecText.getTextRemark());
	}
	
	/**删除（使用1个主键ID和多个主键ID的数组）*/
	@Test
	public void deleteObjectByIDs(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		//Serializable [] ids = {"402881e442599916014259991c450001","402881e44259d1c0014259d1c48b0001"};
		Serializable ids = "402881e44259de3a014259de40e40001"; 
		elecTextDao.deleteBojectByIDs(ids);
	}
	
	/**删除（将对象封装成集合，使用集合删除集合中存放的所有对象）*/
	@Test
	public void deleteObjectByCollection(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDao elecTextDao = (IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<>();
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("402881e44259dfc5014259dfcaa10001");
		ElecText elecText2 = new ElecText();
		elecText2.setTextID("402881e44259e338014259e36de70001");
		list.add(elecText1);
		list.add(elecText2);
		elecTextDao.deleteObjectByCollection(list);
	}
}
