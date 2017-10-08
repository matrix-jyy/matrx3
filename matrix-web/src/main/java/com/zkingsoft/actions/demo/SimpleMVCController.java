package com.zkingsoft.actions.demo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.SimpleMVC.DaoSupport;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PageData;
import com.zkingsoft.util.WebUtil;

/**
 * simple mvc demo
 * 
 * @author jiangyouyao
 *
 */
@Controller
@RequestMapping(value = "/simplemvc")
public class SimpleMVCController {

	@Resource(name="daoSupport")
	private DaoSupport dao;

	@RequestMapping(value = "/add")
	private @ResponseBody AjaxResult addUser() throws Exception {
		PageData pd = WebUtil.getPageData();
		dao.save("demo.add", pd);
		return new AjaxResult(AjaxResult.STATUS_OK, "用户添加成功");
	}

	@RequestMapping(value = "/showList")
	private @ResponseBody AjaxResult showList() throws Exception {
		PageData pd = WebUtil.getPageData();

		return new AjaxResult(AjaxResult.STATUS_OK, (List<Object>) dao.findForList("demo.selectInPage", pd),
				(Integer) dao.findForObject("demo.selectTotal", pd));
	}

}
