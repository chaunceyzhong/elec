package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.util.ValueStackUtils;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author chauncey 2017/12/17.
 */
@Controller("elecCommonMsgAction")
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {

    private ElecCommonMsg elecCommonMsg = this.getModel();

    @Resource(name = IElecCommonMsgService.SERVICE_NAME)
    private IElecCommonMsgService elecCommonMsgService;

    /**
     * @Name            :   home
     * @Description     :   运行监控的首页显示
     * @Author          :   chauncey
     * @Version         :   V1.00
     * @Create Date     :   2017-12-17
     * @param           :   无
     * @return : 跳转到system/actionIndex.jsp
     */
    public String home(){
        ElecCommonMsg elecCommonMsg  = elecCommonMsgService.findElecCommonMsg();
        ValueStackUtils.setValueStack(elecCommonMsg);
        return "home";
    }

    public String save(){
        elecCommonMsgService.saveElecCommonMsg(elecCommonMsg);
        return "save";
    }
}
