package com.cjh.eshop.util;

public class TextUtil {
	
	private TextUtil() {}
	
	/**
	 * 描述：将null转化为"".
	 * 
	 * @param str
	 *            指定的字符串
	 * @return 字符串的String类型
	 */
	public static String parseEmpty(String str) {
		if (str == null || "null".equals(str.trim())) {
			str = "";
		}
		return str.trim();
	}

	/**
	 * 描述：判断一个字符串是否为null或空值.
	 * 
	 * @param str
	 *            指定的字符串
	 * @return true or false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
}
