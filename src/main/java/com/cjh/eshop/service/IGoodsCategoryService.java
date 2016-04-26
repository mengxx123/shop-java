package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.GoodsCategory;
import com.cjh.eshop.service.common.IBaseService;

public interface IGoodsCategoryService extends IBaseService<GoodsCategory, String> {
	public PageInfo<GoodsCategory> getAllByPage(int pageNo, int pageSize);
	public List<GoodsCategory> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public List<GoodsCategory> getAllByParentId(String parentId);
}
