package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.County;
import com.cjh.eshop.service.common.IBaseService;

public interface ICountyService extends IBaseService<County, Integer> {
    public List<County> getByCityCode(Integer code);
}
