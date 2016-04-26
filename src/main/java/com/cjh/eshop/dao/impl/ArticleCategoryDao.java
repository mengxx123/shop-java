package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IArticleCategoryDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.ArticleCategory;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class ArticleCategoryDao extends BaseDao<ArticleCategory, String> implements IArticleCategoryDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "articleCategoryMapper.";
	
	@Override
	public List<ArticleCategory> getTopCategory() {
		return null;
	}

	@Override
	public ArticleCategory getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(ArticleCategory entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(ArticleCategory entity) {
		String statement = MAPPER + "updateById";
		sqlSession.update(statement, entity);
	}

	@Override
	public void deleteById(String id) {
		String statement = MAPPER + "deleteById";
		sqlSession.delete(statement, id);
	}

	@Override
	public PageInfo<ArticleCategory> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		String statement = MAPPER + "selectAll";
		List<ArticleCategory> users =  sqlSession.selectList(statement);
		PageInfo<ArticleCategory> page = new PageInfo<ArticleCategory>(users);
		return page;	
	}

	@Override
	public List<ArticleCategory> getAll() {
		String statement = MAPPER + "selectAll";
		List<ArticleCategory> categories =  sqlSession.selectList(statement);
		return categories;	
	}
	
}
