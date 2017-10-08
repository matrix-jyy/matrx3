package com.zkingsoft.actions.admin.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysPlat;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysPlatService;
import com.zkingsoft.util.WebUtil;

/**
 * 平台管理
 * @author jiangyouyao
 */
@Controller
@RequestMapping(value = "admin/sysPlat")
public class SysPlatController extends BaseController{

	@Resource(name="sysPlatService")
	private SysPlatService sysPlatService;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="SysPlat_BEV";
	public static final String fnCode = "plat";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysPlat sysPlat, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(sysPlatService, sysPlat, pageVo);
	}
	/**
	 * 显示所有
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody AjaxResult all() {
		return new AjaxResult(AjaxResult.STATUS_OK, sysPlatService.findByModel(null));
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysPlat sysPlat) {
		if (sysPlat.getPlatId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			sysPlat.setPlatId(((SysPlat)WebUtil.getSessionAttribute(BEV)).getPlatId());
	   		AjaxResult result=modify(sysPlatService, WebUtil.getSessionAttribute(BEV), sysPlat,"应用");
			WebUtil.removeSessionAttribute(BEV);
			return  result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(sysPlatService, sysPlat, "应用");
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysPlat sysPlat;
		if (id != null) {
			sysPlat = sysPlatService.findById(id);
			WebUtil.getRequest().setAttribute("obj", sysPlat);
			WebUtil.setSessionAttribute(BEV, sysPlat);
		}
		return "super/sysPlat-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
 		authorityAdapter.isBtnPermitted(del);
		return remove(sysPlatService, keys);
	}
  
}