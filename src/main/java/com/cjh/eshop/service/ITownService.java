package com.cjh.eshop.service;

import java.util.List;

import com.cjh.eshop.model.Town;
import com.cjh.eshop.service.common.IBaseService;

public interface ITownService extends IBaseService<Town, Integer> {
    public List<Town> getByCountyCode(Integer code);
}
