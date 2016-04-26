package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Goods;

public interface IGoodsDao extends IBaseDao<Goods, String> {
	public boolean increaseClickCount(String goodsId);
	public PageInfo<Goods> getGoodsesByShopId(String id, int pageNo, int pageSize);
	public PageInfo<Goods> getGoodsesByCategoryId(String id, int pageNo, int pageSize);
	public PageInfo<Goods> getAllByPage(int pageNo, int pageSize);
	public String saveSelective(Goods goods);
	public PageInfo<Goods> getByExample(Goods goods, int pageNo, int pageSize);
	public void increaseCollecionCountById(String id);
	public void decreaseCollecionCountById(String id);
}
