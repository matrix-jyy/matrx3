package com.zkingsoft.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class EncrypUtil {
	private static Logger log = Logger.getLogger(EncrypUtil.class);

	/*
	 * 方法名称：getMD5 功 能：字符串MD5加密 参 数：待转换字符串 返 回 值：加密之后字符串
	 */
	public static String getMD5(String sourceStr)
        throws UnsupportedEncodingException {
        
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        }
        catch (NoSuchAlgorithmException e) {
            log.error("EncrypUtil", e);
            return null;
        }
    }

	public static void main(String[] args) throws Exception {
		System.out.println(getMD5("123"));
	}
}
