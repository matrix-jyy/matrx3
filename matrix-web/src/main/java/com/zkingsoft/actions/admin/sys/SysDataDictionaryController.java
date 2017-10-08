package com.zkingsoft.actions.admin.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysDataDictionary;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysDataDictionaryService;
import com.zkingsoft.util.WebUtil;

/**
 * 
 * @description 数据字典Controller
 * @author chen
 * @date 2016年12月20日
 */
@Controller
@RequestMapping(value = "admin/sysDataDictionary")
public class SysDataDictionaryController extends BaseController{

	@Resource(name="sysDataDictionaryService")
	private SysDataDictionaryService sysDataDictionaryService;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="SysDataDictionary_BEV";
	public static final String fnCode = "dataDictionary";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysDataDictionary sysDataDictionary, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		System.out.println(sysDataDictionary.getDicCateId());
		System.out.println(sysDataDictionary.getDicCateName());
		return showList(sysDataDictionaryService, sysDataDictionary, pageVo);
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysDataDictionary sysDataDictionary) {
		if (sysDataDictionary.getDicId() != null) {
			System.out.println("修改");
			authorityAdapter.isBtnPermitted(edit);
			sysDataDictionary.setDicId(((SysDataDictionary)( WebUtil.getSessionAttribute(BEV))).getDicId());
	   		AjaxResult result=modify(sysDataDictionaryService, WebUtil.getSessionAttribute(BEV), sysDataDictionary,"数据字典");
			WebUtil.removeSessionAttribute(BEV);
			return  result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(sysDataDictionaryService, sysDataDictionary, "数据字典");
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long dicId) {
		authorityAdapter.isBtnPermitted(edit);
		SysDataDictionary sysDataDictionary;
		if (dicId != null) {
			sysDataDictionary = sysDataDictionaryService.findById(dicId);
			WebUtil.getRequest().setAttribute("obj", sysDataDictionary);
			System.out.println(sysDataDictionary);
			WebUtil.setSessionAttribute(BEV, sysDataDictionary);
		}
		return "admin/bus/dictionary-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
 		authorityAdapter.isBtnPermitted(del);
		return remove(sysDataDictionaryService, keys);
	}
  
}