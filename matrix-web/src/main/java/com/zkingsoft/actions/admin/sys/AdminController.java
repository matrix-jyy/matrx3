package com.zkingsoft.actions.admin.sys;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.interceptors.DuplicateSubmitInterceptor;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysUsersService;
import com.zkingsoft.util.WebUtil;

/**
 * @description 公司管理员管理
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-06-26
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {

	@Resource(name="sysUsersService")
	private SysUsersService sysUsersService;


	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "SysUsers_BEV";
	public static final String fnCode = "user";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	// 重置密码
	public static final String reset_password = fnCode + ":reset_password";

	/**
	 * 
	 * @Description: 页面定向方法，每个权限模块公用一个，每个模块共享一个一级路径，已便于进行权限过滤
	 * @author:姜友瑶
	 * @param page1
	 * @param page2
	 * @return 返回类型 String
	 * @date 2016年8月31日
	 */
	@RequestMapping(value = "/redirect/{page1}/{page2}")
	public String redirect(@PathVariable("page1") String page1, @PathVariable("page2") String page2) {
		return "admin/" + page1 + "/" + page2;
	}

	/**
	 * 
	 * @Description: 页面定向方法，每个权限模块公用一个，每个模块共享一个一级路径，已便于进行权限过滤
	 * @author:姜友瑶
	 * @param page1
	 * @param page2
	 * @return 返回类型 String
	 * @date 2016年8月31日
	 */
	@RequestMapping(value = "/redirect/{page1}")
	public String redirect(@PathVariable("page1") String page1) {
		return "admin/" + page1;
	}

	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysUsers sysUsers, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		SysUsers user = authorityAdapter.getLoginUser();
		sysUsers.setCompanyId(user.getCompanyId());
		sysUsers.setSuUserType(SystemConstance.USER_TYPE_EMPLOYEE);
		return showList(sysUsersService, sysUsers, pageVo);
	}

	/**
	 * 新增或者修改公司管理员
	 */
	@RemoveRequestToken
	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysUsers sysUsers) {
		if (sysUsers.getSuId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			//重新设置修改对象的id
			sysUsers.setSuId(((SysUsers)WebUtil.getSessionAttribute(BEV)).getSuId());
			AjaxResult result = modify(sysUsersService, WebUtil.getSessionAttribute(BEV), sysUsers, "管理员");
			WebUtil.removeSessionAttribute(BEV);
			return result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			// 设置一些基本信息
			sysUsers.setSuAccountStatus(SystemConstance.ACCOUNT_STATUS_ACTIVATE);
			sysUsers.setSuRegisterTime(new Date());
			sysUsers.setSuValid(SystemConstance.RECORD_VALID);
			// 设置账号的类型为公司管理员
			sysUsers.setSuUserType(SystemConstance.USER_TYPE_EMPLOYEE);
			SysUsers user = authorityAdapter.getLoginUser();
			sysUsers.setCompanyId(user.getCompanyId());
			
			return add(sysUsersService, sysUsers, "管理员");
		}
	}

	/**
	 * 进入修改界面
	 */
	@SaveRequestToken
	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysUsers sysUsers;
		if (id != null) {
			DuplicateSubmitInterceptor.pushFormName("suId","suName","roleIds","suAccount","suTel","suEmail");
			sysUsers = sysUsersService.findById(id);
			WebUtil.getRequest().setAttribute("obj", sysUsers);
			WebUtil.setSessionAttribute(BEV, sysUsers);
		}else{
			DuplicateSubmitInterceptor.pushFormName("suName","roleIds","suAccount","suTel","suEmail","suPassword");
		}
		
		return "admin/sys/admin-form";
	}
	/**
	
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(sysUsersService, keys);
	}
	
	public static void main(String p[]) {
		System.out.println(0 % 2);
	}
}