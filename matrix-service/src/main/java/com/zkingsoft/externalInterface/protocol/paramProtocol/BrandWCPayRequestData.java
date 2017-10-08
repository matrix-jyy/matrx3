package com.zkingsoft.externalInterface.protocol.paramProtocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.zkingsoft.externalInterface.common.WechatConfigure;
import com.zkingsoft.externalInterface.common.RandomStringGenerator;
import com.zkingsoft.externalInterface.common.Signature;
import com.zkingsoft.util.DateUtils;

/**
 * 
* @Description: 调用微信浏览器内置api请求支付要求数据
* @author:姜友瑶 
* @date 2016年10月14日
 */
public class BrandWCPayRequestData {

    //每个字段具体的意思请查看API文档
    private String appId = "";
    private String timeStamp = "";
    private String nonceStr = "";
    private String prepay_id = "";
    private String signType = "";
    private String paySign = "";
    
    public BrandWCPayRequestData(String prepay_id){
    	//默认必须设置
        setAppId(WechatConfigure.appID);
        //随机字符串，不长于32 位
        setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
        setTimeStamp(DateUtils.getTimeSecent());
        setPrepay_id("prepay_id="+prepay_id);
        //根据API给的签名规则进行签名 【 必须要放在本方法的最后】
        setSignType(WechatConfigure.SIGN_MD5);
        String sign = Signature.getSign(toMap());
        setPaySign(sign);//把签名数据设置到Sign这个属性中
    }

    
	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getNonceStr() {
		return nonceStr;
	}


	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}


	public String getPrepay_id() {
		return prepay_id;
	}


	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getPaySign() {
		return paySign;
	}


	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}


	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                	if(field.getName().equals("prepay_id")){
                		map.put("package", obj);
                	}else{
                		map.put(field.getName(), obj);
                	}
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
