package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IManagerDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Manager;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class ManagerDao extends BaseDao<Manager, String> implements IManagerDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "managerMapper.";
	
	@Override
	public Manager getManagerByAccount(String account) {
		return (Manager) sqlSession.selectOne(MAPPER + "selectByAccount", account);
	}

	@Override
	public Manager getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public PageInfo<Manager> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		String statement = MAPPER + "selectAll";
		List<Manager> managers = sqlSession.selectList(statement);
		PageInfo<Manager> page = new PageInfo<Manager>(managers);
		return page;	
	}

	@Override
	public Manager getByAccount(String account) {
		return (Manager) sqlSession.selectOne(MAPPER + "selectByAccount", account);
	}
	
	@Override
	public String save(Manager entity) {
		// TODO 如何保证外键
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(Manager entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public void updateBySelective(Manager manager) {
		sqlSession.update(MAPPER + "updateByIdSelective", manager);
		
	}
	
	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

}
