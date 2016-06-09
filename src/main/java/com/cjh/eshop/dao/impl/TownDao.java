package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.ITownDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Town;

@Repository
public class TownDao extends BaseDao<Town, Integer> implements ITownDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "townMapper.";

    @Override
    public Town getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Town> getByCountyCode(Integer code) {
        return sqlSession.selectList(MAPPER + "selectByCountyCode", code);    
    }
    
    @Override
    public Integer save(Town entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Town entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }
}
