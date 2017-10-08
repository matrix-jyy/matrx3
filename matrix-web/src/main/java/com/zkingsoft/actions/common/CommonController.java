package com.zkingsoft.actions.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.authority.Matrix;
import com.zkingsoft.authority.loginStrategy.AccountPasswordLogin;
import com.zkingsoft.authority.loginStrategy.LoginStrategy;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.services.sys.SysUsersService;

/**
 * @description 通用控制器,本action未经session过验证器
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-06-26
 */
@RequestMapping(value = "/common")
@Controller
public class CommonController extends BaseController {

	@Resource(name="sysUsersService")
	SysUsersService sysUsersService;

	@Resource
	Matrix matrix;

	/**
	 * @Description: 页面定向方法，每个权限模块公用一个，每个模块共享一个一级路径，已便于进行权限过滤
	 * @date 2016年8月30日
	 */
	@RequestMapping(value = "/redirect/{page1}/{page2}")
	public String redirect(@PathVariable("page1") String page1, @PathVariable("page2") String page2) {
		return "common/" + page1 + "/" + page2;
	}

	/**
	 * 
	 * @Description: 页面定向方法，每个权限模块公用一个，每个模块共享一个一级路径，已便于进行权限过滤
	 * @date 2016年8月30日
	 */
	@RequestMapping(value = "/redirect/{page1}")
	public String redirect(@PathVariable("page1") String page1) {
		return "common/" + page1;
	}

	/**
	 * 
	 * @Description: 登录验证
	 * @author:姜友瑶
	 * @param user
	 * @param request
	 * @return 返回类型 AjaxResult
	 * @date 2016年8月30日
	 */
	@RequestMapping(value = "/dologin")
	public @ResponseBody AjaxResult dologin(SysUsers user) {

		LoginStrategy apLogin = new AccountPasswordLogin(user, sysUsersService);
		user = matrix.login(apLogin);

		switch (user.getSuUserType()) {
		case SystemConstance.USER_TYPE_DEVELOPER:// 开发人员
			return new AjaxResult(AjaxResult.STATUS_OK, "developer/redirect/index", null);
		case SystemConstance.USER_TYPE_SUPER:// 超级管理员
			return new AjaxResult(AjaxResult.STATUS_OK, "super/redirect/index", null);
		case SystemConstance.USER_TYPE_ADMIN:// 企业管理员
			return new AjaxResult(AjaxResult.STATUS_OK, "admin/redirect/index", null);
		case SystemConstance.USER_TYPE_EMPLOYEE:// 企业用户
			return new AjaxResult(AjaxResult.STATUS_OK, "admin/redirect/index", null);
		case SystemConstance.USER_TYPE_CUSTIMER:// 普通用户
			return new AjaxResult(AjaxResult.STATUS_OK, "admin/redirect/index", null);
		default:// 不能识别的用户
			return new AjaxResult(AjaxResult.STATUS_ERR, "common/redirect/login", "什么鬼！");
		}
	}

	/**
	 * 
	 * @Description: 开发模式登录
	 * @author:姜友瑶
	 * @param user
	 * @return 返回类型 AjaxResult
	 * @date 2016年11月15日
	 */
	@RequestMapping(value = "/debugLogin")
	public String debugLogin() {
		SysUsers user = new SysUsers();
		user.setSuAccount("admin");
		user.setSuPassword("123");
		LoginStrategy apLogin = new AccountPasswordLogin(user, sysUsersService);
		matrix.login(apLogin);
		return "admin/index";
	}

	/**
	 * 
	 * @Description: 用户退出系统
	 * @author:姜友瑶
	 * @return
	 * @throws Exception
	 *             返回类型 String
	 * @date 2016年11月15日
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut() throws Exception {
		matrix.getLoginOut();
		return "redirect:/common/redirect/login";
	}

}