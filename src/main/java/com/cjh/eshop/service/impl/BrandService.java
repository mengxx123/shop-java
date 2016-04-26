package com.cjh.eshop.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.Config;
import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IBrandDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Brand;
import com.cjh.eshop.service.IBrandService;
import com.cjh.eshop.service.common.BaseService;
import com.cjh.eshop.util.FileUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public class BrandService extends BaseService<Brand, String> implements IBrandService {
	
	@Resource(name = "brandDao")
	private IBrandDao dao;

	@Override
	protected IBaseDao<Brand, String> getDao() {
		return dao;
	}
	
	@Override
	public PageInfo<Brand> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		if (keyword == null || keyword.equals("")) {
			return dao.getAllByPage(pageNo, pageSize);
		} else {
			return dao.getAllByKeyword(keyword, pageNo, pageSize);
		}
	}

	

	@Override
	public PageInfo<Brand> getByExample(Brand brand, int pageNo, int pageSize) {
		return dao.getByExample(brand, pageNo, pageSize);
	}

	@Override
	public void deleteById(String id) {
		Brand brand = dao.getById(id);
		if (brand == null) {
			//throw new NotFoundException(); // TODO 抛出异常
		}
		
		String iconFilename = brand.getIcon();
		dao.deleteById(id);
		String dirPath = Config.getInstance().getRealPath(Constant.PATH_BRAND_IMAGE);
		String path = dirPath + File.separator + iconFilename;
		FileUtil.deleteFile(path);
	}

}
