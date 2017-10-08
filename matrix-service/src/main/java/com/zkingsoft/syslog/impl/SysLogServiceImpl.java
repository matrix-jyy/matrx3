package com.zkingsoft.syslog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.syslog.SysLog;
import com.zkingsoft.syslog.SysLogDao;
import com.zkingsoft.syslog.SysLogService;

/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-07-17 10:50
 */
@Service("sysLogServices")
public class  SysLogServiceImpl implements SysLogService  {

	
	@Autowired
	private SysLogDao sysLogDao;
	
	
	@Override
	public int add(SysLog sysLog){
		
		return sysLogDao.insert(sysLog);
		
	}
   	
    @Override
	public int modify(SysLog sysLog){
	
		return sysLogDao.update(sysLog);
	
	}
	
	@Override
	public int remove(List<Long> list){
	
		return sysLogDao.deleteByIds(list);
	
	}

	@Override
	public int removeById(Long id){
	
		return sysLogDao.deleteById(id);
	
	}
	
	@Override
	public List<SysLog> findInPage(SysLog sysLog,  PaginationVO pageVo){
	
		return sysLogDao.selectInPage(sysLog , pageVo);
	
	}
	
	@Override
	public List<SysLog> findByModel(SysLog sysLog){
	
		return sysLogDao.selectByModel(sysLog);
	
	}
	
	@Override
	public int  findTotal(SysLog sysLog){
	
		return sysLogDao.selectTotalRecord(sysLog);
	
	}
	
	@Override
	public SysLog  findById(Long id){
	
		return sysLogDao.selectById(id);
	
	}
	
}