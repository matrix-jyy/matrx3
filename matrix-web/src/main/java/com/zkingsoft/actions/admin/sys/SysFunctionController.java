package com.zkingsoft.actions.admin.sys;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.authority.Authority;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysFunction;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysBtnService;
import com.zkingsoft.services.sys.SysFunctionService;
import com.zkingsoft.util.WebUtil;

/**
 * 
 * @Description: 系统功能管理
 * @author:姜友瑶
 * @date 2016年11月16日
 */
@Controller
@RequestMapping(value = "admin/sysFunction")
public class SysFunctionController extends BaseController {

	@Resource(name="sysFunctionService")
	private SysFunctionService sysFunctionService;

	@Resource(name="sysBtnService")
	private SysBtnService sysBtnService;

	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;

	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "SysFunction_BEV";
	public static final String fnCode = "sysfunction";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	public static final String start = fnCode + ":start";
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysFunction sysFunction, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(sysFunctionService, sysFunction, pageVo);
	}

	/**
	 * 显示所有功能
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody AjaxResult all() {
		return new AjaxResult(AjaxResult.STATUS_OK, sysFunctionService.findByModel(null));
	}

	/**
	 * 
	 * @Description: 修改查询function，在session保存旧值
	 * @author:姜友瑶
	 * @param sysFunction
	 * @param pageVo
	 * @return 返回类型 AjaxResult
	 * @date 2016年11月16日
	 */
	@RequestMapping(value = "/findById")
	public @ResponseBody AjaxResult findById(SysFunction sysFunction) {
		sysFunction=sysFunctionService.findById(sysFunction.getFnId());
		WebUtil.setSessionAttribute(BEV, sysFunction);
		return new AjaxResult(AjaxResult.STATUS_OK, Arrays.asList(sysFunction));
	}

	/**
	 * 新增或者修改页面
	 */
	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysFunction sysFunction) {
		if (sysFunction.getFnId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			sysFunction.setFnId(((SysFunction)( WebUtil.getSessionAttribute(BEV))).getFnId());
			AjaxResult result = modify(sysFunctionService, WebUtil.getSessionAttribute(BEV),sysFunction,  "功能");
			// 因为页面是一直打开的需要存新的值
			WebUtil.setSessionAttribute(BEV, sysFunction);
			return result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(sysFunctionService, sysFunction, "功能");
		}
	}

	/**
	 * 启用功能
	 */
	@RequestMapping(value = "/enableFunction")
	public @ResponseBody AjaxResult enableFunction(Long fnId) {
		authorityAdapter.isBtnPermitted(start);
		sysFunctionService.setIsDisable(fnId, SystemConstance.IS_N);
		return new AjaxResult(AjaxResult.STATUS_OK, "功能启用成功");
	}

	/**
	 * 禁用功能
	 */
	@RequestMapping(value = "/disEnableFunction")
	public @ResponseBody AjaxResult disEnableFunction(Long fnId) {
		authorityAdapter.isBtnPermitted(start);
		sysFunctionService.setIsDisable(fnId, SystemConstance.IS_Y);
		return new AjaxResult(AjaxResult.STATUS_OK, "功能禁用成功");
	}

	/**
	 * 进入修改界面
	 */
	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysFunction sysFunction;
		if (id != null) {
			sysFunction = sysFunctionService.findById(id);
			// 查询出所有的按钮
			WebUtil.setRequestAttribute("obj", sysFunction);
			WebUtil.setSessionAttribute(BEV, sysFunction);
		}
		WebUtil.setRequestAttribute("btnList", sysBtnService.findByModel(null));
		return "developer/sysFunction-form";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(Long fnId) {
		authorityAdapter.isBtnPermitted(del);
		int i = sysFunctionService.removeById(fnId);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, "成功删除" + i + "条数据");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "删除失败");
		}
	}

}