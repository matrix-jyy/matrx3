package com.zkingsoft.dao.bus;

import java.util.List;

import com.zkingsoft.model.bus.BusArea;

/**
 * 地区dao
 * 
 * @author jiangyouyao
 *
 */
public interface BusAreaDao {


	/**
	 * 根据父级地区查询子地区
	 * 
	 * @return
	 */
	public List<BusArea> selectChildrenArea(Long parentId);

}