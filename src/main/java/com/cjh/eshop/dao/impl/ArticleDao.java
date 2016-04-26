package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IArticleDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class ArticleDao extends BaseDao<Article, String> implements IArticleDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "articleMapper.";
	
	@Override
	public List<Article> getAllArticle(int pageNo, int pageSize) {
		return sqlSession.selectList(MAPPER + "selectAll");
	}

	@Override
	public PageInfo<Article> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Article> articles =  sqlSession.selectList(MAPPER + "selectAll");
		return new PageInfo<Article>(articles);
	}

	@Override
	public Article getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public String save(Article entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}

	@Override
	public void update(Article entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public PageInfo<Article> getAllByCategoryId(String categoryId, int pageNo,
			int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		String statement = MAPPER + "selectByCategoryId";
		List<Article> articles =  sqlSession.selectList(statement, categoryId);
		return new PageInfo<Article>(articles);
	}
	
}
