package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.GoodsCollection;
import com.cjh.eshop.service.common.IBaseService;

public interface IGoodsCollectionService extends IBaseService<GoodsCollection, String> {
	public PageInfo<GoodsCollection> getByUserId(String id, int page, int pageSize);
	public GoodsCollection getByUserIdAndGoodsId(String userId, String goodsId);
}
