package com.zkingsoft.actions.common;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.util.HttpUtils;

/**
 * 
 * @Description: 初始化web容器的类 一些网站启动需要加载的数据和方法在这里配置和执行
 * @author:姜友瑶
 * @date 2016年10月18日
 */
@Service
public class InitWebContainer implements ServletContextAware {

	private ServletContext servletContext;

	/**
	 * 是否为debug模式
	 */
	@Value("${debug}")
	private String debug;
	@Value("${nginx_url}")
	private String nginx_url;

	// 定义打印log
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Description: 容器启动后加载数据
	 * @author:姜友瑶
	 * @param sc
	 * @date 2016年10月18日
	 */
	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;  
		log.info("web 项目启动***********************" + "项目的绝对路径为：" + servletContext.getRealPath("/"));
		log.info("debug=" + debug);
		if ("true".equals(debug)) {
			SystemConstance.DEBUG = true;
		} else {
			SystemConstance.DEBUG = false;
		}
		log.info("SystemConstance.DEBUG=" + SystemConstance.DEBUG);

		// 检查nginx 是否启动
		if (checkNginxIsWork()) {
			log.info("nginx 路径访问成功*********************" + nginx_url);
		} else {
			log.info("nginx 路径访问失败*********************" + nginx_url);
		}

	}

	/**
	 * 检查配置的nginx文件上传路径是否能正常访问
	 * 
	 * @author jiangyouyao
	 * @return
	 */
	private boolean checkNginxIsWork() {
		try {
			URL realUrl = new URL(nginx_url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				log.info(key + "--->" + map.get(key));
				if ("Server".equals(key)) {
					if (map.get(key).toString().indexOf("nginx") > -1) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			log.info("发送GET请求出现异常！" + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			HttpUtils.sendGet("http://www.baidu2.com/uploadeFile/", null);
		} catch (Exception e) {
		}
	}

}
