package com.cjh.eshop.util;

import com.cjh.eshop.common.Constant;

public class PasswordUtil {

	/**
	 * 对原始密码（未经过加密等技术处理）加密处理，所有需要保存到数据库的密码都必须进过此函数处理
	 * @param password
	 * @return
	 */
	public static String encode(String password) {
		return Md5Util.encode(Md5Util.encode(password) + Constant.APP_KEY);
	}
	
	public static String encodeMd5(String md5Password) {
		return Md5Util.encode(md5Password + Constant.APP_KEY);
	}
}
