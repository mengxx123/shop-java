package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsCollectionDao;
import com.cjh.eshop.dao.IGoodsDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsCollection;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IGoodsCollectionService;
import com.cjh.eshop.service.common.BaseService;

@Service("goodsCollectionService")
@Transactional(rollbackFor = Exception.class)
public class GoodsCollectionService extends BaseService<GoodsCollection, String> implements IGoodsCollectionService {
	
	@Resource(name = "goodsCollectionDao")
	private IGoodsCollectionDao dao;

	@Resource(name = "goodsDao")
	private IGoodsDao goodsDao;
	
	@Override
	public PageInfo<GoodsCollection> getByUserId(String id,
			int page, int pageSize) {
	    PageInfo<GoodsCollection> pageInfo = dao.getByUserId(id, page, pageSize);
	    List<GoodsCollection> goodsCollections = pageInfo.getResult();
	    for (GoodsCollection collection : goodsCollections) {
            collection.setGoods(goodsDao.getById(collection.getGoods().getId()));
        }
		return pageInfo;
	}

	@Override
	protected IBaseDao<GoodsCollection, String> getDao() {
		return dao;
	}

	@Override
	public GoodsCollection getByUserIdAndGoodsId(String userId, String goodsId) {
		GoodsCollection collection = new GoodsCollection();
		collection.setUser(new User(userId));
		collection.setGoods(new Goods(goodsId));
		return dao.getBySelective(collection);
	}
	
	@Override
	public String save(GoodsCollection entity) {
		goodsDao.increaseCollecionCountById(entity.getGoods().getId());
		return dao.save(entity);
	}
	
	@Override
	public void deleteById(String id) {
		GoodsCollection collection = dao.getById(id);
		goodsDao.decreaseCollecionCountById(collection.getGoods().getId());
		
		dao.deleteById(id);
	}

}
