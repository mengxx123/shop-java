package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.User;

public interface IUserDao extends IBaseDao<User, String> {
	public PageInfo<User> getAllByPage(int pageNo, int pageSize);
	/** 根据用户名获取用户信息 */
	public User getUserByUserName(String userName);
	public User getUserByUserEmail(String email);
	public User getUserByUserNickname(String nickname);
	public PageInfo<User> getAllByKeyword(String keyword, int pageNo, int pageSize);
}
