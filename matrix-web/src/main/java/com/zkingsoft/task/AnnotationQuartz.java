package com.zkingsoft.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 * 
 * @Title: AnnotationQuartz.java  
 * @Package com.zkingsoft.test  
 * @description
 * @author 罗凯
 * @email 18075895212@qq.com
 * @date 2016年8月17日 上午9:26:43
 */
@Component
public class AnnotationQuartz {
	// 定义打印log
	Logger log = Logger.getLogger(this.getClass());

	@Scheduled(cron = "0 0/1 *  * * ? ")
	// 需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate
	public void excute() {

		log.info("定时器启动了");
	}
}