package com.zkingsoft.interceptors;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.util.EncrypUtil;
import com.zkingsoft.util.StringUtils;
import com.zkingsoft.util.WebUtil;

/**
 * 防止重复提交的拦截器
 * 
 * @author guchunxia 2016.08.19
 *
 */
public class DuplicateSubmitInterceptor extends HandlerInterceptorAdapter {

	/** tokenurl标识 */
	private final static String TOKEN_URL = "tokenUrl";
	/** token标识 */
	private final static String TOKEN = "token";
	/** timeStamp标识 */
	private final static String TIMESTAMP = "timeStamp";

	Logger log = Logger.getLogger(DuplicateSubmitInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		RemoveRequestToken removeToken = method.getAnnotation(RemoveRequestToken.class);

		if (removeToken != null) {
			// 提交后则清除session
			HttpSession session = request.getSession();
			String clinetToken = request.getParameter(TOKEN);
			// 获取加密的token
			String encrypToken = getFormToken(request);
			// 判断是否是重复提交了,是的话抛出异常
			if (clinetToken == null || encrypToken == null || !clinetToken.equals(encrypToken)) {
				throw new GlobleException("无效操作");
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		RemoveRequestToken removeToken = method.getAnnotation(RemoveRequestToken.class);

		// 存在保存token的标志
		SaveRequestToken saveToken = method.getAnnotation(SaveRequestToken.class);
		if (saveToken != null) {
			// 查看
			String timeStamp = System.currentTimeMillis() + "";
			String token = createFormToken(request, timeStamp);
			String tokenUrl = request.getServletPath();
			// 给页面取数据
			request.setAttribute(TOKEN, token);
			request.setAttribute(TOKEN_URL, tokenUrl);
		}

		if (removeToken != null) {
			// 提交后则清除session
			request.getSession().removeAttribute(TIMESTAMP);
		}

		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 根据客户端提交的表单数据重新计算表单token
	 * 
	 * @param request
	 * @return
	 */
	private String getFormToken(HttpServletRequest request) {
		// 记录表单名称
		String params = "";
		// 是否需要验证表单合法
		if (request.getParameter(TIMESTAMP) != null) {
			Enumeration enumeration = request.getParameterNames();
			List<String> paramsList = new ArrayList<>();
			while (enumeration.hasMoreElements()) {
				String paraName = (String) enumeration.nextElement();
				if (paraName.equals(TIMESTAMP) || paraName.equals(TOKEN) || paraName.equals(TOKEN_URL))
					continue;
				paramsList.add(paraName);
			}
			Collections.sort(paramsList, String.CASE_INSENSITIVE_ORDER);
			// paramsList.sort(String.CASE_INSENSITIVE_ORDER);//since 1.8
			params = StringUtils.collToStr(paramsList, "");
		}
		String token = request.getParameter(TOKEN_URL) + request.getSession().getAttribute(TIMESTAMP) + params;
		log.info("客户端token=" + token);
		try {
			return EncrypUtil.getMD5(token);
		} catch (UnsupportedEncodingException e) {
			log.error("DuplicateSubmitInterceptor:",e);
		}
		return null;
	}
	/**
	 * 创建form表单的token<br>
	 * token生成规则：<br>
	 * 当前访问的url+当前时间戳+合法表单参数名称(参数名称按照字典排序，如果没有则不加)<br>
	 * 字符串拼好后经过md5加密得到最终的token
	 * 
	 * @param request
	 * @return
	 */
	private String createFormToken(HttpServletRequest request, String timeStamp) {
		String tokenUrl = request.getServletPath();
		String formNames = "";
		if (request.getSession().getAttribute(SystemConstance.FORM_ELEMENT_NAMES) != null) {
			formNames = (String) request.getSession().getAttribute(SystemConstance.FORM_ELEMENT_NAMES);
			// 获取到元素字符串后删除 这里可能存在并发的问题
			request.getSession().removeAttribute(SystemConstance.FORM_ELEMENT_NAMES);
			// 如果参数中包含timeStamp则视为需要验证表单的合法性
			request.setAttribute(TIMESTAMP, timeStamp);
		}
		// 无论是否需要验证表单的合法性timeStamp都需要存在session中用来加密token
		request.getSession().setAttribute(TIMESTAMP, timeStamp);
		log.info("创建的token=" + tokenUrl + timeStamp + formNames);
		try {
			tokenUrl = EncrypUtil.getMD5(tokenUrl + timeStamp + formNames);
		} catch (UnsupportedEncodingException e) {
			log.error("DuplicateSubmitInterceptor:",e);
		}
		return tokenUrl;
	}

	/**
	 * 设置当前表单合法字段,name值会被按照字典顺序排列后存在session中<br>
	 * key=SystemConstance.FORM_ELEMENT_NAMES
	 * 
	 * @see com.zkingsoft.constance.SystemConstance
	 * @param names
	 */
	public static void pushFormName(String... names) {
		List<String> list = Arrays.asList(names);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		// list.sort(String.CASE_INSENSITIVE_ORDER);//since 1.8
		WebUtil.setSessionAttribute(SystemConstance.FORM_ELEMENT_NAMES, StringUtils.collToStr(list, ""));
	}

}
