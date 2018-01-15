package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.service.IElecSystemDDLService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chauncey 2017/12/27.
 */
@Controller("elecSystemDDLAction")
@Scope(value="prototype")
public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL> {

    private ElecSystemDDL elecSystemDDL = this.getModel();

    @Resource(name=IElecSystemDDLService.SERVICE_NAME)
    private IElecSystemDDLService elecSystemDDLService;

    public String home(){
        List<ElecSystemDDL> list = elecSystemDDLService.findDistinctKeywod();
        request.setAttribute("systemList", list);
        return "home";
    }

    /**
     * 指定数据类型做为查询条件
     * @return
     */
    public String edit(){
        String keyword = elecSystemDDL.getKeyword();
        List<ElecSystemDDL> list = elecSystemDDLService.findElecSystemDDLListByKeyword(keyword);
        request.setAttribute("list", list);
        return "edit";
    }

    /**
     * 保存数据字典设置
     * @return
     */
    public String save(){
        elecSystemDDLService.saveElecSystemDDL(elecSystemDDL);
        return "save";
    }
}

