package com.zkingsoft.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.util.SessionUtils;

public class LoginCheckInterceptor implements HandlerInterceptor {

	Logger log = Logger.getLogger(this.getClass());

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	/**
	 * 检查管理员是否登陆
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		String requestUrl = request.getRequestURI();
		log.info(request.getHeader("Referer"));
		log.info(requestUrl);
		// 如果访问特殊的路径需要验证管理员的登录权限
		if (requestUrl.indexOf("/admin/") != -1 || requestUrl.indexOf("/super/") != -1
				|| requestUrl.indexOf("/developer/") != -1
				|| requestUrl.indexOf("/customer/") != -1) {
			if (SessionUtils.getSessionObject(SystemConstance.LOGIN_KEY) == null) {
				//判断是否为异步请求
				String requestType = request.getHeader("X-Requested-With");  
				if(requestType==null){
					response.sendRedirect(request.getContextPath() + "/common/redirect/login");
				}else{
					response.getWriter().write("loginTimeOut...");
				}
				return false;
			} else {
				return true;
			} 
		} else {
			return true;
		}
	}

}
