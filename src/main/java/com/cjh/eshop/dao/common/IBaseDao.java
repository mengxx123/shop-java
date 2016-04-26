package com.cjh.eshop.dao.common;

import java.io.Serializable;

public interface IBaseDao<T, ID extends Serializable> {
	public T getById(ID id);
	public ID save(T entity);
	public void update(T entity);
	public void deleteById(ID id);
}
