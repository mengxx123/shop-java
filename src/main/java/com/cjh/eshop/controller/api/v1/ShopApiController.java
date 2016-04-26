package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.model.Shop;
import com.cjh.eshop.service.IShopService;
import com.cjh.eshop.util.StateCode;

/**
 * 商品相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
@RequestMapping("api/v1.0")
public class ShopApiController {

	@Resource(name = "shopService")
	private IShopService shopService;

	@RequestMapping("shops/{id}")
	public Object getShop(@PathVariable("id") String id) {
		return shopService.getById(id);
	}

	@RequestMapping(value = "shops", method = RequestMethod.POST)
	public Object saveBooks(@RequestBody Shop shop) {
		try {
			shopService.save(shop);
		} catch (Exception e) {
			e.printStackTrace();

			JSONObject json = new JSONObject();
			json.put("state", "error");
			json.put("code", 0);
			json.put("message", StateCode.getMessage(0));
			return json;
		}
		JSONObject json = new JSONObject();
		json.put("state", "success");
		json.put("code", 0);
		json.put("message", "save success");
		return json;
	}

	@RequestMapping(value = "shops/{id}", method = RequestMethod.PUT)
	public Object updateBooks(@PathVariable("id") String id, @RequestBody Shop shop) {
		
		shop.setId(id);
		
		try {
			shopService.update(shop);
		} catch (Exception e) {
			e.printStackTrace();

			JSONObject json = new JSONObject();
			json.put("state", "error");
			json.put("code", 0);
			json.put("message", StateCode.getMessage(0));
			return json;
		}

		JSONObject json = new JSONObject();
		json.put("state", "success");
		json.put("code", 0);
		json.put("message", "update success");
		return json;
	}

	@RequestMapping(value = "shops/{id}", method = RequestMethod.DELETE)
	public Object deleteBooks(@PathVariable("id") String id) {
		try {
			shopService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();

			JSONObject json = new JSONObject();
			json.put("state", "error");
			json.put("code", 0);
			json.put("message", StateCode.getMessage(0));
			return json;
		}

		JSONObject json = new JSONObject();
		json.put("state", "success");
		json.put("code", 0);
		json.put("message", "delete success");
		return json;
	}
}
