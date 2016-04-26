package com.cjh.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsDao;
import com.cjh.eshop.dao.common.BaseDao;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.util.IdGenerator;
import com.github.pagehelper.PageHelper;

@Repository
public class GoodsDao extends BaseDao<Goods, String> implements IGoodsDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	final String MAPPER = ASD + "goodsMapper.";
	
	@Override
	public boolean increaseClickCount(String goodsId) {
		/*Session session = getCurrentSession();
		
		// TODO 防注入 是否+1
		String hql = "update Goods goods set goods.clickCount = clickCount + 1 where goods.id = " + goodsId;
		Query queryupdate=session.createQuery(hql);
		int ret = queryupdate.executeUpdate();
		return ret != 0;*/
		return true;
	}
	
	@Override
	public PageInfo<Goods> getGoodsesByShopId(String id, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Goods> users =  sqlSession.selectList(MAPPER + "selectByShopId", id);
		PageInfo<Goods> page = new PageInfo<Goods>(users);
		return page;	
	}

	@Override
	public Goods getById(String id) {
		return sqlSession.selectOne(MAPPER + "selectById", id);
	}

	@Override
	public PageInfo<Goods> getAllByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Goods> users =  sqlSession.selectList(MAPPER + "selectAll");
		PageInfo<Goods> page = new PageInfo<Goods>(users);
		return page;	
	}

	@Override
	public String save(Goods entity) {
		String id = IdGenerator.getId();
		entity.setId(id);
		sqlSession.insert(MAPPER + "insert", entity);
		return id;
	}
	
	@Override
	public String saveSelective(Goods goods) {
		String id = IdGenerator.getId();
		goods.setId(id);
		sqlSession.insert(MAPPER + "insertSelective", goods);
		return id;
	}

	@Override
	public PageInfo<Goods> getGoodsesByCategoryId(String id, int pageNo,
			int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Goods> users =  sqlSession.selectList(MAPPER + "selectByCategoryId", id);
		PageInfo<Goods> page = new PageInfo<Goods>(users);
		return page;	
	}

	@Override
	public PageInfo<Goods> getByExample(Goods goods, int pageNo, int pageSize) {
		System.out.println("DAO"+goods.getCategory());
		PageHelper.startPage(pageNo, pageSize);
		List<Goods> users =  sqlSession.selectList(MAPPER + "selectBySelective", goods);
		PageInfo<Goods> page = new PageInfo<Goods>(users);
		return page;
	}

	@Override
	public void update(Goods entity) {
		sqlSession.update(MAPPER + "updateById", entity);
	}

	@Override
	public void deleteById(String id) {
		sqlSession.delete(MAPPER + "deleteById", id);
	}

	@Override
	public void increaseCollecionCountById(String id) {
		sqlSession.update(MAPPER + "increaseCollecionCountById", id);
	}

	@Override
	public void decreaseCollecionCountById(String id) {
		sqlSession.update(MAPPER + "decreaseCollecionCountById", id);
	}
}
