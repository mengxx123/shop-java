package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.County;

public interface ICountyDao extends IBaseDao<County, Integer> {
    public List<County> getByCityCode(Integer code);
}
