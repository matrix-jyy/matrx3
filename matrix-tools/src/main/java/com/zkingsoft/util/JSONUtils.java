package com.zkingsoft.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import com.zkingsoft.exception.GlobleExceptionResolver;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON对象操作工具类，格式转化，获取值（防止空异常）
 * 
 * @author Ron
 * @createTime 2014.08.30
 */
public class JSONUtils {
	private static Logger log = Logger.getLogger(GlobleExceptionResolver.class);
    public static String getString(JSONObject jsonparam, String key) {
        
        if (jsonparam == null) {
            return "";
        }
        try {
            return jsonparam.getString(key);
        }
        catch (Exception e) {
            return "";
        }
    }
    
    /****
     * 
     * @param jsonparam
     * @param key
     * @param str 为null返回默认值
     * @return
     */
    public static String getString(JSONObject jsonparam, String key, String str) {
        
        if (jsonparam == null) {
            return str;
        }
        try {
            String s = jsonparam.getString(key);
            if (StringUtils.isNotBlank(s)) {
                return StringUtils.trim(s);
            }
            return str;
        }
        catch (Exception e) {
            return str;
        }
    }
    
    public static int getInt(JSONObject jsonparam, String key) {
        
        if (jsonparam == null) {
            return 0;
        }
        try {
            return jsonparam.getInt(key);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * 序列化对象
     * 
     * @param obj
     * @return
     */
    public static String setSer(Object obj) {
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream out;
        String serStr = "";
        try {
            out = new ObjectOutputStream(outputStream);
            out.writeObject(obj);
            serStr = outputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        }
        catch (IOException e) {
            log.error("JSONUtils:",e);
        }
        return serStr;
    }
    
    /**
     * 反序列化对象
     * 
     * @param serStr
     * @return
     */
    public static Object getSer(String serStr) {
        
        String redStr;
        InputStream inputStream = null;
        try {
            redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            inputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
        }
        catch (UnsupportedEncodingException e1) {
            log.error("JSONUtils:",e1);
        }
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(inputStream);
            return in.readObject();
        }
        catch (Exception e) {
            System.out.println("对象存在问题，请重新读取！");
        }
        return null;
    }
    
    public static long getLong(JSONObject jsonparam, String key) {
        
        if (jsonparam == null) {
            return 0;
        }
        try {
            return jsonparam.getLong(key);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static int getInt(JSONObject jsonparam, String key, int number) {
        
        if (jsonparam == null) {
            return number;
        }
        try {
            return jsonparam.getInt(key);
        }
        catch (Exception e) {
            return number;
        }
    }
    
    public static JSONArray getJSONArray(JSONObject jsonparam, String key) {
        JSONArray array = null;
        if (jsonparam == null) {
            array = new JSONArray();
        }
        else {
            try {
                array = jsonparam.getJSONArray(key);
            }
            catch (Exception e) {
                array = new JSONArray();
            }
        }
        return array;
    }
    
    public static JSONArray getJSONArray(String key) {
        
        if (StringUtils.isBlank(key)) {
            return new JSONArray();
        }
        try {
            return JSONArray.fromObject(key);
        }
        catch (Exception e) {
            return new JSONArray();
        }
    }
    
    public static JSONObject getJsonObject(String key) {
        
        if (StringUtils.isBlank(key))
            return new JSONObject();
        try {
            return JSONObject.fromObject(key);
        }
        catch (Exception e) {
            return new JSONObject();
        }
    }
    
    public static JSONObject getJsonObject(JSONObject jsonparam, String key) {
        
        if (StringUtils.isBlank(key))
            return new JSONObject();
        try {
            return jsonparam.getJSONObject(key);
        }
        catch (Exception e) {
            return new JSONObject();
        }
    }
    
    public static Double getDouble(JSONObject jsonparam, String key) {
        
        try {
            Double value = jsonparam.getDouble(key);
            return value;
        }
        catch (Exception e) {
        }
        return 0.0;
    }
    
  
    
    public static String getString(List<String> list, int i) {
        
        if (list == null) {
            return "";
        }
        
        try {
            return list.get(i);
        }
        catch (Exception e) {
            return "";
        }
        
    }
    
    
}
