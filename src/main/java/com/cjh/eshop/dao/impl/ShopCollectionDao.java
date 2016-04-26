package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IShopCollectionDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.ShopCollection;
import com.github.pagehelper.PageHelper;

@Repository
public class ShopCollectionDao extends BaseDao<ShopCollection, String> implements IShopCollectionDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "shopCollectionMapper.";
	@Override
	public String save(ShopCollection entity) {
		return null;
	}

	@Override
	public void update(ShopCollection entity) {
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public PageInfo<ShopCollection> getShopCollectionsByUserId(String id, int page,
			int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ShopCollection> collections = sqlSession.selectList(MAPPER+"selectByUserId", id);
		PageInfo<ShopCollection> pageInfo = new PageInfo<ShopCollection>(collections);
		return pageInfo;
	} 

	@Override
	public ShopCollection getById(String id) {
		return null;
	}
	
}
