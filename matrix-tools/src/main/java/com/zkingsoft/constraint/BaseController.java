package com.zkingsoft.constraint;

import java.util.List;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.util.StringUtils;
import com.zkingsoft.util.WebUtil;
/**
 * @description 除了特殊的action一般业务action都继承这个action来实现
 * 基本增删改查功能
 * @author 姜友瑶
 * @email 935090232@qq.com
 * @date 2016-06-26
 */
public abstract class BaseController {

	public <T> AjaxResult add(BaseServices<T> baseServices, T t, String name) {
		int i = baseServices.add(t);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, name + "添加成功");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, name + "添加失败");
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public <T> AjaxResult modify(BaseServices<T> baseServices, Object oldValue, T newValue,String name) {
		int i = baseServices.modifyByMap((T)oldValue,newValue);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, name +"修改成功");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, name +"修改失败");
		}
	}
	
	

	public <T> AjaxResult remove(BaseServices<T> baseServices, String keys) {
		List<Long> ids = StringUtils.strToCollToLong(keys, ",");
		int i = baseServices.remove(ids);
		if (i > 0) {
			return new AjaxResult(AjaxResult.STATUS_OK, null, "成功删除" + i + "条数据");
		} else {
			return new AjaxResult(AjaxResult.STATUS_ERR, null, "删除失败");
		}
	}

	public <T> AjaxResult showList(BaseServices<T> baseServices, T t, PaginationVO pageVo) {
		if (pageVo == null) {
			pageVo = new PaginationVO();
		}
		List<T> dataList = baseServices.findInPage(t, pageVo);
		AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, null, null, dataList, baseServices.findTotal(t));
		return result;
	}

	public <T> AjaxResult findByModel(BaseServices<T> baseServices, T t) {
		AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, null, null, baseServices.findByModel(t), 0);
		return result;
	}

	public <T> T findById(BaseServices<T> baseServices, Long id) {
		return baseServices.findById(id);
	}

	
	/**
	 * 获取登录员工登录对象
	 * @author 姜友瑶
	 * @date 2016/7/5
	 */
	@SuppressWarnings("unchecked")
	public <T> T  getSessionUser() {
		return (T) WebUtil.getSessionAttribute(SystemConstance.LOGIN_KEY);
	}

}
