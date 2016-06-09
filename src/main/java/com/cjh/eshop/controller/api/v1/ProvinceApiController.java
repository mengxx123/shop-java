package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.IProvinceService;

/**
 * 省相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
public class ProvinceApiController {

	@Resource(name = "provinceService")
	private IProvinceService provinceService;

	@RequestMapping("provinces")
    public Object provinces() {
        return provinceService.getAll();
    }
	
	@RequestMapping("provinces/{id}")
	public Object provinces(@PathVariable("id") Integer id) {
		return provinceService.getById(id);
	}
	
}
