package com.zkingsoft.actions.admin.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysCompany;
import com.zkingsoft.model.sys.SysFunction;
import com.zkingsoft.model.sys.SysPlat;
import com.zkingsoft.model.sys.SysRole;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysCompanyService;
import com.zkingsoft.services.sys.SysFunctionService;
import com.zkingsoft.services.sys.SysPlatService;
import com.zkingsoft.services.sys.SysRoleService;
import com.zkingsoft.util.WebUtil;

/**
 * 功能管理
 * 
 * @author jiangyouyaoaa
 *
 */
@Controller
@RequestMapping(value = "admin/sysRole")
public class SysRoleController extends BaseController {

	@Resource(name="sysRoleService")
	private SysRoleService sysRoleService;

	@Resource(name="sysFunctionService")
	private SysFunctionService sysFunctionService;

	@Resource(name="sysCompanyService")
	private SysCompanyService sysCompanyService;

	@Resource(name="sysPlatService")
	private SysPlatService sysPlatService;
	@Resource(name = "authorityAdapter")
	private Authority authorityAdapter;

	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "SysRole_BEV";
	public static final String fnCode = "role";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";

	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysRole sysRole, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(sysRoleService, sysRole, pageVo);
	}

	/**
	 * 列表显示当前登录人公司的列表
	 */
	@RequestMapping(value = "/showCompanyRole")
	public @ResponseBody AjaxResult showCompanyRole(SysRole sysRole, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		SysUsers user = authorityAdapter.getLoginUser();
		sysRole.setCompanyId(user.getCompanyId());
		return showList(sysRoleService, sysRole, pageVo);
	}

	/**
	 * 新增或者修改页面
	 */
	@RemoveRequestToken
	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysRole sysRole) {
		if (sysRole.getRoleId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			sysRole.setRoleId(((SysRole) WebUtil.getSessionAttribute(BEV)).getRoleId());
			AjaxResult result = modify(sysRoleService, WebUtil.getSessionAttribute(BEV), sysRole, "角色");
			WebUtil.removeSessionAttribute(BEV);
			return result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(sysRoleService, sysRole, "角色");
		}
	}

	/**
	 * 进入修改界面
	 */
	@SaveRequestToken
	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysRole sysRole;
		List<SysFunction> functions = new ArrayList<>();
		if (id != null) {
			sysRole = sysRoleService.findById(id);
			// 获取所有的功能
			functions = sysFunctionService.findRoleFuntion(id);
			WebUtil.getRequest().setAttribute("obj", sysRole);
			WebUtil.setSessionAttribute(BEV, sysRole);
		} else {
			functions = sysFunctionService.findRoleFuntion(null);
		}
		// 获取用户所在公司所有的平台
		SysUsers user = (SysUsers) WebUtil.getSessionAttribute("user");
		SysCompany company = sysCompanyService.findById(user.getCompanyId());
		List<SysPlat> plats = new ArrayList<>();
		if (company != null) {
			plats = sysPlatService.findByCompany(company);
		}
		WebUtil.setRequestAttribute("plats", plats);
		WebUtil.setRequestAttribute("functions", functions);
		return "admin/sys/sysRole-form";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(sysRoleService, keys);
	}

}