package com.cjh.eshop.controller.api.v1;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.model.GoodsComment;
import com.cjh.eshop.service.impl.GoodsCommentService;

@RestController
@RequestMapping("api/v1.0")
public class GoodsCommentApiController {
	private static Logger logger = LoggerFactory.getLogger(GoodsCommentApiController.class);
	
	@Resource(name="goodsCommentService")
	private GoodsCommentService goodsCommentService;
	
	@RequestMapping("all_comment")
	public Object allComment(HttpServletRequest request, ModelMap modelMap) {
		String goodsId = request.getParameter("goodsId");

//		try {
//			goodsId = Integer.parseInt(request.getParameter("goodsId"));
//		} catch (Exception e) {
//			System.out.println("查询不到商品");
//			modelMap.put("smg", "找不到");
//			return new JsonResult<String>(StateCode.ERROR, "商品ID为空");
//		}
//		
		// 获取商品评论
		List<GoodsComment> comments = goodsCommentService.getGoodsCommentsByGoodsId(goodsId, 1, 20);
		return comments;
	}

}
