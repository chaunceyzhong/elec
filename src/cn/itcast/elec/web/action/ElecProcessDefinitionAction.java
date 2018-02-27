package cn.itcast.elec.web.action;

import cn.itcast.elec.service.IElecProcessDefinitionService;
import cn.itcast.elec.web.form.ProcessDefinitionBean;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author chauncey 2018/2/26.
 */

@SuppressWarnings("serial")
@Controller("elecProcessDefinitionAction")
@Scope(value="prototype")
public class ElecProcessDefinitionAction extends BaseAction<ProcessDefinitionBean> {

    private ProcessDefinitionBean processDefinitionBean = this.getModel();


    @Resource(name= IElecProcessDefinitionService.SERVICE_NAME)
    private IElecProcessDefinitionService elecProcessDefinitionService;

    public String home(){
        List<ProcessDefinition> list = elecProcessDefinitionService.findProcessDefinitionListByLastVersion();
        request.setAttribute("list", list);
        return "home";
    }

    public String add(){
        return "add";
    }

    public String delete(){
        elecProcessDefinitionService.deleteProcessDefinition(processDefinitionBean);
        return "delete";
    }

    public String save(){
        try {
            File file = processDefinitionBean.getUpload();
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            elecProcessDefinitionService.deployProcessDefinition(zipInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "close";
    }

    public String downloadProcessImage(){
        //使用传递的流程定义ID，获取对应流程定义的对象，查询流程图，将流程图放置到InputStream中
        InputStream inputStream = elecProcessDefinitionService.findInputStreamByImage(processDefinitionBean);
        //将输入流对象放置到栈顶的InputStream中
        processDefinitionBean.setInputStream(inputStream);
        return "downloadProcessImage";
    }
}
