package com.zkingsoft.pojo;

/**
 * @author 姜友瑶 E-mail:<935090232@qq.com>
 * @date 2016年6月24日
 * @description 分页信息类
 */
public class PaginationVO {

	/**
	 * 起点
	 */
	private Integer offset = 0;
	/**
	 * 查询条数
	 */
	private Integer limit = 10;

	/**
	 * 排序方式
	 */
	private String order;
	/**
	 * 排序字段
	 */
	private String sort;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return propertyToColumn(sort);
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String propertyToColumn(String property) {
		if(property==null)
			return null;
		StringBuffer columnName = new StringBuffer(property);
		for (int i = 0; i < columnName.length(); i++) {
			if (columnName.charAt(i) >= 'A' && columnName.charAt(i) <= 'Z') {
				String upcase = "_" + (columnName.charAt(i) + "").toLowerCase();
				columnName.replace(i, i + 1, upcase);
			}
		}
		return columnName.toString();
	}

	
}
