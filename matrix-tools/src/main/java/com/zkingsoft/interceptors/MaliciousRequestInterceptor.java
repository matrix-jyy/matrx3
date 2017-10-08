package com.zkingsoft.interceptors;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zkingsoft.util.WebUtil;

public class MaliciousRequestInterceptor implements HandlerInterceptor {

	Logger log = Logger.getLogger(this.getClass());
	private static final String IPMAP="ipmap";
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String requestUrl = request.getRequestURI();
		log.info("requestUrl" + requestUrl);
		String ip = request.getRemoteAddr();
		@SuppressWarnings("unchecked")
		HashMap<String, Long> ipMap = (HashMap<String, Long>) WebUtil.getServletContext().getAttribute(IPMAP);
		if (ipMap == null) {
			// 创建一个Mpa用来存放ip和上次访问访问时间的对应关系
			ipMap = new HashMap<String, Long>();
			WebUtil.getServletContext().setAttribute(IPMAP, ipMap);
		}
		log.info("current ipSize()====" + ipMap.size());
		// 记录数达到 2000个后清空一次
		if (ipMap.size() > 5000) {
			log.info("clean ip-----------------------");
			ipMap.clear();
		}
		Long prevTime = ipMap.get(ip);
		Long nowTime = new Date().getTime();
		// ip第一次访问
		if (prevTime == null) {
			ipMap.put(ip, nowTime);
			// 正常访问
			log.info("first request normal-----------" + ip + nowTime);
			return true;
		} else {
			// 上下文中存在改ip地址
			Long timeLong = nowTime - prevTime;

			//随机的时间区间
			int randTime= (int) (Math.random()*500+100);
			
			if (timeLong < randTime) {
				// 如果相差不到150毫秒就是恶意请求
				// 覆盖原来的重新计算时间
				ipMap.put(ip, nowTime);
				log.info(" ******************** Hacker attack request **************" + ip);
				// 跳转到信息页面
				response.sendRedirect(WebUtil.getWebUrl()+"/common/redirect/malicious");
				return false;
			} else {
				ipMap.put(ip, nowTime);
				// 正常访问
				log.info("normal-----------ip=" + ip + " timeLong=" + timeLong);
				return true;
			}
		}

	}

}
