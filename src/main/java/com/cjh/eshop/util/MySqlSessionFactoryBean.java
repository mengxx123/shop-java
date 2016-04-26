package com.cjh.eshop.util;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;



public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {
	
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try {
			return super.buildSqlSessionFactory();
		} catch (NestedIOException e) {
			e.printStackTrace(); // XML 有错误时打印异常。
			throw new NestedIOException("Failed to parse mapping resource: '" + "mapperLocation" + "'", e);
		} finally {
			ErrorContext.instance().reset();
		}
		//return super.buildSqlSessionFactory();
	}
}
