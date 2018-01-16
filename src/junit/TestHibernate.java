package junit;



import cn.itcast.elec.domain.ElecRole;
import cn.itcast.elec.domain.ElecUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestHibernate {

	/**保存*/
	@Test
	public void save(){
		Configuration configuration = new Configuration();
		//默认加载类路径下的hibernate.cfg.xml，同时加载映射文件
		configuration.configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();

		ElecUser user = new ElecUser();
		user.setUserName("张三");
		ElecRole role = new ElecRole();
		role.setRoleID("100");
		role.setRoleName("老师");
        user.getElecRoles().add(role);
        s.save(user);
		tr.commit();
		s.close();
		
	}
}
