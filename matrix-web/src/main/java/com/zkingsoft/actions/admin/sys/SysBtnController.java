package com.zkingsoft.actions.admin.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysBtn;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysBtnService;
import com.zkingsoft.util.WebUtil;

/**
 * @Description 按钮管理
 * @author jiangyouyao
 * @date 2016-11-17 16:43
 */
@Controller
@RequestMapping(value = "admin/sysBtn")
public class SysBtnController extends BaseController{

	@Resource(name="sysBtnService")
	private SysBtnService sysBtnService;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="SysBtn_BEV";
	public static final String fnCode = "btn";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysBtn sysBtn, PaginationVO pageVo) {
		return showList(sysBtnService, sysBtn, pageVo);
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysBtn sysBtn,HttpServletRequest request) {
		
		if (sysBtn.getBtnId() != null) {
			sysBtn.setBtnId(((SysBtn)WebUtil.getSessionAttribute(BEV)).getBtnId());
	   		AjaxResult result=modify(sysBtnService, WebUtil.getSessionAttribute(BEV),sysBtn, "按钮");
			WebUtil.removeSessionAttribute(BEV);
			return  result;
		} else {
			return add(sysBtnService, sysBtn, "按钮");
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		SysBtn sysBtn;
		if (id != null) {
			sysBtn = sysBtnService.findById(id);
			WebUtil.getRequest().setAttribute("obj", sysBtn);
			WebUtil.setSessionAttribute(BEV, sysBtn);
		}
		return "developer/sysBtn-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		return remove(sysBtnService, keys);
	}
 	
 	/**
	 * 显示所有按钮
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody AjaxResult all() {
		return new AjaxResult(AjaxResult.STATUS_OK, sysBtnService.findByModel(null));
	}

	
	
}