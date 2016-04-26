package com.cjh.eshop.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cjh.eshop.service.impl.SettingService;

/** 系统初始化Servlet，在系统启动时读取配置文件信息 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 5113858510124029851L;

	private static Logger logger = LoggerFactory.getLogger(InitServlet.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		logger.info("系统初始化开始");
		String svgPath = getServletContext().getRealPath("svg");
		
		Config config = Config.getInstance();
		config.setSvgPath(svgPath);
		
		String tmpPath = getServletContext().getRealPath("tmp");
		config.setServletContext(getServletContext());
		config.setTmpPath(tmpPath);
		
		ServletContext servletContext = getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		SettingService settingService = (SettingService) ctx.getBean("settingService");
		
		System.out.println(settingService.getValue("websiteName"));
		SettingHelper.getInstance().put("websiteName", settingService.getValue("websiteName"));
		logger.info("系统初始化完成");	
	}
}
