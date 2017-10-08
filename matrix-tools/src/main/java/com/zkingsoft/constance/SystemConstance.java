package com.zkingsoft.constance;

/**
 * 
 * @Description:系统常量类
 * @author:姜友瑶
 * @date 2016年8月28日
 */
public abstract class SystemConstance {

	/**
	 * 用户登录key
	 */
	public static final String LOGIN_KEY = "user";
	public static final String MATRIX = "MATRIX";

	// =====================================账号类型
	/**
	 * 平台管理员
	 */
	public static final String USER_TYPE_SUPER = "super";
	/**
	 * 开发人员
	 */
	public static final String USER_TYPE_DEVELOPER = "developer";
	/**
	 * 公司管理员
	 */
	public static final String USER_TYPE_ADMIN = "admin";
	/**
	 * 客户
	 */
	public static final String USER_TYPE_CUSTIMER = "customer";
	/**
	 * 公司员工
	 */
	public static final String USER_TYPE_EMPLOYEE = "employee";

	// =====================================账号状态
	/**
	 * 已激活
	 */
	public static final String ACCOUNT_STATUS_ACTIVATE = "activate";
	/**
	 * 未激活
	 */
	public static final String ACCOUNT_STATUS_INACTIVATED = "inactivated";
	/**
	 * 锁定
	 */
	public static final String ACCOUNT_STATUS_LOCKED = "locked";

	// =====================================逻辑删除标识
	/**
	 * 未删除
	 */
	public static final String RECORD_VALID = "Y";
	/**
	 * 已删除
	 */
	public static final String RECORD_INVALID = "N";

	// =====================================是否类字段
	/**
	 * 是
	 */
	public static final String IS_Y = "是";
	/**
	 * 否
	 */
	public static final String IS_N = "否";
	
	/**标示form表单的合法参数名称*/
	public static final String FORM_ELEMENT_NAMES = "FORM_ELEMENT_NAMES";

	/**
	 * 是否为debug模式
	 */
	public static boolean DEBUG=true;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
