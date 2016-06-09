package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.City;

public interface ICityDao extends IBaseDao<City, Integer> {
    public List<City> getByProvinceCode(Integer code);
}
