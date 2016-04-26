package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IShopDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Shop;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class ShopDao extends BaseDao<Shop, String> implements IShopDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "shopMapper.";
	
	@Override
	public Shop getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(Shop entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(Shop entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public PageInfo<Shop> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Shop> users =  sqlSession.selectList(MAPPER + "selectAll");
		return new PageInfo<Shop>(users);
	}

	@Override
	public Shop getByUserId(String id) {
		return sqlSession.selectOne(MAPPER+"getShopByUserId", id);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}
	
	@Override
	public void deleteByShopId(String id) {
		sqlSession.delete(MAPPER + "deleteByShopId", id);
	}
	
}
