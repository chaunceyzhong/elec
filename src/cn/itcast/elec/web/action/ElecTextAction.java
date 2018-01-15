package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


/**
 * 相当于spring容器中定义
 * <bean id="elecTextAction" class="cn.itcast.elec.web.action.ElecTextAction">
 *
 */
@SuppressWarnings("serial")
@Controller("elecTextAction")
@Scope(value="prototype")
public class ElecTextAction extends BaseAction<ElecText> {

	private ElecText elecText = this.getModel();

	@Resource(name=IElecTextService.SERVICE_NAME)
	private IElecTextService elecTextService;
	
	/**保存*/
	public String save(){
		elecTextService.save(elecText);
		return "save";
	}


	
}
