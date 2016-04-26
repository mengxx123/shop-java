package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsCollection;

public interface IGoodsCollectionDao extends IBaseDao<GoodsCollection, String> {
	public PageInfo<GoodsCollection> getByUserId(String id, int page, int pageSize);
	public GoodsCollection getBySelective(GoodsCollection collection);
	public void deleteByGoodsId(String id);
}
