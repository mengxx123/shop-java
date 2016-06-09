package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.service.ITownService;

/**
 * 镇相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
public class TownApiController {

	@Resource(name = "townService")
	private ITownService townService;

	@RequestMapping("counties/{id}/towns")
	public Object towns(@PathVariable("id") Integer code) {
		return townService.getByCountyCode(code);
	}
	
}
