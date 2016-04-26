package com.cjh.eshop.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 自定义标签库
 * 
 * @author 陈建杭
 *
 */
public class TimeTag extends SimpleTagSupport {

	private Date value;
    
    @Override
    public void doTag() throws JspException, IOException {

    	Calendar calendar = Calendar.getInstance();
    	int year = calendar.get(Calendar.YEAR);
    	int day = calendar.get(Calendar.DAY_OF_MONTH);
    	calendar.setTime(value);
    	
    	JspWriter out = getJspContext().getOut();
    	
    	if (calendar.get(Calendar.YEAR) != year) {
    		out.print(new SimpleDateFormat("yyyy-MM-dd").format(value));
    	} else if (calendar.get(Calendar.DAY_OF_MONTH) != day) {
    		out.print(new SimpleDateFormat("MM-dd").format(value));
    	} else {
    		out.print(new SimpleDateFormat("HH:mm").format(value));
    	}
    	
    }

	public void setValue(Date value) {
		this.value = value;
	}
    
}
