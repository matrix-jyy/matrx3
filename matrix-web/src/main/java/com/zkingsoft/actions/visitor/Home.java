package com.zkingsoft.actions.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkingsoft.constraint.BaseController;

/**
 * 
* @Description: 不需要登录可以访问的action
* @author:姜友瑶 
* @date 2016年8月31日
 */
@Controller
public class Home extends BaseController {


	/**
	 * 
	 * @Description: 首页配置
	 * @author:姜友瑶
	 * @return 返回类型 String
	 * @date 2016年8月28日
	 */
	@RequestMapping(value = "/")
	public String redirect() {
		return "redirect:/common/redirect/login";
	}

}