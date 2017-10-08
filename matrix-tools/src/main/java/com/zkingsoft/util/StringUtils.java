package com.zkingsoft.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 字符串操作类，转换数据类型，切割字符串,对象比较等操作
 * 
 * @author Ron
 * @createTime 2014.08.30
 */
public class StringUtils {
	// 替换字符串
	private static final String REPLACE_CHAR = "*";

	private static final int SAVA_CHAR_LENGTH = 4;

	private static final String EMPTY = "";

	private static final String NULL = "NULL";

	public static Logger log = Logger.getLogger(StringUtils.class);

	/**
	 * 将字符串中的某些值用指定字符代替
	 * 
	 * @param str
	 *            原始字符串
	 * @param saveLength
	 *            保留位数
	 * @param replaceChar
	 *            替换成的字符
	 * @return
	 */
	public static String replaceStr(String str, int saveLength, String replaceChar) {

		if (saveLength <= 0) {
			saveLength = SAVA_CHAR_LENGTH;
		}

		if (replaceChar == null) {
			replaceChar = REPLACE_CHAR;
		}

		if (isBlank(str) || str.length() < saveLength) {
			return "";
		}

		char[] chars = str.toCharArray();
		StringBuffer stb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if (i < (saveLength - 1) / 2 || (chars.length - i - 1) < (saveLength + 2) / 2) {
				stb.append(chars[i]);
			} else {
				stb.append(replaceChar);
			}
		}

