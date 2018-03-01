package cn.itcast.elec.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import cn.itcast.elec.domain.ElecApplication;
import cn.itcast.elec.domain.ElecApproveInfo;




public interface IElecApplicationFlowService {

	public static final String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecApplicationFlowServiceImpl";

	void saveApplication(ElecApplication elecApplication);

	List<ElecApplication> findElecApplicationListByCondition(
            ElecApplication elecApplication);

	List<ElecApplication> findMyTaskList();

	Collection<String> findOutComesByTaskID(String taskID);

	ElecApplication findElecApplicationByID(Integer id);

	void approve(ElecApplication elecApplication);

	List<ElecApproveInfo> findApproveInfoListByApplicationID(
            ElecApplication elecApplication);

	void viewProcessPicture(ElecApplication elecApplication);

	InputStream findInputStreamByDeploymentId(String deploymentId);

	

}
