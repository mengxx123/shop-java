package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.City;
import com.cjh.eshop.service.common.IBaseService;

public interface ICityService extends IBaseService<City, Integer> {
    public List<City> getByProvinceCode(Integer code);
}
