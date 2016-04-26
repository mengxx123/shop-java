package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.User;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class UserDao extends BaseDao<User, String>  implements IUserDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	final String MAPPER = ASD + "userMapper.";
	
	@Override
	public PageInfo<User> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> users =  sqlSession.selectList(MAPPER + "selectAll");
		PageInfo<User> page = new PageInfo<User>(users);
		return page;	
	}

	@Override
	public User getUserByUserName(String userName) {
		return (User) sqlSession.selectOne(MAPPER + "selectByUserName", userName);
	}

	@Override
	public User getUserByUserEmail(String email) {
		return (User) sqlSession.selectOne(MAPPER + "selectByEmail", email);
	}

	@Override
	public User getUserByUserNickname(String nickname) {
		return (User) sqlSession.selectOne(MAPPER + "selectByNickName", nickname);
	}

	@Override
	public PageInfo<User> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> users =  sqlSession.selectList(MAPPER + "selectAll");
		PageInfo<User> page = new PageInfo<User>(users);
		return page;
	}

	@Override
	public User getById(String id) {
		return (User) sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public void update(User user) {
		String statement = MAPPER + "updateById";
		sqlSession.update(statement, user);
	}

	@Override
	public String save(User user) {
		String id = IdGenerator.getId();
		user.setId(id);
		sqlSession.insert(MAPPER + "insert", user);
		return id;
	}

	@Override
	public void deleteById(String id) {
		String statement = MAPPER + "deleteById";
		sqlSession.delete(statement, id);
	}

}
