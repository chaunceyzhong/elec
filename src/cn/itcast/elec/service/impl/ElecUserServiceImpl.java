package cn.itcast.elec.service.impl;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.MD5keyBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chauncey 2018/1/9.
 */

@Service(IElecUserService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecUserServiceImpl implements IElecUserService {

    private final  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name= IElecUserDao.SERVICE_NAME)
    private IElecUserDao elecUserDao;

    @Resource(name= IElecSystemDDLDao.SERVICE_NAME)
    private IElecSystemDDLDao elecSystemDDLDao;

    @Override public List<ElecUser> findUserListByCondition(ElecUser elecUser) {

        //组织查询条件
        String condition="";
        List<Object> paramsList = new ArrayList<>();
        //获取用户姓名
        String userName = elecUser.getUserName();
        if(StringUtils.isNotBlank(userName)){
            condition += " and o.userName like ?";
            paramsList.add("%"+userName+"%");
        }
        //所属单位
        if(StringUtils.isNotBlank(elecUser.getJctID())){
            condition += " and o.jctID = ?";
            paramsList.add(elecUser.getJctID());
        }
        //入职日期开始时间
        if(elecUser.getOnDutyDateBegin()!=null){
            condition += " and o.onDutyDate >= ?";
            paramsList.add(elecUser.getOnDutyDateBegin());
        }
        //入职日期结束时间
        if(elecUser.getOnDutyDateEnd()!=null){
            condition += " and o.onDutyDate <= ?";
            paramsList.add(elecUser.getOnDutyDateEnd());
        }
        Object [] params = paramsList.toArray();
        //排序，按照入职时间的降序排列
        Map<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("o.onDutyDate", "desc");
        List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, orderby);
        /**3：涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
        this.userPOListToVOList(list);
        return list;
    }
    /**涉及到数据字段字段的时候，要将数据项的编号转换成数据项值*/
    private void userPOListToVOList(List<ElecUser> list) {
        if(list!=null && list.size()>0){
            for(ElecUser elecUser:list){
                //使用数据类型和数据项的编号获取数据项的值
                //性别
                elecUser.setSexID(StringUtils.isNotBlank(elecUser.getSexID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("性别",elecUser.getSexID()):"");
                //所属单位
                elecUser.setJctID(StringUtils.isNotBlank(elecUser.getJctID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("所属单位",elecUser.getJctID()):"");
                //是否在职
                elecUser.setIsDuty(StringUtils.isNotBlank(elecUser.getIsDuty())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("是否在职",elecUser.getIsDuty()):"");
                //职位
                elecUser.setPostID(StringUtils.isNotBlank(elecUser.getPostID())?elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("职位",elecUser.getPostID()):"");
                //#数据字典项要定义成VARCHAR，可以用于数据字典的ddlCode和ddlName之间的转换
            }
        }
    }
    @Override public String checkUser(String logonName) {
        logger.debug("开始经检查.............");
        String message = "";
        if(StringUtils.isNotBlank(logonName)){
            String condition = " and o.logonName = ?";
            Object [] params = {logonName};
            List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition,params,null);
            if(list!=null && list.size()>0){
                message = "2";
            }else{
                message = "3";
            }
        }else{
            message = "1";
        }
        logger.debug("结束检查..............");
        return message;
    }

    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
    @Override public void saveUser(ElecUser elecUser) {
        //获取用户ID
        String userID = elecUser.getUserID();
        //添加md5的密码加密
        this.md5Password(elecUser);
        //1：如果userID==null，直接获取保存的PO对象，执行save()
        if(StringUtils.isBlank(userID)){
            elecUserDao.save(elecUser);
        }
        //2：如果userID!=null，获取更新的PO对象，执行update()
        else{
            elecUserDao.update(elecUser);
        }
    }

    @Override public ElecUser findElecUserByID(ElecUser elecUser) {
        //获取用户ID
        String userID = elecUser.getUserID();
        ElecUser user = elecUserDao.findObjectByID(userID);
        return user;
    }
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
    @Override public void deleteUserByIds(ElecUser elecUser) {
            //获取ID
            String userID = elecUser.getUserID();
            String [] ids = userID.split(", ");
            elecUserDao.deleteBojectByIDs(ids);
    }

    /**添加md5的密码加密，对登录名的密码进行安全的控制*/
    private void md5Password(ElecUser elecUser) {
        //获取页面输入的密码
        String logonPwd = elecUser.getLogonPwd();
        //加密后的密码
        String md5LogonPwd;
        //如果密码没有填写，给出初始密码123
        if(StringUtils.isBlank(logonPwd)){
            logonPwd = "123";
        }
        //判断是否对密码进行了修改，获取password
        String password = elecUser.getPassword();
        //表示没有修改密码，此时不需要进行加密
        if(password!=null && password.equals(logonPwd)){
            md5LogonPwd = logonPwd;
        }
        else{
            //md5密码加密
            MD5keyBean md5keyBean = new MD5keyBean();
            md5LogonPwd = md5keyBean.getkeyBeanofStr(logonPwd);
        }
        //最后讲加密后的密码放置到ElecUser中.
        elecUser.setLogonPwd(md5LogonPwd);
    }

    @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
    @Override public ElecUser findElecUserByLogonName(String logonName) {
        String condition = "";
        List<Object> paramsList = new ArrayList<>();
        if(StringUtils.isNotBlank(logonName)){
            condition += " and o.logonName=?";
            paramsList.add(logonName);
        }
        Object [] params = paramsList.toArray();
        //查询
        List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
        ElecUser elecUser = null;
        if(list!=null && list.size()>0){
            elecUser = list.get(0);
            //方案一：
            //			elecUser.getElecRoles().size();
            //方法二：
           Hibernate.initialize(elecUser.getElecRoles());
        }
        return elecUser;
    }

    /**
     * 根据用户名返回用户权限
     *
     * @param name
     * @return
     */
    @Override public String findPopedomByLogonName(String name) {
        List<Object> list = elecUserDao.findPopedomByLogonName(name);
        StringBuffer buffer = new StringBuffer();
        if(list!=null && list.size()>0){
            for(Object o:list){
                buffer.append(o.toString()).append("@");
            }
            //删除最后一个@
            buffer.deleteCharAt(buffer.length()-1);
        }
        return buffer.toString();
    }



}
