package com.zkingsoft.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description Ajax请求返回的结果对象
 * @author 姜友瑶
 * @data 2016-06-26
 */
public class AjaxResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 可以提示给用户看的错误
	 */
	public static final String STATUS_ERR = "err";
	/**
	 * 系统未知错误
	 */
	public static final String STATUS_UNKWON_ERR = "unkwonerr";
	/**
	 * 请求成功
	 */
	public static final String STATUS_OK = "ok";
	/**
	 * 请求状态是否成功 STATUS_ERR = "err" 失败 STATUS_OK = "ok" 成功
	 */
	private String status;
	/**
	 * 请求后跳转的页面
	 */
	private String page="";
	private String info="";
	private Map<Object, Object> mapInfo = new HashMap<>();
	private List<?> rows= new ArrayList<Object>();
	/**
	 * 总记录数
	 */
	private Integer total;

	public AjaxResult() {
	}

	public AjaxResult(String status, String page, String info, List<?> rows, Integer total) {
		this.status = status;
		this.page = page;
		this.info = info;
		this.rows = rows;
		this.total = total;

	}

	public AjaxResult(String status, String info, List<?> rows, Integer total) {
		this.status = status;
		this.info = info;
		this.rows = rows;
		this.total = total;
	}

	public AjaxResult(String status, List<?> rows, Integer total) {
		this.status = status;
		this.rows = rows;
		this.total = total;
	}

	/**
	 * 
	 * @Description: 在map对象中放置信息
	 * @author:姜友瑶
	 * @param key
	 * @param value
	 *            返回类型 void
	 * @date 2016年9月11日
	 */
	public void putInMap(Object key, Object value) {
		mapInfo.put(key, value);
	}

	/**
	 * 设置简单信息，这是一个便捷的方法
	 * 
	 * @param status
	 * @param page
	 * @param info
	 */
	public AjaxResult(String status, List<?> rows) {
		this.status = status;
		this.rows = rows;
	}

	/**
	 * 设置简单信息，这是一个便捷的方法
	 * 
	 * @param status
	 * @param page
	 * @param info
	 */
	public AjaxResult(String status, String page, String info) {
		this.status = status;
		this.page = page;
		this.info = info;
	}

	public AjaxResult(String status, String info) {
		this.status = status;
		this.info = info;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Map<Object, Object> getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(Map<Object, Object> mapInfo) {
		this.mapInfo = mapInfo;
	}

}
