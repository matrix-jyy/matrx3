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

public class gateWayTest {

	static Logger log = Logger.getLogger(gateWayTest.class);

	// 使用配置文件初始化工作流引擎，默认会加载类路径下的activiti.cfg文件

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	String processInstanceKey = "gateWayProcess";

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
		Deployment deployment = processEngine.getRepositoryService().createDeployment().name("指定办理人")
				.addClasspathResource("diagrams/gateWay.bpmn").addClasspathResource("diagrams/gateWay.png").deploy();
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
		assigeen.put("bxr", "姜友瑶");

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
	 * 认领任务分配 可以指定认领人
	 */
	@Test
	public void setAssignee() {

		processEngine.getTaskService().setAssignee("12332", "张翠三");
	}

}
