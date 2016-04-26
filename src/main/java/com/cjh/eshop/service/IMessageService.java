package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Message;
import com.cjh.eshop.service.common.IBaseService;

public interface IMessageService extends IBaseService<Message, String> {
	public PageInfo<Message> getByUserId(String userId, int pageNo, int pageSize);
	public PageInfo<Message> getByExample(Message message, int pageNo, int pageSize);
	public void updateByIdSelective(Message message);
}
