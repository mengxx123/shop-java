package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.IGoodsImageDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.util.IdGenerator;

@Repository
public class GoodsImageDao extends BaseDao<GoodsImage, String> implements IGoodsImageDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "goodsImageMapper.";
	
	@Override
	public List<GoodsImage> getGoodsImagesByGoodsId(String goodsId) {
		List<GoodsImage> iamges = sqlSession.selectList(MAPPER + "selectByGoodsId", goodsId);
		return iamges;
	}

	@Override
	public GoodsImage getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(GoodsImage image) {
		String id = IdGenerator.getId();
		image.setId(id);
		sqlSession.insert(MAPPER + "insert", image);
		return id;
	}

	@Override
	public void update(GoodsImage entity) {
		
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}
	
}
