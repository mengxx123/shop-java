package com.cjh.eshop.model;

import java.io.Serializable;

/**
 * 省
 */
public class Province implements Serializable {

    private static final long serialVersionUID = -8561699080871973286L;

    private Integer id;
    private Integer code;
    private String name;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}