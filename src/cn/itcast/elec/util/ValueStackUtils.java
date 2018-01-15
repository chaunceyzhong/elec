package cn.itcast.elec.util;

import org.apache.struts2.ServletActionContext;

/**
 * @author chauncey 2017/12/18.
 */
public class ValueStackUtils {

    public  static void setValueStack(Object object){
        ServletActionContext.getContext().getValueStack().pop();
        ServletActionContext.getContext().getValueStack().push(object);
    }
}
