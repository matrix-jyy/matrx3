package com.zkingsoft.authority.loginStrategy;

public interface LoginStrategy {
	
	/**
	 * 登录接口，实现具体的登录验证方法，需要返回一个用户对象
	 * @return
	 */
	public Object login();
	
	/**
	 * 是否要求查询用户权限，如果要求查询用户权限
	 * @return
	 */
	public boolean requireInitUserPower();
	
}
