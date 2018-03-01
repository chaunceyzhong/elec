package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecRole;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.LogonUtils;
import cn.itcast.elec.util.ValueStackUtils;
import cn.itcast.elec.web.form.MenuForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author chauncey 2017/12/15.
 */
@Controller("elecMenuAction")
@Scope(value = "prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {


    @Resource(name= IElecCommonMsgService.SERVICE_NAME)
    private IElecCommonMsgService elecCommonMsgService;

    @Resource(name = IElecUserService.SERVICE_NAME)
    private IElecUserService elecUserService;

    private MenuForm menuForm = this.getModel();

    /**系统登录*/
    public String menuHome(){

        //添加验证码
      /*  boolean flag = LogonUtils.checkNumber(request);
        if(!flag){
            this.addFieldError("error", "验证码有误！");
            return "error";
        }*/
        //1.验证登录密码是否正确
        String name = menuForm.getName();
        String password = menuForm.getPassword();
        ElecUser elecUser = elecUserService.findElecUserByLogonName(name);
        if(elecUser==null){
            //存放错误信息
            this.addFieldError("error", "登录名有误！");
            return "error";
        }
        /*if(StringUtils.isBlank(password)){
            this.addFieldError("error", "密码不能为空！");
            return "error";
        }else {
            //2.页面获取的密码和ElecUser对象中获取的密码比对，如果不一致，提示【密码错误】
            //对页面的密码进行加密
            MD5keyBean md5keyBean = new MD5keyBean();
            String md5Password = md5keyBean.getkeyBeanofStr(password);
            if (!md5Password.equals(elecUser.getLogonPwd())) {
                this.addFieldError("error", "密码有误！");
                return "error";
            }
        }*/
        //3.查看该用户是否具备角色
        Set<ElecRole> elecRoles = elecUser.getElecRoles();
        Hashtable<String,String> ht = new Hashtable<>();
        if(elecRoles!=null && elecRoles.size()>0){
            for(ElecRole elecRole:elecRoles){
                ht.put(elecRole.getRoleID(),elecRole.getRoleName());
            }
        }else{
            this.addFieldError("error","登录名没有分配角色！");
            return "error";
        }
        //4.用户具有角色但是未分配
        String popedom = elecUserService.findPopedomByLogonName(name);
        if(StringUtils.isBlank(popedom)){
            this.addFieldError("error", "登录名具有的角色没有分配权限！");
            return "error";
        }
        //添加【记住我】功能
        LogonUtils.rememberMe(request,response,name,password);
        request.getSession().setAttribute("globle_user", elecUser);
        request.getSession().setAttribute("globle_role", ht);
        request.getSession().setAttribute("globle_popedom", popedom);
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
