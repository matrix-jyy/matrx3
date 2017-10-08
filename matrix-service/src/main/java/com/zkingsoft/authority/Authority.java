package com.zkingsoft.authority;
/**
 * Authority 类定义了Matrix 框架权限部分的主要接口
 * 这些接口被需要实现权限管理的类来实现。
 * @author jiangyouyao
 * @date 2016-12-10
 */
public interface Authority {
	public boolean isBtnPermitted(String matchStr) throws IllegalArgumentException;

	public boolean isFnPermitted(String fnCode);
	
	public <T> T getLoginUser();

	public void getLoginOut();
}
