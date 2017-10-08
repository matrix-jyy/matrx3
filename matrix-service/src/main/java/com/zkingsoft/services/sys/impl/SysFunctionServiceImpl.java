package com.zkingsoft.services.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkingsoft.authority.Matrix;
import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.dao.sys.SysBtnDao;
import com.zkingsoft.dao.sys.SysFunctionDao;
import com.zkingsoft.dao.sys.SysRolePwoerFnDao;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.model.sys.SysBtn;
import com.zkingsoft.model.sys.SysFunction;
import com.zkingsoft.model.sys.SysRolePwoerFn;
import com.zkingsoft.model.sys.SysUsers;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysFunctionService;
import com.zkingsoft.sqlUtil.ServiceUtil;
import com.zkingsoft.util.ModelUtils;
import com.zkingsoft.util.StringUtils;

/**
 * 系统功能管理
 * 
 * @author jiangyouyao
 * @time 2016-11-18
 */
@Service("sysFunctionService")
public class SysFunctionServiceImpl implements SysFunctionService {

	@Autowired
	private SysFunctionDao sysFunctionDao;
	@Autowired
	private SysRolePwoerFnDao rolePwoerFnDao;
	@Autowired
	private SysBtnDao sysBtnDao;
	@Autowired
	private ServiceUtil serviceUtil;

	@Resource
	private Matrix matrix;

	private String tableName = "sys_function";

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public int add(SysFunction sysFunction) {
		// 自己不能是自己的父节点
		if (sysFunction.getFnParentId() != null && sysFunction.getFnParentId().equals(sysFunction.getFnId())) {
			throw new GlobleException("功能的父级不能是自己");
		}
		// 功能code去重
		if (serviceUtil.addCheckRepeat(tableName, "fn_code", sysFunction.getFnCode())) {
			throw new GlobleException("code已经存在！");
		}
		// 设置功能等级
		setFunctionGrade(sysFunction);
		sysFunction.setFnIsDisable(SystemConstance.IS_N);
		return sysFunctionDao.insert(sysFunction);

	}

	private void setFunctionGrade(SysFunction sysFunction) {
		if (sysFunction.getFnParentId() == null) {
			sysFunction.setFnGrade(1);
		} else {
			if (sysFunction.getFnParentId() != null) {
				SysFunction parent = sysFunctionDao.selectById(sysFunction.getFnParentId());
				sysFunction.setFnGrade(parent.getFnGrade() + 1);
			} else {
				sysFunction.setFnGrade(1);
			}
		}
	}

	@Override
	public int batchAdd(List<SysFunction> sysFunctionList) {
		return sysFunctionDao.batchInsert(sysFunctionList);
	}

	@Transactional
	@Override
	public int modifyByMap(SysFunction oldSysFunction, SysFunction newSysFunction) {
		// 自己不能是自己的父节点
		if (newSysFunction.getFnParentId() != null && newSysFunction.getFnParentId().equals(newSysFunction.getFnId())) {
			throw new GlobleException("功能的父级不能是自己");
		}
		// 功能code去重
		if (serviceUtil.updateCheckRepeat(tableName, "fn_code", newSysFunction.getFnCode(), "fn_id",
				newSysFunction.getFnId())) {
			throw new GlobleException("code已经存在！");
		}
		// 设置功能等级
		setFunctionGrade(newSysFunction);
		// 如果功能的按钮发生改变则需要改变所有的角色的按钮
		if ((oldSysFunction.getFnBtns() == null && newSysFunction.getFnBtns() != null)
				|| (newSysFunction.getFnBtns() == null && oldSysFunction.getFnBtns() != null)
				|| (oldSysFunction.getFnBtns() != null
						&& !oldSysFunction.getFnBtns().equals(newSysFunction.getFnBtns()))) {
			updateRoleBtns(newSysFunction);
		}

		if (!ModelUtils.isModified(oldSysFunction, newSysFunction)) {
			return 1;
		}
		Map<String, Object> modifyMap = null;
		try {
			modifyMap = ModelUtils.comparePojo2Map(oldSysFunction, newSysFunction);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobleException("数据对比失败");
		}
		if (modifyMap.size() > 0) {
			modifyMap.put("fnId", oldSysFunction.getFnId());
			sysFunctionDao.updateByMap(modifyMap);
		}
		return 1;
	}

