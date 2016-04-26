package com.cjh.eshop.util;

public class Validation {
	
	/**
	 * 检验一段文本是否包含广告、违法
	 * @param text
	 * @return
	 */
	public static boolean isValid(String text) {
		String[] badWord = {"迷药", "六合彩", "摇头丸", "十五字"};
		for (String string : badWord) {
			if (text.contains(string)) {
				return false;
			}
		}
		
		return true;
	}
}
