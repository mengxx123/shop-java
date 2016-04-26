package com.cjh.eshop.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.dao.ISettingDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Setting;

@Repository
public class SettingDao extends BaseDao<Setting, Integer> implements ISettingDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	final String MAPPER = ASD + "settingMapper.";

	@Override
	public Setting getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Setting entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Setting entity) {
		String statement = MAPPER + "updateById";
		sqlSession.update(statement, entity);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Setting getByKey(String key) {
		return (Setting) sqlSession.selectOne(MAPPER + "selectByKey", key);
	}

	@Override
	public void updateByKey(Setting setting) {
		sqlSession.update(MAPPER + "updateByKey", setting);
	}
	
	
}
