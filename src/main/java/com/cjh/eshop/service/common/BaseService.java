package com.cjh.eshop.service.common;

import java.io.Serializable;

import com.cjh.eshop.dao.common.IBaseDao;

public abstract class BaseService<T, ID extends Serializable> implements IBaseService<T, ID> {
	
	protected abstract IBaseDao<T, ID> getDao();
	
	@Override
	public T getById(ID id) {
		return getDao().getById(id);
	}

	@Override
	public ID save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void update(T entity) {
		getDao().update(entity);
	}

	@Override
	public void deleteById(ID id) {
		getDao().deleteById(id);
	}

}
