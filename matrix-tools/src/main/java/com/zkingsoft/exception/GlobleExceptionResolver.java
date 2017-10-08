package com.zkingsoft.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.util.ResponseUtils;

/**
 * @description 全局异常处理类
 * @author 姜友瑶
 * @data 2016-06-26
 */
@Service("com.zkingsoft.exception.GlobleExceptionResolver")
public class GlobleExceptionResolver implements HandlerExceptionResolver {

	private Logger log = Logger.getLogger(GlobleExceptionResolver.class);
	private static Map<String, String> exceptionMap = new HashMap<>();

	public GlobleExceptionResolver() {
		// 空参数
		exceptionMap.put("NullPointerException", "001");
		// 类没有找到
		exceptionMap.put("ClassNotFoundException", "002");
		// 数学运算异常
		exceptionMap.put("ArithmeticException", "003");
		// 数组角标越界 
		exceptionMap.put("ArrayIndexOutOfBoundsException", "004");
		// 方法的参数错误
		exceptionMap.put("IllegalArgumentException", "005");
		// 没有访问权限
		exceptionMap.put("IllegalAccessException", "006");
		// 类型强制转换异常
		exceptionMap.put("ClassCastException", "007");
		// 文件未找到异常
		exceptionMap.put("FileNotFoundException", "008");
		// 字符串转换为数字异常
		exceptionMap.put("NumberFormatException", "009");
		// 操作数据库异常
		exceptionMap.put("SQLException", "010");
		// 输入输出异常
		exceptionMap.put("IOException", "011");
		// 方法未找到异常
		exceptionMap.put("NoSuchMethodException", "012");
		// 运行时异常
		exceptionMap.put("RuntimeException", "013");
		// 字段类型设置的长度大小不够
		exceptionMap.put("DataIntegrityViolationException", "014");

		// =====================
		// 错误
		// =====================

		// 内存不足错误
		exceptionMap.put("OutOfMemoryError", "101");
		// 堆栈溢出错误
		exceptionMap.put("StackOverflowError", "102");
		// 未知错误。用于指示Java虚拟机发生了未知严重错误的情况
		exceptionMap.put("UnknownError", "103");

	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		log.error("GlobleExceptionResolver:", ex);
		GlobleException globleException;
		AjaxResult result;
		if (ex instanceof GlobleException) {
			globleException = (GlobleException) ex;
			result = new AjaxResult(AjaxResult.STATUS_ERR, null, globleException.getMessage());
		} else {
			String msg = "";
			String className = ex.getClass().getSimpleName();
			log.info("Exception className=" + className);
			if (exceptionMap.get(className) != null) {
				msg = "出错啦！编号(" + exceptionMap.get(className) + ")";
			} else {
				msg = "出错啦！编号(0)";// 0表示未注册异常
			}
			result = new AjaxResult(AjaxResult.STATUS_UNKWON_ERR, null, msg);
		}
		ResponseUtils.send(response, result);
		return null;
	}

}
