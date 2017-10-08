package com.zkingsoft.plugin.message;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Sendsms {

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	/**
	 * 发送并返回短信验证码
	 * 
	 * @param
	 * @return Result
	 * @throws @date
	 *             2016-03-05 17:35
	 * @author Matrix-J
	 * @param tel 
	 */
	public static String sendSms(String tel) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
 		String content = new String("欢迎参与最美系部评选活动您的校验码是：【" + mobile_code + "】。请不要把效验码泄露给其他人。如非本人操作，可不用理会！");
		NameValuePair[] data = { //
				new NameValuePair("account", "cf_rwkj"), new NameValuePair("password", "123456"),
				new NameValuePair("mobile", tel), new NameValuePair("content", content), };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			if ("2".equals(code)) {
				System.out.println("success message");
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return mobile_code + "";
	}
public static void main(String[] args) {
	sendSms("18390563793");
}
}