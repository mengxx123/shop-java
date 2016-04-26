package com.cjh.eshop.controller.api.v1;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.model.Goods;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.util.StateCode;


/**
 * 店铺相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
@RequestMapping("api/v1.0")
public class GoodsApiController {

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@RequestMapping(value = "goods/{id}")
	public Object getGoods(@PathVariable("id") int id) {
		return goodsService.getById(String.valueOf(id));
	}

	@RequestMapping(value = "goods", method = RequestMethod.POST)
	public Object saveBooks(@RequestBody Goods goods) {
		try {
			goodsService.save(goods);
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

	@RequestMapping(value = "goods/{id}", method = RequestMethod.PUT)
	public Object updateBooks(@PathVariable("id") int id, @RequestBody Goods goods) {
		
		goods.setId(String.valueOf(id));
		
		try {
			goodsService.update(goods);
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

	@RequestMapping(value = "goods/{id}", method = RequestMethod.DELETE)
	public Object deleteBooks(@PathVariable("id") int id) {
		try {
			goodsService.deleteById(String.valueOf(id));
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
