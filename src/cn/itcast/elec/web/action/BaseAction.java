package cn.itcast.elec.web.action;

import cn.itcast.elec.util.GenericTypeUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,ServletRequestAware,ServletResponseAware  {

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	private T entity;
	
	public BaseAction(){
		//泛型转换
		Class entityClass = GenericTypeUtils.getGenericSuperClass(this.getClass());
		try {
			entity = (T) entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override public T getModel() {
		return entity;
	}
	
	@Override public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	@Override public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}
}
