package com.cjh.eshop.controller.common;

import javax.servlet.http.HttpServletRequest;

import com.cjh.eshop.common.Constant;

public class BaseApiController {
    
    protected boolean isLogined(HttpServletRequest request) {
        return request.getSession().getAttribute(Constant.SESSION_UID) != null;
    }
}
