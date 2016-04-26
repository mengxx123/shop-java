package com.cjh.eshop.common;

import javax.servlet.ServletContext;

public class Config {
	
	public static final boolean NEED_EMAIL_VALID = false; // 注册时是否需要邮箱验证
	
	private static Config instance = new Config();
	
	public static Config getInstance() {
		return instance;
	}
	
	private String svgPath;
	private String rootPath;
	private String tmpPath;
	private ServletContext servletContext;
	
	private Config() {}

	public String getSvgPath() {
		return svgPath;
	}

	public void setSvgPath(String svgPath) {
		this.svgPath = svgPath;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getTmpPath() {
		return tmpPath;
	}

	public void setTmpPath(String tmpPath) {
		this.tmpPath = tmpPath;
	}

	public String getRealPath(String fileName) {
		return servletContext.getRealPath(fileName);
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	
}
