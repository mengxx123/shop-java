package com.cjh.eshop.model;

import java.io.Serializable;

/**
 * 县、县级市
 */
public class County implements Serializable {

    private static final long serialVersionUID = -8561699080871973286L;

    private Integer id;
    private Integer code;
    private String name;
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}