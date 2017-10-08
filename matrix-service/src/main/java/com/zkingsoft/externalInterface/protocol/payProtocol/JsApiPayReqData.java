package com.zkingsoft.externalInterface.protocol.payProtocol;



import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.zkingsoft.externalInterface.common.WechatConfigure;
import com.zkingsoft.externalInterface.common.RandomStringGenerator;
import com.zkingsoft.externalInterface.common.Signature;

/**
 * 
* @Description: 预付订单请求参数
* @author:姜友瑶 
* @date 2016年10月14日
 */
public class JsApiPayReqData {

    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String device_info = "";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";
    private String detail = "";
    private String attach = "";
    private String out_trade_no = "";
    private String fee_type = "";
    private int total_fee = 0;
    /**
     * 接收微信支付异步通知回调地址
     */
    private String notify_url = WechatConfigure.PAY_NOTIFY_URL;
    private String trade_type = WechatConfigure.TRADE_TYPE_JSAPI;
    private String openid = "";
    private String limit_pay = "";
    private String spbill_create_ip = "";
    private String product_id = "";
    private String time_start = "";
    private String time_expire = "";
    private String goods_tag = "";
    private String auth_code = "";

    
    public JsApiPayReqData() {
	}
    
    /**
     * @param authCode 这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @param body 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @param attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
     * @param totalFee 订单总金额，单位为“分”，只能整数
     * @param deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
     * @param spBillCreateIP 订单生成的机器IP
     * @param timeStart 订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     * @param timeExpire 订单失效时间，格式同上
     * @param goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
     */
    public JsApiPayReqData(String body,String outTradeNo,int totalFee,String spBillCreateIP,String openid){
    	//默认必须设置
        setAppid(WechatConfigure.appID);
        setMch_id(WechatConfigure.mchID);
        setBody(body);
        setOut_trade_no(outTradeNo);
        setTotal_fee(totalFee);
        setSpbill_create_ip(spBillCreateIP);
        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        setOpenid(openid);
        //根据API给的签名规则进行签名 【 必须要放在本方法的最后】
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
      
    }

    
    
    public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    /**
     * 
    * @Description:body最大长度128
    * @author:姜友瑶
    * @param body    
    * 返回类型  void    
    * @date 2016年10月13日
     */
    public void setBody(String body) {
    
    	if(body.length()>128){
    		body=body.substring(0, 128);
    	}
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
