package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.common.IBaseService;

public interface IUserService extends IBaseService<User, String>{

	/**
	 * 用户登陆
	 * @param account 用户名
	 * @param md5Pwd MD5加密后的密码
	 * @return
	 */
	public boolean login(String account, String md5Pwd);
	public User getUserByUserName(String userName);
	public User getUserByUserEmail(String email);
	public User getUserByUserNickname(String nickname);
	public PageInfo<User> getAllByKeyword(String keyword, int pageNo, int pageSize);
}
