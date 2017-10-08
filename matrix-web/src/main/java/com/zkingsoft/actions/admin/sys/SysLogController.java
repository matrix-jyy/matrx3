package com.zkingsoft.actions.admin.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.syslog.SysLog;
import com.zkingsoft.syslog.SysLogService;
import com.zkingsoft.util.StringUtils;
import com.zkingsoft.util.WebUtil;

/**
 * 系统日志管理
 * @author maoluguang
 */
@Controller
@RequestMapping(value = "admin/sysLog")
public class SysLogController extends BaseController {

	@Resource(name="sysLogServices")
	private SysLogService sysLogServices;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="SysLog_BEV";
	public static final String fnCode = "log";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysLog syslog, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		if (pageVo == null) {
			pageVo = new PaginationVO();
		}
		List<SysLog> dataList = sysLogServices.findInPage(syslog, pageVo);
		AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, null, null, dataList, sysLogServices.findTotal(syslog));
		return result;
	}
	/**
	 * 显示所有
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody AjaxResult all() {
		return new AjaxResult(AjaxResult.STATUS_OK, sysLogServices.findByModel(null));
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysLog syslog) {
		if (syslog.getLogId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			int i = sysLogServices.modify(syslog);
			if (i > 0) {
				return new AjaxResult(AjaxResult.STATUS_OK, null, "系统日志修改成功");
			} else {
				return new AjaxResult(AjaxResult.STATUS_ERR, null, "系统日志修改失败");
			}
		} else {
			authorityAdapter.isBtnPermitted(add);
			int i = sysLogServices.add(syslog);
			if (i > 0) {
				return new AjaxResult(AjaxResult.STATUS_OK, null, "系统日志添加成功");
			} else {
				return new AjaxResult(AjaxResult.STATUS_ERR, null, "系统日志添加失败");
			}
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysLog syslog;
		if (id != null) {
			syslog = sysLogServices.findById(id);
			WebUtil.getRequest().setAttribute("obj", syslog);
		}
		return "admin/syslog-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
 		authorityAdapter.isBtnPermitted(del);
 		List<Long> ids = StringUtils.strToCollToLong(keys, ",");
		int i = sysLogServices.remove(ids);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, "成功删除" + i + "条数据");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "删除失败");
		}
	}
  
}