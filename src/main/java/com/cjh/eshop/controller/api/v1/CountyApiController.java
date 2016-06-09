package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.ICountyService;

/**
 * 县相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
public class CountyApiController {

	@Resource(name = "countyService")
	private ICountyService countyService;

	@RequestMapping("cities/{id}/counties")
	public Object provinces(@PathVariable("id") Integer code) {
		return countyService.getByCityCode(code);
	}
	
}
