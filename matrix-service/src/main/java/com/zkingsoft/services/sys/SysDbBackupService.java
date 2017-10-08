package com.zkingsoft.services.sys;

import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.model.sys.SysDbBackup;
import java.util.List;
import com.zkingsoft.constraint.BaseServices;

/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-12-16 18:25
 */
public interface SysDbBackupService  extends BaseServices<SysDbBackup>{
	
	/**
	 * 新增SysDbBackup
	 * 
	 */
	public int add(SysDbBackup sysDbBackup);
   	
   	/**
	 * 批量新增SysDbBackup
	 * 
	 */
	public int batchAdd(List<SysDbBackup>  sysDbBackupList);
   	
   	/**
	 * 根据map键值对 更新SysDbBackup
	 * 
	 */
	public int modifyByMap(SysDbBackup oldSysDbBackup ,SysDbBackup newSysDbBackup);
	
	/**
	 * 根据对象 更新SysDbBackup
	 * 
	 */
	public int modifyByModel(SysDbBackup sysDbBackup);
	
	/**
	 * 批量删除SysDbBackup
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除SysDbBackup
	 * 
	 */
	public int removeById(Long buId);
	
	/**
	 * 根据对象删除SysDbBackup
	 * 
	 */
	public int removeByModel(SysDbBackup sysDbBackup);
	
	/**
	 * 分页查询SysDbBackup
	 * 
	 */
	public List<SysDbBackup> findInPage(SysDbBackup sysDbBackup, PaginationVO pageVo);

	/**
	 * 根据对象查询SysDbBackup
	 * 
	 */
	public List<SysDbBackup> findByModel(SysDbBackup sysDbBackup);
	
	/**
	 * 统计记录数SysDbBackup
	 * 
	 */
	public int  findTotal(SysDbBackup sysDbBackup);
	
	/**
	 * 根据id查询SysDbBackup
	 * 
	 */
	public SysDbBackup  findById(Long buId);

   	

  
}