package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IFeedbackDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Feedback;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class FeedbackDao extends BaseDao<Feedback, String> implements IFeedbackDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "feedbackMapper.";
	
	@Override
	public Feedback getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
    public PageInfo<Feedback> getAllFeedback(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Feedback> feedback =  sqlSession.selectList(MAPPER + "getAll");
        PageInfo<Feedback> page = new PageInfo<Feedback>(feedback);
        return page;    
    }
	
	@Override
	public String save(Feedback entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(Feedback entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

}
