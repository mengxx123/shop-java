package com.cjh.eshop.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.Config;
import com.cjh.eshop.dao.IGoodsImageDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.service.IGoodsImageService;
import com.cjh.eshop.service.common.BaseService;
import com.cjh.eshop.util.FileUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public class GoodsImageService extends BaseService<GoodsImage, String> implements IGoodsImageService {
	
	@Resource(name = "goodsImageDao")
	private IGoodsImageDao dao;

	@Override
	protected IBaseDao<GoodsImage, String> getDao() {
		return dao;
	}

	@Override
	public List<GoodsImage> getGoodsImagesByGoodsId(String goodsId) {
		return dao.getGoodsImagesByGoodsId(goodsId);
	}

	@Override
	public void deleteById(String imageId) {
		GoodsImage goodsImage = getById(imageId);
		String imagePath = Config.getInstance().getRealPath("/");
		String path = imagePath + File.separator + goodsImage.getUrl();
		System.out.println("删除文件：" + path);
		FileUtil.deleteFile(path);
		dao.deleteById(imageId);
	}
}
