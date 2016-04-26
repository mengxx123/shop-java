package com.cjh.eshop.controller.api.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjh.eshop.controller.common.BaseController;

@Controller
public class ApiController extends BaseController {
	
	private static final String URL_API = "api/api"; // API 文档首页
	
	/**
	 * 根据教材ID获取该教材的json数据
	 * <p>
	 * API: /books/id 例：/books/1 <br>
	 * Method: get
	 * </p>
	 * 
	 * @param id
	 *            教材的ID
	 * @return Address对象的Json数据
	 */
	@RequestMapping("api/v1")
	public String getApi() {
		return URL_API;
	}
}
