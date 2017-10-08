package com.zkingsoft.externalInterface.common;

/**
 * User: rizenguo Date: 2014/10/29 Time: 14:40 这里放置各种配置数据
 */
public class WechatConfigure {
	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	// 接收微信支付异步通知回调地址
	public static final String PAY_NOTIFY_URL = "http://fdc.mydoline.com/index";
	
	
	// 交易类型
	public static final String TRADE_TYPE_JSAPI = "JSAPI";
	public static final String TRADE_TYPE_NATIVE = "NATIVE";
	public static final String TRADE_TYPE_APP = "APP";
	public static final String SIGN_MD5 = "MD5";

	// 公众平台秘钥
	public static String APPSECRET = "91b9dea4ddb5a813c68da6fa88c20e95";

	// 微信支付秘钥
	public static String key = "QWHRKNALFJDHLFASDAAFDSSSSSSSSSSS";

	// 微信分配的公众号ID（开通公众号之后可以获取到）
	public static String appID = "wxdb39818fcb6c822b";
	
	//微信开放平台应用id
	public static String web_appID = "wx43dc4efeb5e8d869";
		
	

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String mchID = "1398789202";

	// 受理模式下给子商户分配的子商户号
	public static String subMchID = "";

	// HTTPS证书的本地路径
	public static String certLocalPath = "";

	// HTTPS证书密码，默认密码等于商户号MCHID
	public static String certPassword = "1398789202";

	// 是否使用异步线程的方式来上报API测速，默认为异步模式
	public static boolean useThreadToDoReport = true;

	// 机器IP
	public static String ip = "";

	// 以下是几个API的路径：
	public static String JS_PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	public static String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	// 1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	// 2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	// 3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	// 5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	// 6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	// 7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	// 成功标识
	public static String SUCCESS = "SUCCESS";
	public static String FAIL = "FAIL";

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";

}
