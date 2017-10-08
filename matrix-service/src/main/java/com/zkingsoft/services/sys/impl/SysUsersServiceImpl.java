package com.zkingsoft.services.sys.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkingsoft.dao.sys.SysUsersDao;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysUsersService;
import com.zkingsoft.sqlUtil.ServiceUtil;
import com.zkingsoft.util.EncrypUtil;
import com.zkingsoft.util.ModelUtils;

/**
 * This field was generated by Zking.software.Codegen.
 * 
 * @date 2016-11-16 15:25
 */
@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService {

	@Autowired
	private SysUsersDao sysUsersDao;
	@Autowired
	private ServiceUtil serviceUtil;
	private final String tableName = "sys_users";

	@Override
	public int add(SysUsers sysUsers) {
		// 判断账号是否重复
		if (serviceUtil.addCheckRepeat(tableName, "su_account", sysUsers.getSuAccount())) {
			throw new GlobleException("账号已经被占用");
		}
		try {
			// 密码加密
			sysUsers.setSuPassword(EncrypUtil.getMD5(sysUsers.getSuPassword()));
		} catch (UnsupportedEncodingException e) {
			throw new GlobleException(e.getMessage());
		}
		return sysUsersDao.insert(sysUsers);

	}

	@Override
	public int batchAdd(List<SysUsers> sysUsersList) {
		return sysUsersDao.batchInsert(sysUsersList);
	}

	@Override
	public int modifyByMap(SysUsers oldSysUsers, SysUsers newSysUsers) {
		// 判断账号是否重复
		if (serviceUtil.updateCheckRepeat(tableName, "su_account", newSysUsers.getSuAccount(), "su_id", newSysUsers.getSuId())) {
			throw new GlobleException("账号已经被占用");
		}
		if (!ModelUtils.isModified(oldSysUsers, newSysUsers)) {
			return 1;
		}
		Map<String, Object> modifyMap = null;
		try {
			modifyMap = ModelUtils.comparePojo2Map(oldSysUsers, newSysUsers);
		} catch (Exception e) {
			throw new GlobleException("数据对比失败");
		}
		if (modifyMap.size() > 0) {
			modifyMap.put("suId", oldSysUsers.getSuId());
			sysUsersDao.updateByMap(modifyMap);
		}
		return 1;
	}

	@Override
	public int modifyByModel(SysUsers sysUsers) {

		return sysUsersDao.updateByModel(sysUsers);

	}

	@Override
	public int remove(List<Long> list) {

		return sysUsersDao.deleteByIds(list);

	}

	@Override
	public int removeById(Long suId) {

		return sysUsersDao.deleteById(suId);

	}

	@Override
	public int removeByModel(SysUsers sysUsers) {

		return sysUsersDao.deleteByModel(sysUsers);

	}

	@Override
	public List<SysUsers> findInPage(SysUsers sysUsers, PaginationVO pageVo) {

		return sysUsersDao.selectInPage(sysUsers, pageVo);

	}

	@Override
	public List<SysUsers> findByModel(SysUsers sysUsers) {

		return sysUsersDao.selectByModel(sysUsers);

	}

	@Override
	public int findTotal(SysUsers sysUsers) {

		return sysUsersDao.selectTotalRecord(sysUsers);

	}

	@Override
	public SysUsers findById(Long suId) {

		return sysUsersDao.selectById(suId);

	}
	
	@Override
	public boolean isOnlyPhone(String phone) {
		return serviceUtil.addCheckRepeat(tableName, "su_tel", phone);
	}
	

}