package com.zkingsoft.authority.loginStrategy;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.services.sys.SysUsersService;
import com.zkingsoft.util.EncrypUtil;

public class AccountPasswordLogin implements LoginStrategy {

	private SysUsersService sysUsersService;
	private SysUsers user;
	

	public AccountPasswordLogin(SysUsers user, SysUsersService sysUsersService) {
		this.user=user;
		this.sysUsersService = sysUsersService;
	}

	@Override
	public Object login() {
		// 是否同时有账号和密码
		if (user.getSuAccount() != null && user.getSuPassword() != null) {
			// 加密密码
			try {
				user.setSuPassword(EncrypUtil.getMD5(user.getSuPassword()));
			} catch (UnsupportedEncodingException e) {
				throw new GlobleException("登录失败");
			}
			SysUsers userQuery = new SysUsers();
			userQuery.setSuAccount(user.getSuAccount());
			userQuery.setSuPassword(user.getSuPassword());
			List<SysUsers> users = sysUsersService.findByModel(userQuery);
			// 执行数据库查询
			if (users != null && users.size() > 0) {
				userQuery = users.get(0);
				
				
				
				// 账号状态判断
				if (userQuery.getSuValid().equals(SystemConstance.RECORD_VALID)) {

					if (userQuery.getSuAccountStatus() != null
							&& !userQuery.getSuAccountStatus().equals(SystemConstance.ACCOUNT_STATUS_INACTIVATED)) {
						// 激活状态
						if (userQuery.getSuAccountStatus().equals(SystemConstance.ACCOUNT_STATUS_ACTIVATE)) {
							// 登录成功
							return userQuery;
						} else if (userQuery.getSuAccountStatus().equals(SystemConstance.ACCOUNT_STATUS_LOCKED)) {
							throw new GlobleException("账号已经锁定");
						}
					} else {
						throw new GlobleException("账号未激活");
					}
				} else {
					throw new GlobleException("账号已被删除");
				}

			} else {
				throw new GlobleException("账号或密码错误");
			}
		} else {
		throw new GlobleException("请输入账号和密码");
		}
		return null;
	}

	@Override
	public boolean requireInitUserPower() {
		return true;
	}

}
