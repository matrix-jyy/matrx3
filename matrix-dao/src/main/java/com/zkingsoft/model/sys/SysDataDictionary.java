package com.zkingsoft.model.sys;

import java.io.Serializable;

import com.zkingsoft.anotations.Extend;
/**
 * This field was generated by Zking.software.Codegen.
 * @date 2016-11-16 15:25
 */
public class SysDataDictionary implements Serializable{
	@Extend
	private static final long serialVersionUID = 1L; 

	
	private Long  dicId;
			
	/**
	 * 数据值
	 */
	private String  dicName;
			
	
	/**
	 * 字典类型
	 */
	private String  dicCateId;
	/**
	 * 字典类型名
	 */
	private String  dicCateName;
	

	public Long getDicId() {
		return dicId;
	}
   	
   	public void setDicId(Long dicId) {
		this.dicId=dicId;
	}
   	

	public String getDicName() {
		return dicName;
	}
   	
   	public void setDicName(String dicName) {
		this.dicName=dicName;
	}
   	

	public String getDicCateId() {
		return dicCateId;
	}
   	
   	public void setDicCateId(String dicCateId) {
		this.dicCateId=dicCateId;
	}
   		
   	
	public String getDicCateName() {
		return dicCateName;
	}

	public void setDicCateName(String dicCateName) {
		this.dicCateName = dicCateName;
	}

	@Override
	public String toString() {
		return "{SysDataDictionary:{"
		+"dicId:"+dicId+","
		+"dicName:"+dicName+","
		+"dicCateId:"+dicCateId+","
		+"dicCateName:"+dicCateName+","
		+"}}";
	}


  
}