package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.IGoodsCommentDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.GoodsComment;
import com.cjh.eshop.util.IdGenerator;

@Repository
public class GoodsCommentDao extends BaseDao<GoodsComment, String> implements IGoodsCommentDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "goodsCommentMapper.";
	
	@Override
	public List<GoodsComment> getGoodsCommentsByGoodsId(String id, int pageNo, int pageSize) {
		return sqlSession.selectList(MAPPER + "selectByGoodsId", id);
	}

	@Override
	public List<GoodsComment> getGoodCommentsByGoodsId(String id, int pageNo,
			int pageSize) {
		return sqlSession.selectList(MAPPER + "selectAll", id);
	}

	@Override
	public List<GoodsComment> getNomalCommentsByGoodsId(String id, int pageNo,
			int pageSize) {
		return sqlSession.selectList(MAPPER + "selectAll", id);
	}

	@Override
	public List<GoodsComment> getBadCommentsByGoodsId(String id, int pageNo,
			int pageSize) {
		return sqlSession.selectList(MAPPER + "selectAll", id);
	}

	@Override
	public GoodsComment getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(GoodsComment entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(GoodsComment entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public void deleteByGoodsId(String goodsId) {
		sqlSession.delete(MAPPER + "deleteByGoodsId", goodsId);
	}
}
