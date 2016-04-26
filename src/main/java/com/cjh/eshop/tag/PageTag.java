package com.cjh.eshop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.cjh.eshop.common.PageInfo;

/**
 * 分页标签
 * 
 * @author 陈建杭
 *
 */
public class PageTag extends SimpleTagSupport {

	@SuppressWarnings("rawtypes")
	private PageInfo page;
	private String url;
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		//String url = "sections/1?page=";
		if (page == null) {
			return;
		}
		
		out.print("<ul class=\"pagination\">");
		if (page.getCurrentPage() > 1) {
			out.print("<li><a href=\"" + url + (page.getCurrentPage() - 1) + "\"><span>&laquo;</span></a></li>");
		}
		for (int i = page.getStartPage(); i <= page.getEndPage(); i++) {
			if (i == page.getCurrentPage()) {
				out.print("<li class=\"active\"><a href=\"" + url + i + "\">" + i + "</a></li>");
			} else {
				out.print("<li><a href=\"" + url + i + "\">" + i + "</a></li>");
			}
		}
		if (page.getCurrentPage() < page.getTotalPage()) {
			out.print("<li><a href=\"" + url + (page.getCurrentPage() + 1) + "\"><span>&raquo;</span></a></li>");
		}
		out.print("</ul>");
	}

	@SuppressWarnings("rawtypes")
	public void setPage(PageInfo page) {
		this.page = page;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
