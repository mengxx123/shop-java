package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IManagerDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Manager;
import com.cjh.eshop.service.IManagerService;
import com.cjh.eshop.service.common.BaseService;
import com.cjh.eshop.util.PasswordUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public class ManagerService extends BaseService<Manager, String> implements IManagerService {
	
	@Resource(name = "managerDao")
	private IManagerDao dao;

	@Override
	protected IBaseDao<Manager, String> getDao() {
		return dao;
	}

	@Override
	public boolean login(String account, String md5Password) {
		Manager manager = dao.getManagerByAccount(account);
		if (manager == null) {
			return false;
		}
		return manager.getPassword().equals(PasswordUtil.encodeMd5(md5Password));
	}

	@Override
	public PageInfo<Manager> getAllByPage(int pageNo, int pageSize) {
		return dao.getAllByPage(pageNo, pageSize);
	}

	@Override
	public Manager getByAccount(String account) {
		return dao.getByAccount(account);
	}

	@Override
	public void save(List<Manager> managers) {
		for (Manager manager : managers) {
			dao.save(manager);
		}
	}

	@Override
	public void updateBySelective(Manager manager) {
		dao.updateBySelective(manager);
	}
}
