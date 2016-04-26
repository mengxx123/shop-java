package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsCategory;

public interface IGoodsCategoryDao extends IBaseDao<GoodsCategory, String> {
	public List<GoodsCategory> getAllByKeyword(String keyword, int pageNo, int pageSize);
	public PageInfo<GoodsCategory> getAllByPage(int pageNo, int pageSize);
	public List<GoodsCategory> getAllByParentId(String parentId);
}
