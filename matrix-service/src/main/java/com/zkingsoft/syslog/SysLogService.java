package com.zkingsoft.syslog;

import java.util.List;

import com.zkingsoft.pojo.PaginationVO;


public interface SysLogService  {
	
	/**
	 * 新增SysLog
	 * 
	 */
	public int add(SysLog sysLog);
   	
   	/**
	 * 更新SysLog
	 * 
	 */
	public int modify(SysLog sysLog);
	
	/**
	 * 批量删除SysLog
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除SysLog
	 * 
	 */
	public int removeById(Long id);
	
	/**
	 * 分页查询SysLog
	 * 
	 */
	public List<SysLog> findInPage(SysLog sysLog, PaginationVO pageVo);

	/**
	 * 根据对象查询SysLog
	 * 
	 */
	public List<SysLog> findByModel(SysLog sysLog);
	
	/**
	 * 统计记录数SysLog
	 * 
	 */
	public int  findTotal(SysLog sysLog);
	
	/**
	 * 根据id查询SysLog
	 * 
	 */
	public SysLog  findById(Long id);

   	

  
}