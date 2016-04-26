package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsComment;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.impl.GoodsCommentService;
import com.cjh.eshop.service.impl.GoodsService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;
import com.cjh.eshop.util.Validation;

@Controller
public class GoodsCommentController extends BaseController {
	
	@Resource(name="goodsCommentService")
	private GoodsCommentService goodsCommentService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	// 添加评论
	@ResponseBody
	@RequestMapping(value = "goods_comments", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request, 
    		ModelMap modelMap,
    		@RequestParam(value="content", required = false) String content,
    		@RequestParam(value="goods_id", required = false) String goodsId) {
		
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_UID);
		if (userId == null) {
			return new JsonResult<String>(StateCode.ERROR, "用户未登录，没有操作权限");
		}

		if (TextUtil.isEmpty(content)) {
			return new JsonResult<String>(StateCode.ERROR, "评论内容不能为空");
		}
		
		if (!Validation.isValid(content)) {
			return new JsonResult<String>(StateCode.ERROR, "评论内容包含垃圾、广告、违法内容");
		}
		
		GoodsComment comment = new GoodsComment();
		comment.setContent(content);
		comment.setScore(3);
		comment.setGoods(new Goods(goodsId));
		comment.setUser(new User(userId));
		
		try {
			goodsCommentService.save(comment);
			
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "服务器异常");
		}
	}
}
