package com.zkingsoft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtils {
	private static Logger log = Logger.getLogger(TimeUtils.class);
    /**
     * 获取 当前年、半年、季度、月、日、小时 开始结束时间
     */
    private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");;
    
    private static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    
    private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @SuppressWarnings("static-access")
    public TimeUtils() {
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
        this.longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 获得周的开始时间
     * 
     * @param checkrule 数字规则类型 0 从权重单位时间进行计算 1从当前时间开始往前计算
     * @return
     */
    public static Date getCurrentWeekDayStartTime(String checkrule) {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            if (checkrule.equals("1")) {
                c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
                return c.getTime();
            }
            
        }
        catch (Exception e) {
            log.error("TimeUtils:", e);
        }
        return c.getTime();
    }
    
    /**
     * 获得本天的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentDayStartTime(String checkrule) {
        Date now = new Date();
        try {
            if (checkrule.equals("1")) {
                now = shortSdf.parse(shortSdf.format(now));
                return now;
            }
        }
        catch (Exception e) {
            log.error("TimeUtils:", e);
        }
        return now;
    }
    
    /**
     * 获得本小时的开始时间，即2012-01-01 23:59:59
     * 
     * @return
     */
    public static Date getCurrentHourStartTime(String checkrule) {
        Date now = new Date();
        try {
            if (checkrule.equals("1")) {
                now = longHourSdf.parse(longHourSdf.format(now));
            }
        }
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return now;
    }
    
    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentMonthStartTime(String checkrule) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            
            if (checkrule.equals("1")) {
                c.set(Calendar.DATE, 1);
                now = shortSdf.parse(shortSdf.format(c.getTime()));
                return now;
            }
            
            now = c.getTime();
        }
        catch (Exception e) {
            log.error("TimeUtils:", e);
        }
        return now;
    }
    
    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static Date getCurrentYearStartTime(String checkrule) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            if (checkrule.equals("1")) {
                
                c.set(Calendar.MONTH, 0);
                c.set(Calendar.DATE, 1);
                now = shortSdf.parse(shortSdf.format(c.getTime()));
                return now;
            }
        }
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return c.getTime();
    }
    
    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public static Date getCurrentQuarterStartTime(String checkrule) {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            if (checkrule.equals("1")) {
                now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
                return now;
            }
            
        }
        catch (Exception e) {
            log.error("TimeUtils:", e);
        }
        return c.getTime();
    }
    
    /**
     * 获取前/后半年的开始时间
     * 
     * @return
     */
    public static Date getHalfYearStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            }
            else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        }
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return now;
        
    }
    
    /**
     * 获取前/后半年的结束时间
     * 
     * @return
     */
    public static Date getHalfYearEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            }
            else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        }
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return now;
    }
    
    /*--------------------------------------------------------------------------------*/
    /**
     * 获得年的结束时间
     * 
     * @param data 权重的参数
     * @param checkrule 权重计算类型
     * @return 单位权重的结束时间
     */
    public static Date getEndYearTime(String data, String checkrule) {
        // 获得当前时间
        Calendar c = Calendar.getInstance();
        int dataInt = StringUtils.stringToInt(data);
        try {
            if (checkrule.equals("0")) {
                
                c.add(Calendar.YEAR, -dataInt);
                return c.getTime();
            }
            else {
                // 获得年结束时间
                
                if (dataInt > 1) {
                    dataInt -= 1;
                    c.add(Calendar.YEAR, dataInt);
                }
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
                
                c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
                
                return c.getTime();
            }
        }
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return c.getTime();
    }
    
    /**
     * 权重单位规则 获取季度的结束时间
     * 
     * @param data 规则参数
     * @param checkrule 计算类型
     * @return 规则权限结束时间
     */
    public static Date getQuarter(String data) {
        
        // 获得当前时间
        Calendar c = Calendar.getInstance();
        try {
            
            int dataInt = StringUtils.stringToInt(data);
            
            if (dataInt == 1) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            }
            if (dataInt == 2) {
                c.set(Calendar.MONTH, 4);
                c.set(Calendar.DATE, 31);
            }
            if (dataInt == 3) {
                c.set(Calendar.MONTH, 7);
                c.set(Calendar.DATE, 31);
            }
            if (dataInt == 4) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        }
        catch (Exception e) {
        }
        return c.getTime();
        
    }
    
    /**
     * 权重单位为 天
     * 
     * @param data 权重参数
     * @param checkrule 类型
     * @return 结束时间
     */
    public static Date getDay(String data, String checkrule) {
        
        // 获得当前时间
        Calendar c = Calendar.getInstance();
        int endDay = StringUtils.stringToInt(data);
        try {
            
            if (checkrule.equals("1")) {
                c.add(Calendar.DATE, endDay);
                c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
                return c.getTime();
            }
            else if (checkrule.equals("0")) {
                c.add(Calendar.DATE, -endDay);
                
                return c.getTime();
            }
        }
        catch (Exception e) {
        }
        
        return c.getTime();
    }
    
    /**
     * 单位权重为月 时 获得 到期时间
     * 
     * @param data 权重参数
     * @param checkrule 计算类型
     * @return 结束时间
     */
    public static Date getendTimeToMonth(String data, String checkrule) {
        
        // 获得当前时间
        Date createTime = new Date();
        Calendar c = Calendar.getInstance();
        try {
            int dataInt = StringUtils.stringToInt(data);
            int month = DateUtils.getMonth(createTime);
            int year = DateUtils.getYear(createTime);
            if (dataInt > 12 - month && dataInt < 12) {
                month += dataInt;
                year += 1;
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month - 12 - 1);
                
            }
            else if (dataInt >= 12) {
                int trad = dataInt / 12;
                int residue = dataInt % 12;
                year += trad;
                month += residue;
                if (month > 12) {
                    month -= 12;
                    year += 1;
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, month - 1);
                }
                else {
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, month - 1);
                }
            }
            if (checkrule.equals("0")) {
                c.set(Calendar.DATE, 30);
            }
        }
        catch (Exception e) {
            
        }
        return c.getTime();
    }
    
    /**
     * 获得小时的结束时间
     * 
     * @param data
     * @return 小时的结束时间
     */
    public static Date getEndHours(String data, String checkrule) {
        int dataInt = StringUtils.stringToInt(data);
        Calendar c = Calendar.getInstance();
        try {
            
            if (checkrule.equals("1")) {
                c.add(Calendar.HOUR, dataInt - 1);
                c.setTime(longSdf.parse(longHourSdf.format(c.getTime()) + ":59:59"));
            }
            else if (checkrule.equals("0")) {
                c.add(Calendar.HOUR, -dataInt);
            }
        }
        
        catch (Exception e) {
            log.error("TimeUtils:",e );
        }
        return c.getTime();
    }
    
    /**
     * 周的结束时间
     * 
     * @param date
     * @return 结束时间
     */
    public static Date getEndWeekDayTime(String data, String checkrule) {
        Calendar c = Calendar.getInstance();
        try {
            
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            int dataInt = StringUtils.stringToInt(data);
            if (checkrule.equals("0")) {
                c.add(Calendar.DATE, -(7 * dataInt));
                
            }
            else if (checkrule.equals("1")) {
                if (dataInt == 1) {
                    c.add(Calendar.DATE, 8 - weekday);
                    c.setTime(longSdf.parse(longHourSdf.format(c.getTime()) + "23:59:59"));
                }
                if (dataInt > 1) {
                    c.add(Calendar.DATE, (8 - weekday) + (dataInt - 1) * 7);
                    c.setTime(longSdf.parse(longHourSdf.format(c.getTime()) + "23:59:59"));
                }
            }
        }
        catch (Exception e) {
            log.error("TimeUtils:", e);
        }
        return c.getTime();
    }
    
    public static void main(String[] args) {
        
        String data = "3";
        String checkrule = "1";
        
        Date resultDate = TimeUtils.getEndYearTime(data, checkrule);
        String time = DateUtils.dateToString(resultDate, DateUtils.DATE_FORMAT_SS);
        System.out.println(time);
    }
}