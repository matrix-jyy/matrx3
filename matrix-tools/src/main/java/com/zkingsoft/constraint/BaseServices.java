package com.zkingsoft.constraint;

import java.util.List;

import com.zkingsoft.pojo.PaginationVO;

/**
 * 
 * @Description: 所有业务service的基础类
 * @author:姜友瑶
 * @date 2016年8月28日
 */
public interface BaseServices<T> {
	/**
	 * 新增记录
	 * 
	 */
	public int add(T obje);

	/**
	 * 更新记录
	 * 
	 */
	public int modifyByMap(T oldValue, T newValue);

	/**
	 * 批量删除记录
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除记录
	 * 
	 */
	public int removeById(Long id);

	/**
	 * 分页查询记录
	 * 
	 */
	public List<T> findInPage(T obje, PaginationVO pageVo);

	/**
	 * 根据对象查询记录
	 * 
	 */
	public List<T> findByModel(T obje);

	/**
	 * 统计记录数记录
	 * 
	 */
	public int findTotal(T obje);

	/**
	 * 根据id查询记录
	 * 
	 */
	public T findById(Long id);

}
