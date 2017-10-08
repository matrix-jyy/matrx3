package com.zkingsoft.model.sys;

import com.zkingsoft.anotations.Extend;
import java.io.Serializable;
/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-24 19:06
 */
public class SysPlat implements Serializable{
	@Extend
	private static final long serialVersionUID = 1L; 

	
	private Long  platId;
			
	
	/**
	 * 平台名称
	 */
	private String  platName;
			
	
	/**
	 * 平台编号
	 */
	private String  platCode;
			
	
	/**
	 * 平台访问地址
	 */
	private String  platUrl;
			
	
	/**
	 * 公司id
	 */
	private Long  companyId;
			
	

	public Long getPlatId() {
		return platId;
	}
   	
   	public void setPlatId(Long platId) {
		this.platId=platId;
	}
   	

	public String getPlatName() {
		return platName;
	}
   	
   	public void setPlatName(String platName) {
		this.platName=platName;
	}
   	

	public String getPlatCode() {
		return platCode;
	}
   	
   	public void setPlatCode(String platCode) {
		this.platCode=platCode;
	}
   	

	public String getPlatUrl() {
		return platUrl;
	}
   	
   	public void setPlatUrl(String platUrl) {
		this.platUrl=platUrl;
	}
   	

	public Long getCompanyId() {
		return companyId;
	}
   	
   	public void setCompanyId(Long companyId) {
		this.companyId=companyId;
	}
   	

	@Override
	public String toString() {
		return "{SysPlat:{"
		+"platId:"+platId+","
		+"platName:"+platName+","
		+"platCode:"+platCode+","
		+"platUrl:"+platUrl+","
		+"companyId:"+companyId+","
		+"}}";
	}


  
}