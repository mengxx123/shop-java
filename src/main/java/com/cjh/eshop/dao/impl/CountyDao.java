package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.ICountyDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.County;

@Repository
public class CountyDao extends BaseDao<County, Integer> implements ICountyDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "countyMapper.";

    @Override
    public County getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<County> getByCityCode(Integer code) {
        return sqlSession.selectList(MAPPER + "selectByCityCode", code);    
    }
    
    @Override
    public Integer save(County entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(County entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }
}
