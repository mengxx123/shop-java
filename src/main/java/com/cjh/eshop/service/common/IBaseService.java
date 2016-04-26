package com.cjh.eshop.service.common;

import java.io.Serializable;

import com.cjh.eshop.dao.common.IBaseDao;

public interface IBaseService<T, PK extends Serializable> extends IBaseDao<T, PK> {

}
