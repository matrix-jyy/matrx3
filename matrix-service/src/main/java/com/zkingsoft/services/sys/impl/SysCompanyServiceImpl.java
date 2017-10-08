package com.zkingsoft.services.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkingsoft.dao.sys.SysCompanyDao;
import com.zkingsoft.dao.sys.SysRoleDao;
import com.zkingsoft.dao.sys.SysRolePwoerFnDao;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.model.sys.SysCompany;
import com.zkingsoft.model.sys.SysRole;
import com.zkingsoft.model.sys.SysRolePwoerFn;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysCompanyService;
import com.zkingsoft.util.ModelUtils;
import com.zkingsoft.util.StringUtils;

/**
 * This field was generated by Zking.software.Codegen.
 * 
 * @date 2016-11-16 15:25
 */
@Service("sysCompanyService")
public class SysCompanyServiceImpl implements SysCompanyService {

	@Autowired
	private SysCompanyDao sysCompanyDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRolePwoerFnDao rolePwoerFnDao;

	@Override
	public int add(SysCompany sysCompany) {

		return sysCompanyDao.insert(sysCompany);

	}

	@Override
	public int batchAdd(List<SysCompany> sysCompanyList) {
		return sysCompanyDao.batchInsert(sysCompanyList);
	}

	@Override
	public int modifyByMap(SysCompany oldSysCompany, SysCompany newSysCompany) {

		List<Long> oldFunctions = null;
		String functions = sysCompanyDao.selectById(newSysCompany.getComId()).getComFunctions();
		if (functions == null || functions.equals("")) {
			oldFunctions = new ArrayList<>();
		} else {
			oldFunctions = StringUtils
					.strToCollToLong(sysCompanyDao.selectById(newSysCompany.getComId()).getComFunctions(), ",");
		}
		List<Long> newFunctions = new ArrayList<>();
		if (newSysCompany.getComFunctions() != null) {
			newFunctions = StringUtils.strToCollToLong(newSysCompany.getComFunctions(), ",");
		}

		for (Long old : oldFunctions) {
			// 如果新权限中不包含这个老的功能，则要更新改企业下所有的角色权限
			if (!newFunctions.contains(old)) {
				SysRole role = new SysRole();
				role.setCompanyId(newSysCompany.getComId());
				List<SysRole> roles = sysRoleDao.selectByModel(role);
				// 获取该公司下所有的权限信息
				List<SysRolePwoerFn> compalyRolePwoer = new ArrayList<>();
				for (SysRole tempRole : roles) {
					SysRolePwoerFn r = new SysRolePwoerFn();
					r.setRoleId(tempRole.getRoleId());
					compalyRolePwoer.addAll(rolePwoerFnDao.selectByModel(r));
				}
				// 记录要删除的id
				List<Long> delRolePwoer = new ArrayList<>();
				for (SysRolePwoerFn rolePwoer : compalyRolePwoer) {
					// 如果有一个老权限则删除这个权限
					if (rolePwoer.getFnId() != null && rolePwoer.getFnId().equals(old)) {
						delRolePwoer.add(rolePwoer.getRpfId());
					}
				}
				// 如果获取到了就删除
				if (delRolePwoer.size() > 0) {
					rolePwoerFnDao.deleteByIds(delRolePwoer);
				}
			}
		}
		if (!ModelUtils.isModified(oldSysCompany, newSysCompany)) {
			return 1;
		}
		Map<String, Object> modifyMap = null;
		try {
			modifyMap = ModelUtils.comparePojo2Map(oldSysCompany, newSysCompany);
		} catch (Exception e) {
			throw  new GlobleException("数据对比失败");
		}
		if (modifyMap.size() > 0) {
			modifyMap.put("comId", oldSysCompany.getComId());
			sysCompanyDao.updateByMap(modifyMap);
		}
		return 1;
	}

	@Override
	public int modifyByModel(SysCompany sysCompany) {

		return sysCompanyDao.updateByModel(sysCompany);

	}

	@Override
	public int remove(List<Long> list) {

		return sysCompanyDao.deleteByIds(list);

	}

	@Override
	public int removeById(Long comId) {

		return sysCompanyDao.deleteById(comId);

	}

	@Override
	public int removeByModel(SysCompany sysCompany) {

		return sysCompanyDao.deleteByModel(sysCompany);

	}

	@Override
	public List<SysCompany> findInPage(SysCompany sysCompany, PaginationVO pageVo) {

		return sysCompanyDao.selectInPage(sysCompany, pageVo);

	}

	@Override
	public List<SysCompany> findByModel(SysCompany sysCompany) {

		return sysCompanyDao.selectByModel(sysCompany);

	}

	@Override
	public int findTotal(SysCompany sysCompany) {

		return sysCompanyDao.selectTotalRecord(sysCompany);

	}

	@Override
	public SysCompany findById(Long comId) {

		return sysCompanyDao.selectById(comId);

	}

}