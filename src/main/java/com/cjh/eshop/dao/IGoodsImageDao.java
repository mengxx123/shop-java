package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsImage;

public interface IGoodsImageDao extends IBaseDao<GoodsImage, String> {
	public List<GoodsImage> getGoodsImagesByGoodsId(String goodsId);
}
