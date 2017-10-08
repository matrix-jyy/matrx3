package com.zkingsoft.sqlUtil;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkingsoft.dao.sys.UtilDao;


/**
 * 
* @Description: 提供一些特殊的sql操作
* @author:姜友瑶 
* @date 2016年8月28日
 */
@Service("ServiceUtil")
public class ServiceUtil {
	@Autowired
	private UtilDao utilDao;

	/**
	 * 单字段判断去重
	 * ß
	 * @author jyy
	 * @time 2016-07-31
	 * @param tableName
	 *            表明
	 * @param column
	 *            字段名
	 * @param value
	 *            字段值
	 * @return
	 */
	public boolean addCheckRepeat(String tableName, String column, Object value) {
		HashMap<String, Object> query = excuteQuery(tableName, column, value);
		if (query != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 双字段去重
	 * 
	 * @author jyy
	 * @time 2016-07-31
	 * @param tableName
	 *            表名
	 * @param column1
	 *            字段名1
	 * @param value1
	 *            字段名1的值
	 * @param column2
	 *            字段名2
	 * @param value2
	 *            字段名2的值
	 * @return
	 */
	public boolean addCheckRepeatTowColumn(String tableName, String column1, Object value1, String column2,
			Object value2) {
		HashMap<String, Object> query = excuteTow(tableName, column1, value1, column2, value2);
		if (query != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 双字段去重 更新时调用
	 * 
	 * @author jyy
	 * @time 2016-07-31
	 * @param tableName
	 * @param column1
	 * @param value1
	 * @param column2
	 * @param value2
	 * @param idName
	 *            数据库id名称
	 * @param idValue
	 *            更新数据的id
	 * @return
	 */
	public boolean updateCheckRepeatTowColumn(String tableName, String column1, Object value1, String column2,
			Object value2, String idName, Object idValue) {
		HashMap<String, Object> query = excuteTow(tableName, column1, value1, column2, value2);
		if (query != null && !query.get(idName).equals(idValue)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 单字段去重 更新时调用
	 * 
	 * @author jyy
	 * @time 2016-07-31
	 * @param tableName
	 * @param column
	 * @param value
	 * @param idName
	 *            数据库id名称
	 * @param idValue
	 *            更新数据的id
	 * @return
	 */
	public boolean updateCheckRepeat(String tableName, String column, Object value, String idName, Object idValue) {
		HashMap<String, Object> query = excuteQuery(tableName, column, value);
		if (query != null && !query.get(idName).equals(idValue)) {
			return true;
		} else {
			return false;
		}
	}

	private HashMap<String, Object> excuteQuery(String tableName, String column, Object value) {
		HashMap<String, Object> query = new HashMap<>();
		query.put("tableName", tableName);
		query.put("column", column);
		query.put("value", value);
		query = utilDao.selectRepeat(query);
		return query;
	}

	private HashMap<String, Object> excuteTow(String tableName, String column1, Object value1, String column2,
			Object value2) {
		HashMap<String, Object> query = new HashMap<>();
		query.put("tableName", tableName);
		query.put("column1", column1);
		query.put("value1", value1);
		query.put("column2", column2);
		query.put("value2", value2);
		query = utilDao.selectRepeatTowColumn(query);
		return query;
	}

	/**
	 * 获取一个表中,其中一个字段的最大值 @Title: getMaxValue @Description:
	 * TODO @author:罗凯 @param tableName @param column @return String 返回类型 @date
	 * 2016年8月22日 上午10:51:03 @throws
	 */
	public String getMaxValue(String tableName, String column) {
		String max = utilDao.selectMaxValue(tableName, column);
		return max;
	}

	/**
	 * 根据0001 的自增算法计算出要设置的值,编号会超过1000的不要用该方法。 @Title: getMaxValue @Description:
	 * TODO @author:罗凯 @param tableName @param column @return String 返回类型 @date
	 * 2016年8月22日 上午10:51:03 @throws
	 */
	public String getMaxAddNo(String tableName, String column) {
		String max = utilDao.selectMaxValue(tableName, column);
		if (max == null || max.equals("")) {
			max = "0001";
		}
		int s = Integer.parseInt(max);
		s = s + 1;
		String reslut = s > 10 ? (s > 100 ? s + "" : "00" + s) : "000" + s; // 计算
																			// 转型
		return reslut;
	}
}
