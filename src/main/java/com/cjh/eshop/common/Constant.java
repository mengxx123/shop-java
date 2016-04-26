package com.cjh.eshop.common;

public class Constant {
	
	public static String SESSION_UID = "user_id";
	public static String SESSION_USER_NAME = "user_name";
	public static String SESSION_MANAGER_ID = "manager_name";
	
	public static String PATH_GOODS_IMAGE = "goods_image";
	public static String PATH_BRAND_IMAGE = "images/brand";
	public static String PATH_UPLOAD = "upload";
	
	public static String COOKIE_TOPIC_VIEW = "topic_view";
	public static String COOKIE_ADMIN_ACCOUNT = "manager_account";
	public static String COOKIE_ADMIN_PASSWORD = "manager_password";
	public static String COOKIE_UID = "UID";
	public static String COOKIE_PASSWORD = "password";
	
	public static String SETTING_WEBSITE_NAME = "websiteName";
	
//	public static String FILE_SEPARATOR = "/";
	
	public static String APP_KEY = "&%#@?$%)@%($)#_$)*"; // 盐值（Salt），加密用的，保证每个应用唯一性，切忌一旦使用后不可修改，否则会造成所有用户无法登陆

	public static String APP_KEY2 = "&%#@?$%)asdfgh";
}
