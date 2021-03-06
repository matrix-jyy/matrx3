package com.zkingsoft.services.bus;

import com.zkingsoft.pojo.PaginationVO;
import java.util.List;
import com.zkingsoft.model.bus.BusParameterSettings;
import com.zkingsoft.constraint.BaseServices;

/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-28 16:55
 */
public interface BusParameterSettingsService  extends BaseServices<BusParameterSettings>{
	
	/**
	 * 新增BusParameterSettings
	 * 
	 */
	public int add(BusParameterSettings busParameterSettings);
   	
   	/**
	 * 批量新增BusParameterSettings
	 * 
	 */
	public int batchAdd(List<BusParameterSettings>  busParameterSettingsList);
   	
   	/**
	 * 根据map键值对 更新BusParameterSettings
	 * 
	 */
	public int modifyByMap(BusParameterSettings oldBusParameterSettings ,BusParameterSettings newBusParameterSettings);
	
	/**
	 * 根据对象 更新BusParameterSettings
	 * 
	 */
	public int modifyByModel(BusParameterSettings busParameterSettings);
	
	/**
	 * 批量删除BusParameterSettings
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除BusParameterSettings
	 * 
	 */
	public int removeById(Long paramId);
	
	/**
	 * 根据对象删除BusParameterSettings
	 * 
	 */
	public int removeByModel(BusParameterSettings busParameterSettings);
	
	/**
	 * 分页查询BusParameterSettings
	 * 
	 */
	public List<BusParameterSettings> findInPage(BusParameterSettings busParameterSettings, PaginationVO pageVo);

	/**
	 * 根据对象查询BusParameterSettings
	 * 
	 */
	public List<BusParameterSettings> findByModel(BusParameterSettings busParameterSettings);
	
	/**
	 * 统计记录数BusParameterSettings
	 * 
	 */
	public int  findTotal(BusParameterSettings busParameterSettings);
	
	/**
	 * 根据id查询BusParameterSettings
	 * 
	 */
	public BusParameterSettings  findById(Long paramId);

   	
 
  
}