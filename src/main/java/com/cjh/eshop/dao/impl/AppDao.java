package com.cjh.eshop.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.IAppDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.App;

@Repository
public class AppDao extends BaseDao<App, String> implements IAppDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	final String MAPPER = ASD + "appMapper.";

    @Override
    public App getById(String id) {
        return sqlSession.selectOne(MAPPER + "selectById", id);
    }

    @Override
    public String save(App entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(App entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }
	
	
	
}
