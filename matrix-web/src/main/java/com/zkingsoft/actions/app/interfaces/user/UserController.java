package com.zkingsoft.actions.app.interfaces.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.authority.Authority;
import com.zkingsoft.authority.Matrix;
import com.zkingsoft.authority.loginStrategy.AppPhoneLogin;
import com.zkingsoft.authority.loginStrategy.LoginStrategy;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.services.sys.SysUsersService;
import com.zkingsoft.util.EncrypUtil;
import com.zkingsoft.util.HttpUtils;
import com.zkingsoft.util.WebUtil;

/**
 * @description 用户接口
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-06-26
 */
@Controller
@RequestMapping(value = "app/user")
public class UserController extends BaseController {

	@Resource(name = "sysUsersService")
	private SysUsersService sysUsersService;

	@Resource(name = "authorityAdapter")
	private Authority authorityAdapter;
	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "SysUsers_BEV";

	private static String YZM = "yzm";
	private static String PHONE = "phone";

	@Resource
	Matrix matrix;

	Logger log = Logger.getLogger(UserController.class);

	/**
	 * @Description: 手机密码登录
	 * @author:姜友瑶
	 */
	@RequestMapping(value = "/dologin")
	public @ResponseBody AjaxResult dologin(SysUsers user) {
		LoginStrategy apLogin = new AppPhoneLogin(user, sysUsersService);
		user = matrix.login(apLogin);
		AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, "登录成功");
		result.putInMap("user", user);
		return result;
	}

	/**
	 * 手机注册获取验证码 默认都发送123
	 */
	@RequestMapping(value = "/getRegisterCode")
	public @ResponseBody AjaxResult getRegisterCode(String phone) {
		if (!sysUsersService.isOnlyPhone(phone)) { // 判断手机号是否已注册
			try {
				// WebUtil.getSession().setAttribute(YZM,
				// Sendsms.sendSms(phone)); // 获取验证码
				WebUtil.getSession().setAttribute(YZM, "123"); // 获取验证码
				WebUtil.getSession().setAttribute(PHONE, phone); // 获取发送验证码的手机号
			} catch (Exception e) {
				log.error("UserController:", e);
			}
			return new AjaxResult(AjaxResult.STATUS_OK, "验证码发送成功");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "手机号已注册");
		}
	}

	/**
	 * 注册会员
	 * 
	 */
	@RequestMapping(value = "/register")
	public @ResponseBody AjaxResult register(SysUsers user) {
		if (WebUtil.getSession().getAttribute(YZM) != null) { // 判断验证码是否失效
			if (user.getSuTel().equals(WebUtil.getSession().getAttribute(PHONE))) { // 判断手机是否为发送短信的手机
				if (!(WebUtil.getRequest().getParameter(YZM)).equals(WebUtil.getSession().getAttribute(YZM))) // 判断验证码是否正确
				{
					return new AjaxResult(AjaxResult.STATUS_ERR, null, "验证码错误!");
				} else {
					// 判断手机是否以注册
					if (!sysUsersService.isOnlyPhone(user.getSuTel())) {
						// 密码加密
						try {
							user.setSuPassword(EncrypUtil.getMD5(user.getSuPassword()));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						user.setSuRegisterTime(new Date());
						user.setSuUserType(SystemConstance.USER_TYPE_CUSTIMER);
						user.setSuAccountStatus(SystemConstance.ACCOUNT_STATUS_ACTIVATE);
						user.setSuValidateCode(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
						user.setSuValid(SystemConstance.RECORD_VALID);
						// 设置用户注册赠送金额
						int i = sysUsersService.add(user);
						if (i > 0) {
							// 自动登录
							WebUtil.getSession().setAttribute(SystemConstance.LOGIN_KEY, user);
							AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, null, "会员注册成功");
							result.putInMap("user", user);
							return result;
						} else {
							return new AjaxResult(AjaxResult.STATUS_ERR, null, "会员注册失败");
						}
					} else {
						return new AjaxResult(AjaxResult.STATUS_ERR, null, "手机号已注册");
					}
				}
			} else {
				return new AjaxResult(AjaxResult.STATUS_ERR, null, "手机号未验证");
			}
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "验证码已失效");
		}

	}

	public static AtomicInteger

	b = new AtomicInteger(0);

	public static void main(String[] args) {

		ExecutorService exec = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 500 * 10; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					b.set(b.get() + 1);
					System.out.println(Thread.currentThread()+"  count----->" + b.get());
					HttpUtils.sendGet("http://10.0.0.115:8080/mingyi-web/app", "");
				}
			});
		}

	}

}