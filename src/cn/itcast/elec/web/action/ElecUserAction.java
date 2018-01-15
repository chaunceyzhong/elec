package cn.itcast.elec.web.action;

import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.ValueStackUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author chauncey 2018/1/9.
 */

@SuppressWarnings("serial")
@Controller("elecUserAction")
@Scope(value="prototype")
public class ElecUserAction extends BaseAction<ElecUser> {

    private ElecUser elecUser = this.getModel();

    @Resource(name=IElecUserService.SERVICE_NAME)
    private IElecUserService elecUserService;

    @Resource(name=IElecSystemDDLService.SERVICE_NAME)
    private IElecSystemDDLService elecSystemDDLService;

    public String home(){
        //1：从数据字典表中，查询所属单位的列表，返回List<ElecSystemDDL>
        List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
        request.setAttribute("jctList", jctList);
        //2:指定查询条件，查询用户集合
        List<ElecUser> userList = elecUserService.findUserListByCondition(elecUser);
        request.setAttribute("userList", userList);
        return "home";
    }

    public String add(){
        //1：查询性别，职位，所属单位，是否在职的下拉菜单
        this.initSystemDDL();
        return "add";
    }

    /**查询性别，职位，所属单位，是否在职的下拉菜单*/
    private void initSystemDDL() {
        List<ElecSystemDDL> jctList = elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
        request.setAttribute("jctList", jctList);
        List<ElecSystemDDL> sexList = elecSystemDDLService.findElecSystemDDLListByKeyword("性别");
        request.setAttribute("sexList", sexList);
        List<ElecSystemDDL> isDutyList = elecSystemDDLService.findElecSystemDDLListByKeyword("是否在职");
        request.setAttribute("isDutyList", isDutyList);
        List<ElecSystemDDL> postList = elecSystemDDLService.findElecSystemDDLListByKeyword("职位");
        request.setAttribute("postList", postList);
    }

    /**
     * 获取对应地区的所属单位名称
     * @return
     */
    public String findJctUnit(){
        String keyword = elecUser.getJctID();
        List<ElecSystemDDL> list = elecSystemDDLService.findElecSystemDDLListByKeyword(keyword);
        ValueStackUtils.setValueStack(list);
        return "findJctUnit";
    }

    /**
     * 检查用户名的合法性
     */
    public String checkUser(){
        //获取登录名
        String logonName = elecUser.getLogonName();
        //以登录名作为条件，查询用户表
        String message = elecUserService.checkUser(logonName);
        //将message放置到栈顶
        elecUser.setMessage(message);
        return "checkUser";
    }

    /**
     * 保存用户
     * @return
     */
    public String save(){
        elecUserService.saveUser(elecUser);
        return "close";
    }

    /**
     * 删除用户
     * @return
     */
    public String delete(){
        elecUserService.deleteUserByIds(elecUser);
        return "delete";
    }

}
