package com.zkingsoft.actions.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.services.bus.BusAreaService;

/**
 * 通用业务公共控制器
 * 
 * @author guchuxia
 * 
 */
@RequestMapping("/busCommon")
@Controller
public class BusCommonController {

	@Resource(name="busAreaService")
	private BusAreaService busAreaService;

	/**
	 * 获取子地区
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/findChildenArea")
	public @ResponseBody
	AjaxResult findChildenArea(Long parentId) {
		return new AjaxResult(AjaxResult.STATUS_OK, busAreaService.findChildArea(parentId));
	}

	/**
	 * 获取子地区
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/test")
	public String test(Long parentId) {
		return "demo/area";
	}

}