	/**
	 * 更新所有角色的按钮权限
	 * 
	 * @param newSysFunction
	 */
	private void updateRoleBtns(SysFunction newSysFunction) {
		SysRolePwoerFn sysRolePwoerFn = new SysRolePwoerFn();
		sysRolePwoerFn.setFnId(newSysFunction.getFnId());
		Map<String, Object> keys = new HashMap<>();
		// 获得所有具有改功能的权限
		List<SysRolePwoerFn> pwoerList = rolePwoerFnDao.selectByModel(sysRolePwoerFn);
		for (SysRolePwoerFn tempPwoer : pwoerList) {
			keys.put("rpfId", tempPwoer.getRpfId());
			if (newSysFunction.getFnBtns() == null) {
				keys.put("rpfBtns", null);
				rolePwoerFnDao.updateByMap(keys);
			} else if (tempPwoer.getRpfBtns() != null) {
				// 都不为空则进行比较，把多的btn删除
				StringBuilder resultbtn = new StringBuilder();
				String[] btn = tempPwoer.getRpfBtns().split(",");
				for (String temp : btn) {
					if (StringUtils.isContentSet(temp, newSysFunction.getFnBtns())) {
						if (resultbtn.length() == 0) {
							resultbtn.append(temp);
						} else {
							resultbtn.append("," + temp);
						}
					}
				}
				keys.put("rpfBtns", resultbtn.toString());
				rolePwoerFnDao.updateByMap(keys);
			}
		}
	}

	@Override
	public int modifyByModel(SysFunction sysFunction) {

		return sysFunctionDao.updateByModel(sysFunction);

	}

	@Override
	public int remove(List<Long> list) {

		return sysFunctionDao.deleteByIds(list);

	}

	/**
	 * 删除功能和功能下的子功能
	 * 
	 * @param fnId
	 * @return
	 */
	@Override
	public int removeById(Long fnId) {
		if (fnId == null) {
			return 0;
		}
		List<Long> ids = new ArrayList<>();
		ids = getFunctionChirds(fnId, new ArrayList<Long>());
		return sysFunctionDao.deleteByIds(ids);
	}

	@Override
	public int removeByModel(SysFunction sysFunction) {

		return sysFunctionDao.deleteByModel(sysFunction);

	}

	@Override
	public List<SysFunction> findInPage(SysFunction sysFunction, PaginationVO pageVo) {

		return sysFunctionDao.selectInPage(sysFunction, pageVo);

	}

	@Override
	public List<SysFunction> findByModel(SysFunction sysFunction) {

		return sysFunctionDao.selectByModel(sysFunction);

	}

	@Override
	public int findTotal(SysFunction sysFunction) {

		return sysFunctionDao.selectTotalRecord(sysFunction);

	}

	@Override
	public SysFunction findById(Long fnId) {

		return sysFunctionDao.selectById(fnId);

	}

	/**
	 * 获取功能下的子功能id，包含功能自己的id
	 * 
	 * @param id
	 * @param ids
	 * @return
	 */
	private List<Long> getFunctionChirds(Long id, List<Long> ids) {
		SysFunction fnquery = new SysFunction();
		fnquery.setFnParentId(id);
		// 获取子节点
		List<SysFunction> list = sysFunctionDao.selectByModel(fnquery);
		if (!list.isEmpty()) {
			for (SysFunction function : list) {
				// 删除子节点
				getFunctionChirds(function.getFnId(), ids);
			}
		}
		ids.add(id);
		return ids;

	}

