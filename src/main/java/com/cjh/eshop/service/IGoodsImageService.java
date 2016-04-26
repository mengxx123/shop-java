package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.service.common.IBaseService;

public interface IGoodsImageService extends IBaseService<GoodsImage, String> {
	public List<GoodsImage> getGoodsImagesByGoodsId(String goodsId);
}
