package com.xw.util.util;

import java.util.Random;

public class StringUtil {
	 /**
     * 返回指定长度随机数字组成的字符串
     * 
     * @param length  指定长度
     * @return 随机字符串
     */
    public static String captchaNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    
	/**
	 * 确定字符互异
	 * @param iniString
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDifferent(String iniString) {
		return !iniString.matches(".*(.)(.*\\1).*");
	}
	
	/**
     * 判断字符串是否为数字和有正确的值
     *
     * @param str
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isNumber(String str) {
        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }
}
