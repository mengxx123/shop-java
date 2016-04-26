package com.cjh.eshop.util;

import org.junit.Test;

import com.cjh.eshop.util.EmailUtil;

public class EmailUtilTest {

	@Test
	public void testSendEmail(String[] args) {
		EmailUtil.sendEmail("1418503647@qq.com", "测试标题", "测试内容");
	}

}
