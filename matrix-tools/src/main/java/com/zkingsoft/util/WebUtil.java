package com.zkingsoft.util;

import java.io.File;
import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zkingsoft.pojo.PageData;

/**
 * 针对ssh项目提供的一些实用性的方法。
 * 
 * @author JiangYouYao
 *
 */
public class WebUtil {

	/**
	 * @Description: 获得ssh项目中的spring的容器
	 * @param request
	 *            当前项目对应的WebApplicationContext对象
	 * @Return: WebApplicationContext
	 * @Author: JiangYouYao
	 * @Version: V1.00
	 * @Create Date: 2014-8-31
	 */
	public static WebApplicationContext getApplication() {
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	/**
	 * 获得web资源的绝对路径
	 * 
	 * @author JiangYouYao
	 * @date 2014年10月14日-上午8:31:01
	 * @param request
	 * @param path
	 * @return
	 */
	public static String getResourceRealPath(String path) {
		return getServletContext().getRealPath(path);
	}

	/**
	 * 
	 * @Description: 获取web项目的访问URl
	 * @author:姜友瑶
	 * @param path
	 * @return 返回类型 String
	 * @date 2016年11月16日
	 */
	public static String getWebUrl() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
				+ getRequest().getContextPath() + "/";
	}

	/**
	 * @Description: 获得该类的泛型类型
	 * @param Class
	 * @Return: Class 泛型的类型
	 * @Author: JiangYouYao
	 * @Version: V1.00 （版本号1.0)
	 * @Create Date: 2014-8-12 （创建日期）
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class clazz) {
		// 泛型转换
		ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * 2016/6/2
	 * 
	 * @author xieguangya
	 * @return getRequest
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 
	 * @Description: 在当前请求的request中获取一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static Object getRequestAttribute(String name) {
		return getRequest().getAttribute(name);
	}

	/**
	 * 
	 * @Description: 在当前请求的request中新增一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static void setRequestAttribute(String name, Object o) {
		getRequest().setAttribute(name, o);
	}

	/**
	 * 
	 * @Description: 在request中删除一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static void removeRequestAttribute(String name) {
		getRequest().removeAttribute(name);
	}

	/**
	 * 
	 * @Description: 在Session中新增一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static void setSessionAttribute(String name, Object o) {
		getSession().setAttribute(name, o);
	}

	/**
	 * 
	 * @Description: 在当前session中获取一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}

	/**
	 * 
	 * @Description: 在Session中删除一个值
	 * @author:姜友瑶
	 * @return
	 * @date 2016年11月11日
	 */
	public static void removeSessionAttribute(String name) {
		getSession().removeAttribute(name);
	}

	/**
	 * 获取session
	 * 
	 * @author Matrix-J
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	/**
	 * 2016年6月15日 获取ServletContext
	 * 
	 * @author Matrix-J
	 * @return getServletContext
	 */
	public static ServletContext getServletContext() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession()
				.getServletContext();
	}

	public static final String LEFT_SLASH = "/";
	/**
	 * 常量“字符.”
	 */
	public static final String CHARACTER_ALL = "\\.";

	/**
	 * 常量“.class”
	 */
	public static final String CLASS_FILE_EXTEND_NAME = ".class";
	/**
	 * 常量“一个空格”
	 */
	public static final String CHARACTER_BLANK = " ";
	/**
	 * 空格转码后结果
	 */
	public static final String SPACE_REPLEACE_STRING = "%20";
	/**
	 * 常量“字符左斜杠”
	 */
	public static final String CHARACTER_LEFT = "\\/";
	/**
	 * 常量"WEB-INF"路径
	 */
	public static final String CONFIG_ROOT = "WEB-INF/";
	/**
	 * 文件协议
	 */
	public static final String FILE_PROTOCOL = "file:";

	/**
	 * <li>功能简述:获取项目的实际路径
	 * <li>详细描述:WEB-INF
	 */
	public static String getContextPath() {

		String name = WebUtil.class.getName();
		name = LEFT_SLASH + name.replaceAll(CHARACTER_ALL, CHARACTER_LEFT) + CLASS_FILE_EXTEND_NAME;
		String space = SPACE_REPLEACE_STRING;
		String path = WebUtil.class.getResource(name).getPath();
		path = path.substring(0, path.indexOf(CONFIG_ROOT) + CONFIG_ROOT.length());
		path = path.replaceAll(space, CHARACTER_BLANK);
		if (path.startsWith(FILE_PROTOCOL)) {
			path = path.substring(FILE_PROTOCOL.length());
		}
		return path;
	}

	/**
	 * <li>功能简述:获得发布目录路径
	 * <li>详细描述:webapps
	 */
	public static String getDeployPath() {

		File tempDir = new File(getContextPath());
		return tempDir.getParentFile().getParentFile().getAbsolutePath();
	}

	/**
	 * <li>功能简述:获得项目目录
	 */
	public static String getWebPath() {

		File tempDir = new File(getContextPath());
		return tempDir.getParentFile().getAbsolutePath();
	}

	/**
	 * 
	 * 
	 * @description 获取客户端ip地址
	 * @data 2015年8月6日 下午7:15:38
	 * @author Administrator
	 * @param request
	 * @return
	 */
	public static String getCustomerIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}

	/**
	 * 得到simple mvc PageData
	 * 
	 * @author jiangyouyao
	 */
	public static PageData getPageData() {
		return new PageData(getRequest());
	}
	
	

}
