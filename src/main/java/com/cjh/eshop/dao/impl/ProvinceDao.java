package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.IProvinceDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Province;

@Repository
public class ProvinceDao extends BaseDao<Province, Integer> implements IProvinceDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "provinceMapper.";

    @Override
    public Province getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Province> getAll() {
        return sqlSession.selectList(MAPPER + "selectAll");    
    }
    
    @Override
    public Integer save(Province entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Province entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }
}
