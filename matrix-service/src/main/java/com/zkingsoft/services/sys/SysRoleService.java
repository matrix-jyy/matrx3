package com.zkingsoft.services.sys;

import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.model.sys.SysRole;
import java.util.List;
import com.zkingsoft.constraint.BaseServices;

/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-16 15:25
 */
public interface SysRoleService  extends BaseServices<SysRole>{
	
	/**
	 * 新增SysRole
	 * 
	 */
	public int add(SysRole sysRole);
   	
   	/**
	 * 批量新增SysRole
	 * 
	 */
	public int batchAdd(List<SysRole>  sysRoleList);
   	
   	/**
	 * 根据map键值对 更新SysRole
	 * 
	 */
	public int modifyByMap(SysRole oldSysRole ,SysRole newSysRole);
	
	/**
	 * 根据对象 更新SysRole
	 * 
	 */
	public int modifyByModel(SysRole sysRole);
	
	/**
	 * 批量删除SysRole
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除SysRole
	 * 
	 */
	public int removeById(Long roleId);
	
	/**
	 * 根据对象删除SysRole
	 * 
	 */
	public int removeByModel(SysRole sysRole);
	
	/**
	 * 分页查询SysRole
	 * 
	 */
	public List<SysRole> findInPage(SysRole sysRole, PaginationVO pageVo);

	/**
	 * 根据对象查询SysRole
	 * 
	 */
	public List<SysRole> findByModel(SysRole sysRole);
	
	/**
	 * 统计记录数SysRole
	 * 
	 */
	public int  findTotal(SysRole sysRole);
	
	/**
	 * 根据id查询SysRole
	 * 
	 */
	public SysRole  findById(Long roleId);

   	

  
}