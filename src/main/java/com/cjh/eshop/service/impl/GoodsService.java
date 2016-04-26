package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IGoodsCollectionDao;
import com.cjh.eshop.dao.IGoodsCommentDao;
import com.cjh.eshop.dao.IGoodsDao;
import com.cjh.eshop.dao.IGoodsImageDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.service.IGoodsImageService;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.service.common.BaseService;

@Service("goodsService")
@Transactional(rollbackFor = Exception.class)
public class GoodsService extends BaseService<Goods, String> implements IGoodsService {
	
	@Resource(name = "goodsDao")
	private IGoodsDao dao;
	
	@Resource(name = "goodsImageDao")
	private IGoodsImageDao imageDao;

	@Resource(name = "goodsCommentDao")
	private IGoodsCommentDao goodsCommentDao;
	
	@Resource(name = "goodsCollectionDao")
	private IGoodsCollectionDao goodsCollectionDao;
	
	@Resource(name = "goodsImageService")
	private IGoodsImageService goodsImageService;
	
	@Override
	protected IBaseDao<Goods, String> getDao() {
		return dao;
	}

	@Override
	public boolean increaseClickCount(String goodsId) {
		return dao.increaseClickCount(goodsId);
	}

	@Override
	public PageInfo<Goods> getGoodsesByShopId(String id, int page, int pageSize) {
		return dao.getGoodsesByShopId(id, page, pageSize);
	}

	@Override
	public String saveSelective(Goods goods) {
		return dao.saveSelective(goods);
	}

	@Override
	public PageInfo<Goods> getGoodses(int page, int pageSize) {
		return dao.getAllByPage(page, pageSize);
	}

	@Override
	public PageInfo<Goods> getGoodsesByCategoryId(String id, int pageNo,
			int pageSize) {
		return dao.getGoodsesByCategoryId(id, pageNo, pageSize);
	}

	@Override
	public void deleteById(String id) {
		
		// 删除数据库中的商品图片及商品图片文件
		List<GoodsImage> images = imageDao.getGoodsImagesByGoodsId(id);
		for (GoodsImage img : images) {
			goodsImageService.deleteById(img.getId());
		}

		// 删除商品相关的评论和收藏
		goodsCommentDao.deleteByGoodsId(id);
		goodsCollectionDao.deleteByGoodsId(id);
		
		dao.deleteById(id);
	}

	@Override
	public PageInfo<Goods> getByExample(Goods goods, int pageNo, int pageSize) {
		return dao.getByExample(goods, pageNo, pageSize);
	}
}
