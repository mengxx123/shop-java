package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsComment;

public interface IGoodsCommentDao extends IBaseDao<GoodsComment, String> {
	public List<GoodsComment> getGoodsCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getGoodCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getNomalCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getBadCommentsByGoodsId(String id, int page, int pageSize);
	public void deleteByGoodsId(String goodsId);
}
