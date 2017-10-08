package com.zkingsoft.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 日期格式化，转换工具类
 * 
 * @author Ron
 * @createTime 2014.08.30
 */
public class DateUtils {

    public static Logger log = Logger.getLogger(DateUtils.class);

    public static final String DATE_FORMAT_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_MONGO = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String DATE_FORMAT_STS = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String DATE_FORMAT_MM = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_HH = "yyyy-MM-dd HH";

    public static final String DATE_FORMAT_DD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";
    
    public static final String DATE_FORMAT_SPLITE_DD = "yyyy.MM.dd";

    public static final String DATE_FORMAT_NO_SPLITE_DD = "yyyyMMdd";

    public static final String DATE_FORMAT_MM_NO_DD = "yyyyMM";

    public static final String DATE_FORMAT_NO_SPLITE_MM = "yyyyMMddHHmm";
    
    public static final String DATE_FORMAT_NO_SPLITE_MM_HH = "yyyyMMddHH";

    public static final String YEAR = "yyyy";

    public static final String DATE_FORMAT_MMDD = "M月d日";

    public static final String DATE_FORMAT_WEEK = "星期";

    public static final String DATE_TIME_MORNING = "早上";

    public static final String DATE_TIME_AFTERNOON = "下午";

    public static final String DATE_TIME_NIGHT = "晚上";

    public static final String CENTRE_SCRIBING = "-";

    protected static final String EMPTY = "";

    protected static final String ZERO = "0";

    protected static final String SPLITE_CHAR = ":";

    protected static final String START_TIME = " 00:00:00";// 空格不能删除

    protected static final String END_TIME = " 23:59:59";// 空格不能删除

    protected static final int WEEK_DAYS = 7;

    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String HH_mm = "HH:mm";
    
    protected static final String[] weeks = { "一", "二", "三", "四", "五", "六", "日" };

