package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsCategoryDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class GoodsCategoryDao extends BaseDao<GoodsCategory, String> implements IGoodsCategoryDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	final String MAPPER = ASD + "goodsCategoryMapper.";
	
	@Override
	public List<GoodsCategory> getAllByKeyword(String keyword, int pageNo,
			int pageSize) {
		return null;
	}

	@Override
	public GoodsCategory getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(GoodsCategory category) {
		String id = IdGenerator.getId();
		category.setId(id);
		sqlSession.insert(MAPPER + "insert", category);
		return id;
	}

	@Override
	public void update(GoodsCategory category) {
		sqlSession.update(MAPPER + "updateById", category);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public PageInfo<GoodsCategory> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<GoodsCategory> brands =  sqlSession.selectList(MAPPER + "getAll");
		return new PageInfo<GoodsCategory>(brands);
	}

	@Override
	public List<GoodsCategory> getAllByParentId(String parentId) {
		return sqlSession.selectList(MAPPER + "selectByParentId", parentId);
	}
	
}
