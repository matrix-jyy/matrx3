package com.zkingsoft.actions.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.externalInterface.business.JsApiPayBusiness;
import com.zkingsoft.externalInterface.common.WechatConfigure;
import com.zkingsoft.externalInterface.protocol.paramProtocol.BrandWCPayRequestData;
import com.zkingsoft.externalInterface.protocol.payProtocol.JsApiPayReqData;
import com.zkingsoft.externalInterface.protocol.payProtocol.JsApiPayResData;
import com.zkingsoft.util.StringUtils;
import com.zkingsoft.util.WebUtil;

/**
 * @description 微信接口demo
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-11-30
 */
@Controller
@RequestMapping(value = "wechat")
public class WechatDemoController extends BaseController {

	Logger log = Logger.getLogger(WechatDemoController.class);

	/**
	 * 
	 * @Description: 创建微信预付订单并使用微信内置浏览器发起支付
	 * @author:姜友瑶
	 * @return
	 * @throws Exception
	 *             返回类型 String
	 * @date 2016年10月14日
	 */
	@RequestMapping(value = "/createTradeByWXBrowse")
	public String createPrapareOrder(String page) throws Exception {

		// 获取订单信息
		// 创建微信支付预付接口
		JsApiPayBusiness jsApiPayBusiness = new JsApiPayBusiness();
		// 设置预付请求参数，并获取预付请求结果
		// 这里的用户opein最好每次去拿新的，以免出现在别人微信中登录自己的账号。出现支付 账号与请求账号不一致的情况
		int price = 1;
		String orderNo = StringUtils.getRandomString(10);
		String spInfo = "微信支付测试";
		String userOppenId = "21";
		JsApiPayReqData jsApiPayReqData = new JsApiPayReqData(spInfo, orderNo, price,
				WebUtil.getRequest().getRemoteAddr(), userOppenId);
		// 创建预付订单并返回请求结果
		JsApiPayResData result = jsApiPayBusiness.createPrapareOrder(jsApiPayReqData);
		// 把预付订单的信息存放在request域中
		WebUtil.getRequest().setAttribute("msg", result.toString());
		WebUtil.getRequest().setAttribute("orderNo", orderNo);
		if (result.getReturn_code().equals(WechatConfigure.SUCCESS) && result.getResult_code().equals(WechatConfigure.SUCCESS)) {
			// 请求成功
			// 构建BrandWCPayRequest发起支付需要的参数
			BrandWCPayRequestData payData = new BrandWCPayRequestData(result.getPrepay_id());
			WebUtil.getRequest().setAttribute("payData", payData);
			return "/customer/fyPay";
		} else {
			// 请求失败
			log.info("request prepay_id fall  return_msg=" + result.getReturn_msg());
			return "/common/404";
		}
	}
}
