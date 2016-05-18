package com.cjh.eshop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IFeedbackDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Feedback;
import com.cjh.eshop.service.IFeedbackService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class FeedbackService extends BaseService<Feedback, String> implements IFeedbackService {
	
	@Resource(name = "feedbackDao")
	private IFeedbackDao dao;

	@Override
	protected IBaseDao<Feedback, String> getDao() {
		return dao;
	}

	@Override
	public PageInfo<Feedback> getAllFeedback(int page, int pageSize) {
		return dao.getAllFeedback(page, pageSize);
	}
}
