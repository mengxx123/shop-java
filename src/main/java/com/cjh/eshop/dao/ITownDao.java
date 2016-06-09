package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Town;

public interface ITownDao extends IBaseDao<Town, Integer> {
    public List<Town> getByCountyCode(Integer code);
}
