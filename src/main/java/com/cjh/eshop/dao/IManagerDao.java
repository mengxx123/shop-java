package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Manager;

public interface IManagerDao extends IBaseDao<Manager, String> {
	public Manager getManagerByAccount(String account);
	public PageInfo<Manager> getAllByPage(int pageNo, int pageSize);
	public Manager getByAccount(String account);
	public void updateBySelective(Manager manager);
}
