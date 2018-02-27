package cn.itcast.elec.service;

import cn.itcast.elec.web.form.ProcessDefinitionBean;
import org.jbpm.api.ProcessDefinition;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author chauncey 2018/2/26.
 */
public interface IElecProcessDefinitionService {

    String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecProcessDefinitionServiceImpl";

    List<ProcessDefinition> findProcessDefinitionListByLastVersion();

    void deployProcessDefinition(ZipInputStream zipInputStream);

    void deleteProcessDefinition(ProcessDefinitionBean processDefinitionBean);

    InputStream findInputStreamByImage(
            ProcessDefinitionBean processDefinitionBean);

}
