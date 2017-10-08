package com.zkingsoft.plugin.message;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class MeiduSendsms {

	private static String Url = "http://115.29.184.65:8081/sms.aspx?";

	/**
	 * 发送并返回短信验证码
	 * 
	 * @param
	 * @return Result
	 * @throws @date
	 *             2016-07-30 08:35
	 * @param tel 
	 */
	public static String sendSms(String tel) throws Exception{

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
		int mobile_code = (int) ((Math.random() * 9 + 1) * 1000);
		String content = new String("欢迎注册美度商城账号，您的校验码是：【" + mobile_code + "】。请不要把效验码泄露给其他人。如非本人操作，可不用理会！");
		NameValuePair[] data = { //
				new NameValuePair("action","send"),new NameValuePair("userid","55363"),
				new NameValuePair("account", "mydo008"), new NameValuePair("password", "123456"),
				new NameValuePair("mobile", tel), new NameValuePair("content", content),
				new NameValuePair("sendTime", ""), new NameValuePair("extno", ""),};
		method.setRequestBody(data);
		client.executeMethod(method);
		String SubmitResult = method.getResponseBodyAsString();
		Document doc = DocumentHelper.parseText(SubmitResult);
		Element root = doc.getRootElement();
		String returnstatus = root.elementText("returnstatus");
		if(returnstatus.equals("Faild")){
			throw new Exception("验证码发送失败！");
		}
		return mobile_code + "";
	}

}