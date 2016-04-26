package com.cjh.eshop.service;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.model.Feedback;
import com.cjh.eshop.service.common.IBaseService;

public interface IFeedbackService extends IBaseService<Feedback, Integer> {
	public PageInfo<Feedback> getAllFeedback(int pageNo, int pageSize);
}
