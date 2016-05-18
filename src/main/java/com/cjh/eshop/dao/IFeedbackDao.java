package com.cjh.eshop.dao;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Feedback;

public interface IFeedbackDao extends IBaseDao<Feedback, String> {
	public PageInfo<Feedback> getAllFeedback(int page, int pageSize);
}
