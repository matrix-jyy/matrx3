package com.zkingsoft.actions.admin.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.web.WebArticleType;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.web.WebArticleTypeService;
import com.zkingsoft.util.WebUtil;

/**
 * @author 陈才
 * @date 2016-12-01 10:36
 */
@Controller
@RequestMapping(value = "admin/webArticleType")
public class WebArticleTypeController extends BaseController {

	@Resource(name = "webArticleTypeService")
	private WebArticleTypeService webArticleTypeService;

	@Resource(name = "authorityAdapter")
	private Authority authorityAdapter;

	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "WebArticleType_BEV";

	public static final String fnCode = "WebArticleType";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";

	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(WebArticleType webArticleType, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(webArticleTypeService, webArticleType, pageVo);
	}

	/**
	 * 加载所有列表
	 */
	@RequestMapping(value = "/all")
	public @ResponseBody AjaxResult all(WebArticleType webArticleType) {
		authorityAdapter.isBtnPermitted(search);
		List<WebArticleType> list = webArticleTypeService.findByModel(webArticleType);
		if (webArticleType.getArtTypeId() != null && list.size() > 0) {
			WebUtil.setSessionAttribute("WebArticleType_BEV", list.get(0));
		}
		return new AjaxResult(AjaxResult.STATUS_OK, null, "执行成功", list, 0);
	}

	/**
	 * 新增或者修改页面
	 */
	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(WebArticleType webArticleType) {
		if (webArticleType.getArtTypeId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			AjaxResult result = modify(webArticleTypeService, WebUtil.getSessionAttribute(this.BEV), webArticleType,
					"文章类型");
			WebUtil.removeSessionAttribute(this.BEV);
			return result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(webArticleTypeService, webArticleType, "文章类型");
		}
	}

	/**
	 * 进入修改界面
	 */
	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		WebArticleType webArticleType;
		if (id != null) {
			webArticleType = webArticleTypeService.findById(id);
			WebUtil.getRequest().setAttribute("obj", webArticleType);
			WebUtil.setSessionAttribute(this.BEV, webArticleType);
		}
		return "admin/web/articleType-form";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(webArticleTypeService, keys);
	}

	/**
	 * 删除文章类型及其子类
	 */
	@RequestMapping(value = "/delAll")
	public @ResponseBody AjaxResult delAll(Long artTypeId) {
		authorityAdapter.isBtnPermitted(del);
		int i = webArticleTypeService.removeById(artTypeId);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, "成功删除" + i + "条数据");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "删除失败");
		}
	}
}