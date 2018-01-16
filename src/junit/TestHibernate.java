
import cn.itcast.elec.domain.Stock;
import cn.itcast.elec.domain.StockItem;
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
        Stock stock = new Stock();
        StockItem stockItem = new StockItem();
        stock.getStockItems().add(stockItem);
        //stockItem.setStock(stock);
        s.save(stock);
        s.save(stockItem);
        tr.commit();
		s.close();
		
	}
}
