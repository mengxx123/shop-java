package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.ICityService;

/**
 * 市相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
public class CityApiController {

	@Resource(name = "cityService")
	private ICityService cityService;

	@RequestMapping("provinces/{id}/cities")
	public Object provinces(@PathVariable("id") Integer code) {
		return cityService.getByProvinceCode(code);
	}
	
}
