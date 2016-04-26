package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Message;

public interface IMessageDao extends IBaseDao<Message, String> {
	public PageInfo<Message> getByUserId(String userId, int pageNo, int pageSize);
	public PageInfo<Message> getByExample(Message message, int pageNo, int pageSize);
	public void updateByIdSelective(Message message);
}
