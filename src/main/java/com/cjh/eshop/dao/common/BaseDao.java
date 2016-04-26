package com.cjh.eshop.dao.common;

import java.io.Serializable;

public abstract class BaseDao<T, ID extends Serializable> implements IBaseDao<T, ID> {

	protected final String ASD = "com.cjh.eshop.mapping."; // TODO
}
