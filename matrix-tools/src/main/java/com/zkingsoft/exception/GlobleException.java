package com.zkingsoft.exception;

/**
 * @description 系统异常类
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-06-26
 */
public class GlobleException extends RuntimeException {

	private static final long serialVersionUID = 5538900603076485646L;
	// 异常信息
	public String message;
	
	public GlobleException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
