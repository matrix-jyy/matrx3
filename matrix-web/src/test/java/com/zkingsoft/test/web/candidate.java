package com.zkingsoft.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.sun.tools.javac.main.Main;

public class candidate {

	static Logger log = Logger.getLogger(candidate.class);

	// 使用配置文件初始化工作流引擎，默认会加载类路径下的activiti.cfg文件

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	String processInstanceKey = "candidateProcess";

	/**
	 * 使用java进行初始化数据配置数据源
	 */
	@Test
	public void createTable() {
		ProcessEngineConfiguration pr = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
		pr.setJdbcDriver("com.mysql.jdbc.Driver");
		pr.setJdbcUrl(
				"jdbc:mysql://localhost:3306/matrixtest?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&transformedBitIsBoolean=true");
		pr.setJdbcUsername("root");
		pr.setJdbcPassword("root");
		pr.setDatabaseSchema(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 工作流和核心对象
		ProcessEngine processEngine = pr.buildProcessEngine();
	}

	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinition() {
		// 可以从类路径，从文件流从zip压缩包部署流程
		Deployment deployment = processEngine.getRepositoryService().createDeployment().name("组任务")
				.addClasspathResource("diagrams/candidate.bpmn").addClasspathResource("diagrams/candidate.png").deploy();
		log.info(deployment.getId());
		log.info(deployment.getName());

	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInterface() {
		// 指定任务接收人
		Map assigeen = new HashMap<>();

		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processInstanceKey, assigeen);// 使用key启动一个流程实例，默认使用最新版本启动

		log.info("流程实例id=" + pi.getId());
		log.info("流程定义id=" + pi.getProcessDefinitionId());

	}

	/**
	 * 完成任务
	 */
	@Test
	public void completeTask() {
		// 根据任务id完成任务
		String taskId = "72504";
		Map variables = new HashMap<>();
		variables.put("money", 1400);
		processEngine.getTaskService().complete(taskId, variables);
	}

	/**
	 * 查询组任务
	 */
	@Test
	public void queryCandidate() {

		List<Task> taskList=processEngine.getTaskService().createTaskQuery().taskCandidateUser("a").list();
		for (Task task : taskList) {
			log.info("任务id:" + task.getId());
			log.info("任务名称:" + task.getName());
			log.info("任务办理人:" + task.getAssignee());
			log.info("任务流程定义id:" + task.getProcessDefinitionId());
			log.info("任务流程实例id:" + task.getProcessInstanceId());
			log.info("任务执行对象id:" + task.getExecutionId());
		}
	}
	
	/**
	 * 查询组任务候选人
	 */
	@Test
	public void queryCandidateList(){
		List<IdentityLink> list=processEngine.getTaskService().getIdentityLinksForTask("95004");
		for (IdentityLink identityLink : list) {
			log.info(identityLink.getGroupId()+"  "+identityLink.getTaskId()+"   "+identityLink.getType()+"  "+identityLink.getUserId());
		}
	}
	
	/**
	 * 组任务分配操作
	 */
	@Test
	public void getTask(){
		
		//拾取，认领任务，组任务被认领后转换为个人任务
		processEngine.getTaskService()
			.claim("95004", "a");  //将组任务分配给个人，这个人可以是组里的人，也可以是非组里的人。
		
		
		//将个人任务退回组任务，前提是原来是组任务
		
		processEngine.getTaskService().setAssignee("xx", null);
		
		
	}
	
	
	/**
	 * 组任务成员新增和删除
	 */
	@Test
	public void setCanidate(){
		//添加一个成员 
		processEngine.getTaskService()
			.addCandidateUser("95004", "妹妹");
		//删除一个成员
		processEngine.getTaskService()
			.deleteCandidateUser("95004", "a");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
