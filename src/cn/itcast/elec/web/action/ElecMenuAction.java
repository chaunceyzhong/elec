package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.util.ValueStackUtils;
import cn.itcast.elec.web.form.MenuForm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author chauncey 2017/12/15.
 */
@Controller("elecMenuAction")
@Scope(value = "prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {

    //注入运行监控的Service
    @Resource(name= IElecCommonMsgService.SERVICE_NAME)
    private IElecCommonMsgService elecCommonMsgService;
    private MenuForm menuForm = this.getModel();

    /**系统登录*/
    public String menuHome(){
        return "menuHome";
    }

    /**系统首页标题*/
    public String title(){
        return "title";
    }

    /**系统首页左侧菜单*/
    public String left(){
        return "left";
    }

    /**系统首页显示框架改变*/
    public String change(){
        return "change";
    }

    /**系统首页功能区域显示*/
    public String loading(){
        return "loading";
    }

    /**重新登录*/
    public String logout(){
        request.getSession().invalidate();
        return "logout";
    }

    /**
     * @Name: alermStation
     * @Description: 显示站点运行情况
     * @Author: 刘洋（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2013-11-17（创建日期）
     * @Parameters: 无
     * @Return: 跳转到menu/alermStation.jsp
     */
    public String alermStation(){
        //1：查询运行监控表，获取运行监控表中的数据，返回ElecCommonMsg对象
        ElecCommonMsg commonMsg = elecCommonMsgService.findElecCommonMsg();
        //2：将ElecCommonMsg对象压入到栈顶，用于表单回显，将所有数据显示到文本框中
        ValueStackUtils.setValueStack(commonMsg);
        return "alermStation";
    }

    /**
     * @Name: alermDevice
     * @Description: 显示设备运行情况
     * @Author: 刘洋（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2013-11-17（创建日期）
     * @Parameters: 无
     * @Return: 跳转到menu/alermDevice.jsp
     */
    public String alermDevice(){
        //1：查询运行监控表，获取运行监控表中的数据，返回ElecCommonMsg对象
        ElecCommonMsg commonMsg = elecCommonMsgService.findElecCommonMsg();
        //2：将ElecCommonMsg对象压入到栈顶，用于表单回显，将所有数据显示到文本框中
        ValueStackUtils.setValueStack(commonMsg);
        return "alermDevice";
    }


}
