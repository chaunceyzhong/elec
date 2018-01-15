package cn.itcast.elec.domain;

import java.io.Serializable;

/**
 * @author chauncey 2018/1/14.
 */
public class ElecRolePopedom implements Serializable{


    private String roleID;
    private String mid;
    private String pid;

    public String getRoleID() {
        return roleID;
    }
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

}
