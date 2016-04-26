package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Manager;
import com.cjh.eshop.service.common.IBaseService;

public interface IManagerService extends IBaseService<Manager, String> {
	/**
	 * 登陆
	 * @param account 用户名
	 * @param md5Pwd MD5加密后的密码
	 * @return 是否登陆成功
	 */
	public boolean login(String account, String md5Pwd);
	
	public PageInfo<Manager> getAllByPage(int pageNo, int pageSize);
	public Manager getByAccount(String account);
	public void updateBySelective(Manager manager);
	public void save(List<Manager> managers);
}
