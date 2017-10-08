package com.zkingsoft.externalInterface.service;

import com.zkingsoft.externalInterface.common.WechatConfigure;
import com.zkingsoft.externalInterface.protocol.payProtocol.JsApiPayReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class JsApiPayService extends BaseService{

    public JsApiPayService() throws Exception {
        super(WechatConfigure.UNIFIEDORDER);
    }

    /**
     * 请求支付服务
     * @param jsApiPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(JsApiPayReqData jsApiPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(jsApiPayReqData);

        return responseString;
    }
    
    
    
    
    /**
     * 请求openid
     * @param 
     * @return API返回的数据
     * @throws Exception
     */
    public String requestOpenid(JsApiPayReqData jsApiPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(jsApiPayReqData);

        return responseString;
    }
    
    
    
}
