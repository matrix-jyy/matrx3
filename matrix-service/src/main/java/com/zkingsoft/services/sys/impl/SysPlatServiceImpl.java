package com.zkingsoft.services.sys.impl;

import com.zkingsoft.services.sys.SysPlatService;
import com.zkingsoft.sqlUtil.ServiceUtil;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.model.sys.SysCompany;
import com.zkingsoft.model.sys.SysPlat;
import com.zkingsoft.dao.sys.SysPlatDao;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.zkingsoft.exception.GlobleException;
import com.zkingsoft.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-16 15:25
 */
@Service("sysPlatService")
public class  SysPlatServiceImpl implements    SysPlatService  {

	
	@Autowired
	private SysPlatDao sysPlatDao;
	@Autowired 
	private ServiceUtil serviceUtil;
	private final String tableName="sys_plat";
	@Override
	public int add(SysPlat sysPlat){
		if(serviceUtil.addCheckRepeat(tableName, "plat_code", sysPlat.getPlatCode())){
			throw new GlobleException("code已经存在");
		}
		return sysPlatDao.insert(sysPlat);
		
	}
	
	@Override
	public int batchAdd(List<SysPlat>  sysPlatList) {
		return sysPlatDao.batchInsert(sysPlatList);
	}
	
	
   	
    @Override
	public int modifyByMap(SysPlat oldSysPlat
	,SysPlat newSysPlat){
    	if(serviceUtil.updateCheckRepeat(tableName, "plat_code",newSysPlat.getPlatCode(),"plat_id", newSysPlat.getPlatId())){
			throw new GlobleException("code已经存在");
		}
		if (!ModelUtils.isModified(oldSysPlat, newSysPlat)) {
			return 1;
		}
		Map<String, Object> modifyMap=null;
		try {
			modifyMap = ModelUtils.comparePojo2Map(oldSysPlat, newSysPlat);
		} catch (Exception e) {
			throw new GlobleException("数据对比失败");
		}
		if (modifyMap.size() > 0) {
			modifyMap.put("platId", oldSysPlat.getPlatId());
			sysPlatDao.updateByMap(modifyMap);
		}
		return 1;
	}
	
	@Override
	public int modifyByModel(SysPlat sysPlat){
	
		return sysPlatDao.updateByModel(sysPlat);
	
	}
	
	
	
	@Override
	public int remove(List<Long> list){
	
		return sysPlatDao.deleteByIds(list);
	
	}

	@Override
	public int removeById(Long platId){
	
		return sysPlatDao.deleteById(platId);
	
	}
	
	@Override
	public int removeByModel(SysPlat sysPlat){
	
		return sysPlatDao.deleteByModel(sysPlat);
	
	}
	
	
	@Override
	public List<SysPlat> findInPage(SysPlat sysPlat,  PaginationVO pageVo){
	
		return sysPlatDao.selectInPage(sysPlat , pageVo);
	
	}
	
	@Override
	public List<SysPlat> findByModel(SysPlat sysPlat){
	
		return sysPlatDao.selectByModel(sysPlat);
	
	}
	
	@Override
	public int  findTotal(SysPlat sysPlat){
	
		return sysPlatDao.selectTotalRecord(sysPlat);
	
	}
	
	@Override
	public SysPlat  findById(Long platId){
	
		return sysPlatDao.selectById(platId);
	
	}

   	
	@Override
	public List<SysPlat> findByCompany(SysCompany company) {
		
		return sysPlatDao.selectByCompany(company);
	}
	
	
	
}