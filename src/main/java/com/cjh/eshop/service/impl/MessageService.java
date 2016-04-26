package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IMessageDao;
import com.cjh.eshop.dao.IUserDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Message;
import com.cjh.eshop.service.IMessageService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class MessageService extends BaseService<Message, String> implements IMessageService {
	
	@Resource(name = "messageDao")
	private IMessageDao dao;
	
	@Resource(name = "userDao")
	private IUserDao userDao;

	@Override
	protected IBaseDao<Message, String> getDao() {
		return dao;
	}

	@Override
	public PageInfo<Message> getByUserId(String userId, int pageNo, int pageSize) {
		// TODO 待优化
		PageInfo<Message> pageInfo = dao.getByUserId(userId, pageNo, pageSize);
		List<Message> messages = pageInfo.getResult();
		for (Message message : messages) {
			message.setUser(userDao.getById(message.getUser().getId()));
		}
		return pageInfo;
	}

	@Override
	public void updateByIdSelective(Message message) {
		dao.updateByIdSelective(message);
	}

	@Override
	public PageInfo<Message> getByExample(Message message, int pageNo,
			int pageSize) {
		// TODO 待优化
		PageInfo<Message> pageInfo = dao.getByExample(message, pageNo, pageSize);
		List<Message> messages = pageInfo.getResult();
		for (Message msg : messages) {
			msg.setUser(userDao.getById(msg.getUser().getId()));
		}
		return pageInfo;
	}

}
