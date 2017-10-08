package com.zkingsoft.actions.admin.web;

import java.util.Date;

import javax.annotation.Resource;

import com.zkingsoft.services.web.WebArticleService;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.model.web.WebArticle;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.authority.MatrixAdapter;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.util.WebUtil;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.pojo.AjaxResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkingsoft.anotations.RemoveRequestToken;

/**
 * @author 陈才
 * @date 2016-12-01 10:36
 */
@Controller
@RequestMapping(value = "admin/webArticle")
public class WebArticleController extends BaseController{

	@Resource(name="webArticleService")
	private WebArticleService webArticleService;
	
	@Resource(name="authorityAdapter")
	private Authority  authorityAdapter;
	
	
	
	//记录编辑前的值Before_Edit_Value
	public static final String BEV="WebArticle_BEV";
	
	public static final String fnCode = "WebArticle";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	
	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(WebArticle webArticle, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(webArticleService, webArticle, pageVo);
	}
   	
	/**
	 * 新增或者修改页面
	 */   
	@RemoveRequestToken	
   	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(WebArticle webArticle) {
		if (webArticle.getArtId() != null) {
	   		authorityAdapter.isBtnPermitted(edit);
	   		webArticle.setArtId(((WebArticle)WebUtil.getSessionAttribute(BEV)).getArtId());
	   		AjaxResult result=modify(webArticleService, WebUtil.getSessionAttribute(this.BEV), webArticle, "文章");
			WebUtil.removeSessionAttribute(this.BEV);
			return  result;
		} else {
			// authorityAdapter.isBtnPermitted(add);
			webArticle.setArtCreatetiem(new Date());
			return add(webArticleService, webArticle, "文章");
		}
	}
	
   	/**
	 * 进入修改界面
	 */   
	@SaveRequestToken
   	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		WebArticle webArticle;
		if (id != null) {
			webArticle = webArticleService.findById(id);
			WebUtil.getRequest().setAttribute("obj", webArticle);
			WebUtil.setSessionAttribute(this.BEV, webArticle);
		}
		return "admin/web/article-form";
	}
   	
   	
   	/**
	 * 删除
	 */  
 	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(webArticleService, keys);
	}
  
}