    /**
     * 返回年份
     * 
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(java.util.Date date) {

        try {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.setTime(date);
            return c.get(java.util.Calendar.YEAR);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return 0;
    }

    /**
     * 返回月份
     * 
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(java.util.Date date) {

        try {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.setTime(date);
            return c.get(java.util.Calendar.MONTH) + 1;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return 0;
    }
    /**
     * 
    * @Title: getMonth 
    * @Description: TODO date 转换成年月日时分秒的String类型数据
    * @author:罗凯
    * @param date 
    * @return String    返回类型 
    * @date 2016年7月19日 下午2:18:36 
    * @throws
     */
    public static String getDateString(java.util.Date date) {
    	DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
    	return fmt.format(date);
    }
    /**
     * 日期转字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {

        if (date == null) {
            return EMPTY;
        }
        DateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /**
     * 字符串转日期
     * 
     * @param dateStr
     * @param format
     * @return
     */
    public static Date stringToDate(String dateStr, String format) {

        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format)) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 比较两个日期是否一致
     * 
     * @param dateA
     * @param dateB
     * @return
     */
    public static boolean equals(Date dateA, Date dateB) {

        if (dateA == null || dateB == null) {
            return false;
        }
        String strA = dateToString(dateA, DATE_FORMAT_DD);
        String strB = dateToString(dateB, DATE_FORMAT_DD);

        if (StringUtils.equals(strA, strB)) {
            return true;
        }

        return false;
    }

    /**
     * 比较两个日期是否一致
     * 
     * @param dateA
     * @param dateB
     * @return
     */
    public static boolean notEquals(Date dateA, Date dateB) {

        return !equals(dateA, dateB);
    }

    /**
     * 判断给定的日期是一周中的第几天，注意：按照中国的习惯，周日是第七天
     * 
     * @param date
     * @return
     */
    public static int dateToWeek(Date date) {

        if (date == null) {
            return 0;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            return 7;
        } else {
            return cal.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }

    public static String dateOfWeek(Date date) {

        return DATE_FORMAT_WEEK + weeks[dateToWeek(date) - 1];
    }

    /**
     * 指定时间的下一天
     * 
     * @param date
     * @return
     */
    public static Date nextDate(Date date) {

        if (date == null) {
            return date;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            return cal.getTime();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }

    /**
     * 指定时间的前一天
     * 
     * @param date
     * @return
     */
    public static Date previousDate(Date date) {

        if (date == null) {
            return date;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            return cal.getTime();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }

    /**
     * 指定时间的下N天
     * 
     * @param date
     * @return
     */
    public static Date nextNDate(Date date, int nDay) {

        if (date == null) {
            return date;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
            cal.add(Calendar.DATE, nDay);
            return cal.getTime();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }
	/**
	 * 
	* @Title: getFutureDay  获取某个时间点的未来 几天的日期
	* @Description: TODO
	* @author:罗凯
	* @param date
	* @param start      
	* @param end
	 */
	public static List<Date> getFutureDay(Date date, int start,int end){
		List<Date> dates=new ArrayList<Date>();
		for (int i = 0; i < end-start; i++) {
			dates.add(DateUtils.nextNDate(date,i));
		}
		return dates;
	}

    /**
     * 指定时间的前N天
     * 
     * @param date
     * @return
     */
    public static Date previousNDate(Date date, int nDay) {

        if (date == null) {
            return date;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
            cal.add(Calendar.DATE, -nDay);
            return cal.getTime();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return null;
    }

    /**
     * 获取一天的起始时间
     * 
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {

        if (date == null) {
            return date;
        }

        DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_DD);
        String dateStr = fmt.format(date);
        dateStr = dateStr + START_TIME;
        fmt = new SimpleDateFormat(DATE_FORMAT_SS);
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return date;
    }

    /**
     * 获取一天的结束时间
     * 
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {

        if (date == null) {
            return date;
        }

        DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_DD);
        String dateStr = fmt.format(date);
        dateStr = dateStr + END_TIME;
        fmt = new SimpleDateFormat(DATE_FORMAT_SS);
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return date;
    }

    /**
     * currentDat是否在referenceDate日期之前
     * 
     * @param referenceDate
     * @param currentDat
     * @return
     */
    public static boolean isBeforeDate(Date referenceDate, Date currentDate) {

        if (currentDate == null) {
            return false;
        }
        if (referenceDate == null) {
            return true;
        }
        return currentDate.before(referenceDate);
    }

    /**
     * currentDat是否在referenceDate日期之后
     * 
     * @param referenceDate
     * @param currentDat
     * @return
     */
    public static boolean isAffterDate(Date referenceDate, Date currentDate) {

        if (currentDate == null) {
            return false;
        }
        if (referenceDate == null) {
            return true;
        }
        return currentDate.after(referenceDate);
    }

    /**
     * 判断currentDate是否在startDate和endDate之间，不包括startDate和endDate
     * 
     * @param startDate
     * @param endDate
     * @param currentDate
     * @return
     */
    public static boolean isDuringDate(Date startDate, Date endDate, Date currentDate) {

        if (currentDate == null) {
            return false;
        }

        if (isAffterDate(startDate, currentDate) && isBeforeDate(endDate, currentDate)) {
            return true;
        }
        return false;
    }

    /**
     * 判断currentDate是否在startDate和endDate之间，包括startDate和endDate
     * 
     * @param startDate
     * @param endDate
     * @param currentDate
     * @return
     */
    public static boolean isBetweenDate(Date startDate, Date endDate, Date currentDate) {

        if (currentDate == null) {
            return false;
        }

        if (isAffterDate(startDate, currentDate) && isBeforeDate(endDate, currentDate)) {
            return true;
        }
        // 开始接受日期判断
        else if (equals(startDate, currentDate) || equals(endDate, currentDate)) {
            return true;
        }
        return false;
    }

    /**
     * 获取startDate到endDate之间的星期day（中文星期）不包括startDate和endDate
     * 
     * @param startDate
     * @param endDate
     * @param day
     * @return
     */
    public static List<Date> findDayDuringDate(Date startDate, Date endDate, int day) {

        List<Date> listDate = new ArrayList<Date>();
        int startDay = dateToWeek(startDate);

        Date date = null;
        if (startDay == day) {
            date = nextNDate(startDate, WEEK_DAYS);
        } else {
            date = nextNDate(startDate, day - startDay);
        }
        while (isDuringDate(startDate, endDate, date)) {
            listDate.add(date);
            date = nextNDate(date, WEEK_DAYS);
        }

        return listDate;
    }
    /**
     * 
    * @Title: getNextNMinute  获取date之后N分钟的时间
    * @Description: TODO
    * @author:罗凯
    * @param date
    * @return    
    * Date    返回类型 
    * @date 2016年8月3日 下午2:48:51 
    * @throws
     */
    public static Date getNextNMinute(Date date,int n){
    	return new Date(date.getTime()+(n*60*1000));
    }
    /**
     * 获取startDate到endDate之间的星期day（中文星期）包括startDate和endDate
     * 
     * @param startDate
     * @param endDate
     * @param day
     * @return
     */
    public static List<Date> findDayBetweenDate(Date startDate, Date endDate, int day) {

        List<Date> listDate = new ArrayList<Date>();
        int startDay = dateToWeek(startDate);

        Date date = null;
        if (startDay == day) {
            date = startDate;
        } else {
            date = nextNDate(startDate, day - startDay);
        }
        while (isBetweenDate(startDate, endDate, date)) {
            listDate.add(date);
            date = nextNDate(date, WEEK_DAYS);
        }

        return listDate;
    }

    /**
     * date转换成Timestamp
     * 
     * @param date
     * @param format
     * @return
     */
    public static Timestamp dateToTimestamp(Date date, String format) {

        if (date == null) {
            return null;
        }

        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_SS;
        }

        DateFormat fmt = new SimpleDateFormat(format);

        return Timestamp.valueOf(fmt.format(date));
    }

    /**
     * 获取早中晚
     * 
     * @param time
     * @return
     */
    public static String getDateTime(int time) {

        // 早上
        if (time == 1) {
            return DateUtils.DATE_TIME_MORNING;
        }
        // 下午
        else if (time == 2) {
            return DateUtils.DATE_TIME_AFTERNOON;
        }
        // 晚上
        else if (time == 3) {
            return DateUtils.DATE_TIME_NIGHT;
        }
        return null;
    }

    /**
     * 获取早中晚的开始时间
     * 
     * @param date
     * @param time
     * @return
     */
    public static Date getMeetTimeStart(String date, int time) {

        // 早上
        if (time == 1) {
            return DateUtils.stringToDate(date + " 06:00", DateUtils.DATE_FORMAT_MM);
        }
        // 下午
        else if (time == 2) {
            return DateUtils.stringToDate(date + " 13:00", DateUtils.DATE_FORMAT_MM);
        }
        // 晚上
        else if (time == 3) {
            return DateUtils.stringToDate(date + " 19:00", DateUtils.DATE_FORMAT_MM);
        }
        return null;
    }

    /**
     * 获取早中晚的结束时间
     * 
     * @param date
     * @param time
     * @return
     */

    public static Date getMeetTimeEnd(String date, int time) {
        // 早上
        if (time == 1) {
            return DateUtils.stringToDate(date + " 13:00", DateUtils.DATE_FORMAT_MM);
        }
        // 下午
        else if (time == 2) {
            return DateUtils.stringToDate(date + " 19:00", DateUtils.DATE_FORMAT_MM);
        }
        // 晚上
        else if (time == 3) {
            return DateUtils.stringToDate(date + " 23:00", DateUtils.DATE_FORMAT_MM);
        }
        return null;
    }
    
    /**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Timestamp getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return new Timestamp(now.getTime().getTime());
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Timestamp getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return new Timestamp(now.getTime().getTime());
	}
	
	
	/**
	 * 将日期类型格式化成字符串
	 * 
	 * @param date
	 * @return 格式化后日期字符串
	 * @throws ParseException
	 */
	public static String dateFormatStr(Date date, String dateStyle) {
		String dateStr = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimeMark() {
		return dateToString(new Date(), "yyyyMMddHHmmssSSS");
	}
   
	/**
	 * 获取一个编码
	 * 获取一个当前时间并且加入了四位的随机字母
	 * @return
	 */
	public static String getTimeCode() {
		return dateToString(new Date(), "yyyyMMddssSSS")+StringUtils.getRandomString(4);
		
	}
	/** 
	 * 获取时间搓，秒级
	 * @return
	 */
	public static String getTimeSecent() {
		long time=new Date().getTime();
		System.out.println(time/1000);
		return (time/1000)+"";
		
	}
}
