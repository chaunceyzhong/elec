package cn.itcast.elec.service.impl;

import cn.itcast.elec.service.IElecProcessDefinitionService;
import cn.itcast.elec.web.form.ProcessDefinitionBean;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author chauncey 2018/2/26.
 */
@Service(IElecProcessDefinitionService.SERVICE_NAME)
@Transactional(readOnly=true,rollbackFor = Exception.class)
public class ElecProcessDefinitionServiceImpl implements IElecProcessDefinitionService {

    @Resource(name="processEngine")
    private ProcessEngine processEngine;

    @Override public List<ProcessDefinition> findProcessDefinitionListByLastVersion() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)
                .list();
        Map<String,ProcessDefinition> map = new LinkedHashMap<>();
        if(list!=null && list.size()>0){
            for(ProcessDefinition pd : list){
                map.put(pd.getKey(),pd);
            }
        }
        List<ProcessDefinition> pdList = new ArrayList<>(map.values());
        return pdList;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = Exception.class)
    @Override public void deployProcessDefinition(ZipInputStream zipInputStream) {
        processEngine.getRepositoryService()
                .createDeployment()
                .addResourcesFromZipInputStream(zipInputStream)
                .deploy();

    }

    @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false,rollbackFor = Exception.class)
    @Override public void deleteProcessDefinition(ProcessDefinitionBean processDefinitionBean) {

        String key = processDefinitionBean.getKey();
        try {
            key = URLDecoder.decode(key,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<ProcessDefinition> list  =  processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .list();
        if(list!=null && list.size()>0){
            for(ProcessDefinition pd:list){
                //获取部署ID
                String deploymentId = pd.getDeploymentId();
                //删除流程定义
                processEngine.getRepositoryService()
                        .deleteDeploymentCascade(deploymentId);
            }
        }
    }

    @Override
    public InputStream findInputStreamByImage(ProcessDefinitionBean processDefinitionBean) {
        //使用传递的流程定义ID，获取对应流程定义的对象
        String id = processDefinitionBean.getId();
        try {
            id = URLDecoder.decode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ProcessDefinition pd = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionId(id)
                .uniqueResult();
        //使用流程定义的对象，获取部署ID和资源图片名称，获取输入流对象（inputStream）
        //部署ID
        String deploymentId = pd.getDeploymentId();
        //资源图片名称
        String imageResourceName = pd.getImageResourceName();
        //存放资源图片
        InputStream inputStream = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, imageResourceName);
        return inputStream;
    }
}
