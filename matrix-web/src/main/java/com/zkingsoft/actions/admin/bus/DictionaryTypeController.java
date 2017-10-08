package com.zkingsoft.actions.admin.bus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constance.BusinessConstance;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.bus.BusCatalog;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.bus.BusCatalogService;
import com.zkingsoft.util.WebUtil;
/**
 * 数据字典分类管理
 * @author 罗凯
 *
 */
@Controller
@RequestMapping(value = "admin/dictionaryType")
public class DictionaryTypeController extends BaseController{

	@Resource(name="busCatalogService")
	private BusCatalogService busCatalogService;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	
	
	
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="DictionaryType_BEV";
	
	public static final String fnCode = "dictionaryType";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(BusCatalog busCatalog, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		busCatalog.setCataType(BusinessConstance.CATA_DICTIONARY_TYPE);
		return showList(busCatalogService, busCatalog, pageVo);
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(BusCatalog busCatalog) {
		if (busCatalog.getCataId() != null) {
	   		authorityAdapter.isBtnPermitted(edit);
	   		busCatalog.setCataId(((BusCatalog)( WebUtil.getSessionAttribute(BEV))).getCataId());
	   		AjaxResult result=modify(busCatalogService, WebUtil.getSessionAttribute(BEV), busCatalog, "数据字典类型");
			WebUtil.removeSessionAttribute(BEV);
			return  result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			busCatalog.setCataType(BusinessConstance.CATA_DICTIONARY_TYPE);
			return add(busCatalogService, busCatalog, "数据字典类型");
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		BusCatalog busCatalog;
		if (id != null) {
			busCatalog = busCatalogService.findById(id);
			WebUtil.getRequest().setAttribute("obj", busCatalog);
			WebUtil.setSessionAttribute(BEV, busCatalog);
		}
		return "admin/bus/dictionaryType-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(busCatalogService, keys);
	}
 	
 	
	/**
	 * 查询所有的数据字典分类
	 */
	@RequestMapping(value = "/showAll")
	public @ResponseBody AjaxResult showAll(BusCatalog busCatalog) {
		authorityAdapter.isBtnPermitted(search);
		busCatalog.setCataType(BusinessConstance.CATA_DICTIONARY_TYPE);
		return showList(busCatalogService, busCatalog, null);
	}
  
}
