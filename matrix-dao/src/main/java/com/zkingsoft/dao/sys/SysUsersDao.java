package com.zkingsoft.dao.sys;

import org.apache.ibatis.annotations.Param;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.model.sys.SysUsers;
import java.util.Map;
import java.util.List;


/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-16 15:25
 */


public interface SysUsersDao{

	public int insert(SysUsers sysUsers);
   	
   	public int batchInsert(@Param("list") List<SysUsers> sysUsersList);
   	
	public int updateByMap(Map<String, Object> modifyMap);
	
	public int updateByModel(SysUsers sysUsers);
	
	public int deleteByIds(@Param("list") List<Long> list);
	
	public int deleteById(Long suId);

	public int deleteByModel(@Param("record") SysUsers sysUsers);
	
	public List<SysUsers> selectInPage(@Param("record") SysUsers sysUsers, @Param("pageVo") PaginationVO pageVo);

	public List<SysUsers> selectByModel(@Param("record") SysUsers sysUsers);
	
	public int  selectTotalRecord(@Param("record") SysUsers sysUsers);
	
	public SysUsers  selectById(Long suId);
	
	public SysUsers  selectForUpdate(Long suId);
	
}