package com.zkingsoft.test.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class helloworldTest {

	static Logger log = Logger.getLogger(helloworldTest.class);

	// 使用配置文件初始化工作流引擎，默认会加载类路径下的activiti.cfg文件

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	String processInstanceKey = "leaveProcess";

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
		Deployment deployment = processEngine.getRepositoryService().createDeployment().name("请假流程带条件")
				.addClasspathResource("diagrams/leave.bpmn").addClasspathResource("diagrams/leave.png").deploy();
		log.info(deployment.getId());
		log.info(deployment.getName());

	}

	/**
	 * 查询流程定义
	 */
	@Test
	public void findProcessDefinition() {
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery()
				// .deploymentId(deploymentId) 用部署对象id查询
				// .processDefinitionId(processDefinitionId)//使用流程定义id查询
				// .list()//返回集合
				.processDefinitionName("leave1").list();
		log.info(list);
		if (list != null && list.size() > 0) {
			for (ProcessDefinition p : list) {
				log.info("流程定义id:" + p.getId()); // =流程定义名称+版本号+水机随机生成数
				log.info("流程定义名称:" + p.getName());
				log.info("流程定义key:" + p.getKey());
				log.info("流程定义版本：" + p.getVersion());
				log.info("资源名称：" + p.getResourceName());
				log.info("资源png名称：" + p.getDiagramResourceName());
				log.info("**********************");
			}
		}
	}

	/**
	 * 查询流程定义图片
	 */
	@Test
	public void viewPic() {
		InputStream imgIn = processEngine.getRepositoryService().getResourceAsStream("17501", "diagrams/helloword.png");

		File f = new File("/Users/jiangyouyao/es6/1.png");
		try {
			FileUtils.copyInputStreamToFile(imgIn, f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除流程定义
	 */
	@Test
	public void deleteProcessDefinition() {
		/**
		 * 只能删除没有启动的流程，流程启动抛出异常
		 */
		// processEngine.getRepositoryService().deleteDeployment("12501");
		/**
		 * 级联删除，不管流程是否启动
		 */
		processEngine.getRepositoryService().deleteDeployment("12501", true);
	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInterface() {

		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processInstanceKey);// 使用key启动一个流程实例，默认使用最新版本启动
		log.info("流程实例id=" + pi.getId());
		log.info("流程定义id=" + pi.getProcessDefinitionId());

	}

	/**
	 * 查询个人任务
	 */
	@Test
	public void queryTask() {
		// 根据指派人查询
		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee("姜友瑶").singleResult();
		log.info("任务id:" + task.getId());
		log.info("任务名称:" + task.getName());
		log.info("任务办理人:" + task.getAssignee());
		log.info("任务流程定义id:" + task.getProcessDefinitionId());
		log.info("任务流程实例id:" + task.getProcessInstanceId());
		log.info("任务执行对象id:" + task.getExecutionId());
		 
	}

	/**
	 * 完成任务
	 */
	@Test
	public void completeTask() {
		// 根据任务id完成任务
		String taskId = "45003";
		Map variables = new HashMap<>();
		variables.put("leave", 14);
		processEngine.getTaskService().complete(taskId,variables);
	}

	/**
	 * 设置流程变量
	 */
	@Test
	public void setVariable() {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee("李四").singleResult();

		// 设置流程变量的值, 流程变量可以是基本类型和序列号对象。 localvariable是只对当前任务可见的变量
		Map variables = new HashMap<>();
		variables.put("请假天数", 14);
		variables.put("请假原因", "回家结婚");
		// 设置执行对象流程变量
		// runtimeService.setVariables("123", variables);
		// runtimeService.startProcessInstanceById(processDefinitionId,
		// variables)启动流程变量的时候设置流程变量
		// 设置任务的流程变量
		TaskService taskService = processEngine.getTaskService();
		taskService.setVariables(task.getId(), variables);

		// taskService.complete(taskId, variables);//完成任务的时候社会中流程变量
	}

	/**
	 * 获取流程变量
	 */
	@Test
	public void getVariable() {
		TaskService taskService = processEngine.getTaskService();
		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee("李四").singleResult();

		log.info(taskService.getVariables(task.getId(), Arrays.asList(new String[] { "请假天数", "请假原因" })));
	}
	
	/**
	 * 查询历史流程变量
	 */
	@Test
	public void getHistoryVariable(){
		List<HistoricVariableInstance> hisVar=processEngine.getHistoryService().createHistoricVariableInstanceQuery()
		.variableName("请假天数").list();
		for (HistoricVariableInstance hv : hisVar) {
			log.info(hv);
		}
	}

	/**
	 * 查询历史任务
	 */
	@Test
	public void queryHisttoryTask() {
		List<HistoricTaskInstance> listTask = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
				.taskAssignee("李四").list();
		if (listTask != null && listTask.size() > 0) {
			for (HistoricTaskInstance ht : listTask) {
				log.info(ht.getAssignee() + "   " + ht.getStartTime().toLocaleString() + "    " + ht.getName());
			}
		}
	}

}
