package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.service.common.IBaseService;

public interface IGoodsService extends IBaseService<Goods, String> {
	public boolean increaseClickCount(String goodsId);
	public PageInfo<Goods> getGoodsesByShopId(String id, int page, int pageSize);
	public PageInfo<Goods> getByExample(Goods goods, int pageNo, int pageSize);
	public PageInfo<Goods> getGoodses(int page, int pageSize);
	public PageInfo<Goods> getGoodsesByCategoryId(String id, int pageNo, int pageSize);
	public String saveSelective(Goods goods);
}
