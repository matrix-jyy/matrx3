package com.zkingsoft.externalInterface.business;

import org.apache.log4j.Logger;

import com.zkingsoft.externalInterface.common.Util;
import com.zkingsoft.externalInterface.protocol.payProtocol.JsApiPayReqData;
import com.zkingsoft.externalInterface.protocol.payProtocol.JsApiPayResData;
import com.zkingsoft.externalInterface.service.JsApiPayService;

/**
 * User: rizenguo Date: 2014/12/1 Time: 17:05
 */
public class JsApiPayBusiness {

	private JsApiPayService jsApiPayService;

	
    //打log用
    Logger log=Logger.getLogger(JsApiPayBusiness.class);
    
	public JsApiPayBusiness() throws Exception {
		jsApiPayService = new JsApiPayService();
	}

	/**
	 * 
	 * @Description:生成预付订单
	 * @author:姜友瑶
	 * @param scanPayReqData
	 *            返回类型 void
	 * @throws Exception
	 * @date 2016年10月13日
	 */
	public JsApiPayResData createPrapareOrder(JsApiPayReqData jsApiPayReqData) throws Exception {
		// 接受API返回
		String payServiceResponseString;
		
		long costTimeStart = System.currentTimeMillis();

		payServiceResponseString = jsApiPayService.request(jsApiPayReqData);
		
		long costTimeEnd = System.currentTimeMillis();
		long totalTimeCost = costTimeEnd - costTimeStart;
		log.info("request createPrapareOrder taking ：" + totalTimeCost + "ms");
		
		// 将从API返回的XML数据映射到Java对象
		JsApiPayResData jsApiPayResData = (JsApiPayResData) Util.getObjectFromXML(payServiceResponseString,
				JsApiPayResData.class);
		
		return jsApiPayResData;
	}

}