		return stb.toString();
	}

	/**
	 * 将字符串转换为整形
	 * 
	 * @param str
	 * @return
	 */
	public static int stringToInt(String str) {

		if (StringUtils.isBlank(str)) {
			return 0;
		}
		try {
			return Integer.valueOf(str).intValue();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return 0;
		}
	}

	/**
	 * 是否为空，去除首尾空格后比较
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}

		if (EMPTY.equals(str.trim())) {
			return true;
		}

		return false;
	}

	/**
	 * 不为空，去除首尾空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {

		if (str == null) {
			return false;
		}

		if (EMPTY.equals(str.trim())) {
			return false;
		}

		return true;
	}

	/**
	 * 去除字符串首尾空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {

		if (isNotBlank(str)) {
			return str.trim();
		}

		return str;
	}

	/**
	 * 两个字符串是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(String a, String b) {

		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null) {
			if (a.equals(b)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 两个字符串是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean notEquals(String a, String b) {

		return !equals(a, b);
	}

	/**
	 * 忽略大小写后两个字符串是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equalsIgnoreCase(String a, String b) {

		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null) {
			if (a.toUpperCase().equals(b.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 忽略大小写后两个字符串是否相等
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean notEqualsIgnoreCase(String a, String b) {

		return !equalsIgnoreCase(a, b);
	}

	/**
	 * 用字符串split风格字符串str成数组
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String[] stringToArray(String str, String split) {

		if (isBlank(str)) {
			return null;
		}

		return str.split(split);
	}

	/**
	 * 如果值为null或者是空格则显示空
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {

		if (isBlank(str)) {
			return EMPTY;
		}

		return str.trim();
	}

	/**
	 * 字符串如果为空或空格则返回null
	 * 
	 * @param str
	 * @return
	 */
	public static String getNull(String str) {

		if (isNotBlank(str)) {
			return str;
		}

		return null;
	}

	/**
	 * 填充字符串到指定长度
	 * 
	 * @param source
	 *            原始字符串
	 * @param length
	 *            填充到长度
	 * @param chars
	 *            填充字符
	 * @param fillLeft
	 *            true-左填充，false-右填充
	 * @return
	 */
	public static String fillStringWithChar(String source, int length, char chars, boolean fillLeft) {

		if (StringUtils.isNotBlank(source) && length > 0) {
			int realLength = length - source.length();

			StringBuffer stb = new StringBuffer();
			for (; realLength > 0; realLength--) {
				stb.append(chars);
			}

			if (fillLeft) {
				return stb.toString() + source;
			} else {
				return source + stb.toString();
			}
		}
		return source;
	}

	public static int setOrder(String order) {

		if (StringUtils.isBlank(order) || "asc".equals(order))
			return 1;
		else
			return -1;
	}

	/**
	 * 去掉json字符串两头的中括号
	 * 
	 * @param jsonArrStr
	 * @return
	 */
	public static String getJsonObjStr(String jsonArrStr) {
		String tempString = jsonArrStr.trim();
		if (tempString.startsWith("[") && tempString.endsWith("]")) {
			return tempString.substring(1, tempString.length() - 1);
		} else {
			return tempString;
		}
	}

	private static final int MAX_GENERATE_COUNT = 99999;

	private static int generateCount = 0;

	public static synchronized String getUniqueString() {

		if (generateCount > MAX_GENERATE_COUNT)

			generateCount = 0;

		String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);

		generateCount++;

		return uniqueNumber;
	}

	public static synchronized String getUUIDString() {

		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(getUUIDString());
	}
	/**
	 * 是否为空
	 * 
	 * @param obj
	 * @return true:为空,false:不为空
	 */
	public static boolean isNull(Object obj) {
		if (obj == null || NULL.equals(obj) || NULL.toLowerCase().equals(obj))
			return true;
		return false;
	}

	/***
	 * 针对javascript encodeURIComponent编码后进行解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		try {
			return java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("StringUtils:", e);
		}
		return "";
	}

	public static String encodingUTF(String str) {

		if (str == null) {
			return null;
		}
		try {
			str = new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("StringUtils:", e);
		}
		return str;
	}

	/**
	 * @Description: 获得不重复的32位的随机的大写字符串
	 * @Return:String
	 * @Author: JiangYouYao
	 * @Version: V1.00 （版本号1.0)
	 * @Create Date: 2014-8-30（创建日期）
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 把一个集合转化成一个字符串，null或size为0时转化为空串
	 */
	public static String collToStr(Collection<?> collection, String splitPatten) {
		if (collection == null || collection.size() == 0) {
			return "";
		}
		Iterator<?> it = collection.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next().toString() + splitPatten);
		}
		int lastIndex = sb.lastIndexOf(splitPatten);
		return sb.substring(0, lastIndex);
	}

	/**
	 * 字符串转集合
	 * 
	 * @param sour
	 *            原字符串
	 * @param splitPatten
	 *            截取规则
	 * @return List<String>
	 */
	public static List<String> strToColl(String sour, String splitPatten) {
		List<String> list = new ArrayList<String>();
		if (sour != null) {
			String[] temps = sour.split(splitPatten);
			for (String string : temps) {
				list.add(string);
			}
		}
		return list;
	}

	/**
	 * 字符串转集合
	 * 
	 * @param sour
	 *            原字符串
	 * @param splitPatten
	 *            截取规则
	 * @return List<String>
	 */
	public static List<Long> strToCollToLong(String sour, String splitPatten) {
		List<Long> list = new ArrayList<Long>();
		if (sour != null) {
			String[] temps = sour.split(splitPatten);
			for (String string : temps) {
				list.add(Long.parseLong(string));
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: getRandomString @Description: TODO
	 *         获取一个获得一定长度的随机数,包含大写字母及数字 @author:罗凯 @param length @return @return
	 *         String 返回类型 @date 2016年7月19日 下午3:01:20 @throws
	 */
	public static String getRandomString(int length) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(str.length());
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 用逗号分开的存的id是否包含对应的id 如比较 123,123,124 中是否包含12这个主键 比对的方法是 字符串中是否包含"^12$"
	 * "^12,.*" 或者 ".+,12$" 或者 ".+,12,$" 或者 ".+,12,.+" 字符串
	 */
	public static boolean isContentSet(String id, String setStr) {
		id = id.trim();
		setStr = setStr.trim();
		String reg = "^" + id + "$|^" + id + ",.*|.+," + id + "$|.+," + id + ",$|.+," + id + ",.+";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(setStr);
		boolean result = matcher.matches();
		log.info("id=[" + id + "] :  setStr =[" + setStr + "] :result=" + result);
		return result;
	}

}