	@Override
	public void setIsDisable(Long fnId, String status) {
		SysFunction dbFunction = sysFunctionDao.selectForUpdate(fnId);
		if (status.equals(SystemConstance.IS_Y)) {
			// 启用功能
			if (dbFunction.getFnIsDisable().equals(SystemConstance.IS_Y)) {
				throw new GlobleException("功能已经是启用状态");
			} else {

				List<Long> list = getFunctionChirds(fnId, new ArrayList<Long>());
				sysFunctionDao.batchChangeStatu(SystemConstance.IS_Y, list);
			}
		} else {
			// 禁用功能
			if (dbFunction.getFnIsDisable().equals(SystemConstance.IS_N)) {
				throw new GlobleException("功能已是禁用状态");
			} else {

				// 启用子节点,和父节点，因为目前只考虑2级的情况先写死吧
				List<Long> ids = new ArrayList<Long>();
				if (dbFunction.getFnParentId() != null) {
					ids.add(dbFunction.getFnParentId());
				}
				ids = getFunctionChirds(fnId, ids);
				sysFunctionDao.batchChangeStatu(SystemConstance.IS_N, ids);

			}
		}

	}

	@Override
	public List<SysFunction> findCompanyFunction(Long companyId) {
		return sysFunctionDao.selectCompanyFunction(companyId);
	}

	@Override
	public List<SysFunction> findFunctionByRoleIds(String roleIds) {
		return sysFunctionDao.selectFunctionByRoleIds(roleIds);
	}

	@Override
	public List<SysFunction> findRoleFuntion(Long id) {
		// 获取所有的功能--》获取所有的按钮--》对于功能的按钮|构建功能的父子关系--》
		Map<Long, SysFunction> userFnMap = new HashMap<>();
		if (id != null) {// id为空则不需要查询选中项
			// 把用户所有的功能变成map
			List<SysFunction> editorFunction = sysFunctionDao.selectFunctionByRoleIds(id + "");
			for (SysFunction temp : editorFunction) {
				userFnMap.put(temp.getFnId(), temp);
			}
		}
		// 只查询出没有被禁用的
		SysUsers user = (SysUsers) matrix.getLoginUser();
		List<SysFunction> allFunction = sysFunctionDao.selectCompanyFunction(user.getCompanyId());// 只能查本公司的
		Map<Long, SysFunction> fnMap = new TreeMap<>();
		Map<String, SysBtn> btnMap = new HashMap<>();
		List<SysBtn> btns = sysBtnDao.selectByModel(null);
		for (SysBtn sysBtn : btns) {
			btnMap.put(sysBtn.getBtnValue(), sysBtn);
		}
		for (SysFunction sysFunction : allFunction) {

			// 用一个map记录下每一个功能
			fnMap.put(sysFunction.getFnId(), sysFunction);
			// 处理按钮
			if (sysFunction.getFnBtns() == null) {
				continue;
			}
			if (sysFunction.getSysBtns() == null) {
				sysFunction.setSysBtns(new ArrayList<SysBtn>());
			}
			String[] btnstr = sysFunction.getFnBtns().split(",");
			for (String btnValue : btnstr) {
				SysBtn temp = btnMap.get(btnValue);
				if (temp != null) {
					sysFunction.getSysBtns().add(temp);
				}
			}
			// 清空功能的按钮字符串，因为界面要用字符串来比较是否已经具有本按钮
			sysFunction.setFnBtns("");
		}
		// 构建树形关系
		allFunction = new ArrayList<>();
		Set<Long> keys = fnMap.keySet();
		for (Long fnId : keys) {
			SysFunction function = fnMap.get(fnId);
			// id为空则不需要查询选中项
			if (id != null && userFnMap.containsKey(fnId)) {
				function.setHasThisFn(true);
				// 构建按钮字符串用于界面进选中
				function.setFnBtns(userFnMap.get(fnId).getFnBtns());
			}
			// 如果是一级节点则直接存入菜单
			if (function.getFnParentId() == null) {
				allFunction.add(function);
			} else {
				// 非一级节点找到父节点后存入
				SysFunction parentFn = fnMap.get(function.getFnParentId());
				List<SysFunction> childs = parentFn.getChilds();
				if (childs == null) {
					parentFn.setChilds(new ArrayList<SysFunction>());
				}
				parentFn.getChilds().add(function);
			}
		}
		log.info(allFunction);
		return allFunction;
	}

}