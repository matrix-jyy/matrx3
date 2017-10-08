package com.zkingsoft.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zkingsoft.exception.GlobleException;

import net.sf.json.JSONObject;

/**
 * 后台返回给前台数据处理工具类
 * 
 * @author Matrix-J
 * 
 */
public class ResponseUtils {
	public static Log log = LogFactory.getLog(ResponseUtils.class);

	/**
	 * 
	 * 发送json格式数据到页面 过时的方法，不建议使用，在返回值中直接使用
	 * 
	 * @responsebody 可以达到相同的效果
	 * @author Matrix-J
	 * @param response
	 * @param content
	 */
	public static void send(HttpServletResponse response, Object result) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = null;
		String content = JSONObject.fromObject(result).toString();
		try {
			out = response.getWriter();
			// 若发送数据为null 则默认为""
			if (content == null) {
				content = "";
			}
			out.write(content);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		} finally {
			if (out != null)
				out.close();
		}
	}

	@Deprecated
	public static void socketSend(HttpServletResponse response, String msg) {
		response.setContentType("text/event-stream");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.error("ResponseUtils：",e);
		}
		// 若发送数据为null 则默认为""
		if (out != null) {
			out.write(msg);
			out.flush();
		} else {
			throw new GlobleException("out PrintWriter is null");
		}

	}

}
