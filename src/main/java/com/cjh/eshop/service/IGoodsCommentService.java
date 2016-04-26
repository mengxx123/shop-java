package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.GoodsComment;

public interface IGoodsCommentService {
	public List<GoodsComment> getGoodsCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getGoodCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getNomalCommentsByGoodsId(String id, int page, int pageSize);
	public List<GoodsComment> getBadCommentsByGoodsId(String id, int page, int pageSize);
}
