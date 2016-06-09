package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.ICityDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.City;

@Repository
public class CityDao extends BaseDao<City, Integer> implements ICityDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "cityMapper.";

    @Override
    public City getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<City> getByProvinceCode(Integer code) {
        return sqlSession.selectList(MAPPER + "selectByProvinceCode", code);    
    }
    
    @Override
    public Integer save(City entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(City entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }
}
