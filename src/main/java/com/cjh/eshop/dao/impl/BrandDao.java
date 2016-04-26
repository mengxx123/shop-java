package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IBrandDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Brand;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class BrandDao extends BaseDao<Brand, String> implements IBrandDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "brandMapper.";
	
	@Override
	public PageInfo<Brand> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Brand> brands =  sqlSession.selectList(MAPPER + "selectAll");
		return new PageInfo<Brand>(brands);
	}

	@Override
	public void update(Brand brand) {
		sqlSession.update(MAPPER + "updateById", brand);
	}

	@Override
	public String save(Brand brand) {
		String id = IdGenerator.getId();
		brand.setId(id);
		sqlSession.insert(MAPPER + "insert", brand);
		return id;
	}

	@Override
	public PageInfo<Brand> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Brand> brands =  sqlSession.selectList(MAPPER + "selectAll");
		return new PageInfo<Brand>(brands);
	}

	@Override
	public Brand getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public PageInfo<Brand> getByExample(Brand brand, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Brand> brands =  sqlSession.selectList(MAPPER + "selectBySearch", brand);
		return new PageInfo<Brand>(brands);
	}

}
