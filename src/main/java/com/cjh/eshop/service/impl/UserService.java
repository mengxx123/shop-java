package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.service.common.BaseService;
import com.cjh.eshop.util.PasswordUtil;

@Service("userService")
@Transactional(rollbackFor = { Exception.class })
public class UserService extends BaseService<User, String> implements IUserService {
	
	@Resource(name = "userDao")
	private IUserDao dao;

	@Override
	protected IBaseDao<User, String> getDao() {
		return dao;
	}
	
	@Override
	public boolean login(String account, String md5Password) {
		User user = dao.getUserByUserName(account);
		if (user == null) {
			return false;
		} else {
			String pwd = user.getPassword();
			
			return pwd.equals(PasswordUtil.encodeMd5(md5Password));
		}
	}

	@Override
	public User getUserByUserName(String userName) {
		return dao.getUserByUserName(userName);
	}

	@Override
	public User getUserByUserEmail(String email) {
		return dao.getUserByUserEmail(email);
	}

	@Override
	public User getUserByUserNickname(String nickname) {
		return dao.getUserByUserNickname(nickname);
	}

	@Override
	public PageInfo<User> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		if (keyword == null || keyword.equals("")) {
			return dao.getAllByPage(pageNo, pageSize);
		} else {
			return dao.getAllByKeyword(keyword, pageNo, pageSize);
		}
	}
}
