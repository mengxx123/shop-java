package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.dao.IGoodsCommentDao;
import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsComment;
import com.cjh.eshop.service.IGoodsCommentService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class GoodsCommentService extends BaseService<GoodsComment, String> implements IGoodsCommentService {
	
	@Resource(name = "goodsCommentDao")
	private IGoodsCommentDao dao;

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	@Override
	protected IBaseDao<GoodsComment, String> getDao() {
		return dao;
	}

	@Override
	public List<GoodsComment> getGoodsCommentsByGoodsId(String id, int page, int pageSize) {
		List<GoodsComment> comments = dao.getGoodsCommentsByGoodsId(id, page, pageSize);
		
		for (GoodsComment comment : comments) {
			comment.setUser(userDao.getById(comment.getUser().getId()));
		}
		return comments;
	}

	@Override
	public List<GoodsComment> getGoodCommentsByGoodsId(String id, int page,
			int pageSize) {
		return dao.getGoodCommentsByGoodsId(id, page, pageSize);
	}

	@Override
	public List<GoodsComment> getNomalCommentsByGoodsId(String id, int page,
			int pageSize) {
		return dao.getNomalCommentsByGoodsId(id, page, pageSize);
	}

	@Override
	public List<GoodsComment> getBadCommentsByGoodsId(String id, int page,
			int pageSize) {
		return dao.getBadCommentsByGoodsId(id, page, pageSize);
	}

}
