package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IMessageDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Message;
import com.github.pagehelper.PageHelper;

@Repository
public class MessageDao extends BaseDao<Message, String> implements IMessageDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	final String MAPPER = ASD + "messageMapper.";
	
	@Override
	public Message getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public PageInfo<Message> getByUserId(String userId, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		String statement = MAPPER + "selectByUserId";
		List<Message> topics =  sqlSession.selectList(statement, userId);
		PageInfo<Message> page = new PageInfo<Message>(topics);
		return page;
	}
	
	@Override
	public String save(Message entity) {
		sqlSession.insert(MAPPER + "insert", entity);
		return null; // TODO
	}

	@Override
	public void update(Message entity) {
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public void updateByIdSelective(Message message) {
		sqlSession.insert(MAPPER + "updateByIdSelective", message);
	}

	@Override
	public PageInfo<Message> getByExample(Message message, int pageNo,
			int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		String statement = MAPPER + "selectByExample2";
		List<Message> topics =  sqlSession.selectList(statement, message);
		PageInfo<Message> page = new PageInfo<Message>(topics);
		return page;
	}

}
