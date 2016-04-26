package com.cjh.eshop.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.model.User;

public class UserTest {
	public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
		
		
        /*
		SqlSession session = MyBatisUtil.getSqlSession();
		
		IUserDao mapper = session.getMapper(IUserDao.class);
		*/
        String statement = "com.cjh.eshop.mapping.userMapper.getAllUser";
        List<User> users =  session.selectList(statement);
        
        System.out.println(users.size());
        
		
        session.close();

	}
}
