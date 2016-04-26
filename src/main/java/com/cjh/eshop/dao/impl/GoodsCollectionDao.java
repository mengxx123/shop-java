package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsCollectionDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.GoodsCollection;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class GoodsCollectionDao extends BaseDao<GoodsCollection, String> implements IGoodsCollectionDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "goodsCollectionMapper.";

	@Override
	public String save(GoodsCollection entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(GoodsCollection entity) {
	}

	@Override
	public PageInfo<GoodsCollection> getByUserId(String id, int page,
			int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<GoodsCollection> collections = sqlSession.selectList(MAPPER+"selectByUserId", id);
		PageInfo<GoodsCollection> pageInfo = new PageInfo<GoodsCollection>(collections);
		return pageInfo;
	} 

	@Override
	public GoodsCollection getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public GoodsCollection getBySelective(GoodsCollection collection) {
		GoodsCollection ret = sqlSession.selectOne(MAPPER + "selectBySelective", collection);
		return ret;
	} 
	
	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}
	
	@Override
	public void deleteByGoodsId(String id) {
		sqlSession.delete(MAPPER + "deleteByGoodsId", id);
	}
}
