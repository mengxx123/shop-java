package com.cjh.eshop.dao;

import java.util.List;

import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Province;

public interface IProvinceDao extends IBaseDao<Province, Integer> {
    public List<Province> getAll();
}
