package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.ISellerDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Seller;
import com.github.pagehelper.PageHelper;

@Repository
public class SellerDao extends BaseDao<Seller, Integer> implements ISellerDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "sellerMapper.";
	
	@Override
	public Seller getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Seller entity) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageInfo<Seller> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Seller> brands =  sqlSession.selectList(MAPPER + "getAll");
		return new PageInfo<Seller>(brands);
	}

	
}
