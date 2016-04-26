package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Feedback;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IFeedbackService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 反馈管理相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class FeedbackController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	@Resource(name = "feedbackService")
	private IFeedbackService feedbackService;
	
	private static final String VIEW_ADMIN_FEEDBACK = "feedback/admin_feedback";
	private static final String VIEW_FEEDBACK = "feedback/feedback";
	
	@RequestMapping("admin/feedbacks")
	public String feedback(HttpSession session, HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value="page", defaultValue = "1") Integer page,
			@RequestParam(value="keyword", required = false) String keyword) {
		
		modelMap.put("keyword", keyword);
		
		final int PAGE_SIZE = 5;
		
		PageInfo<Feedback> pageInfo = feedbackService.getAllFeedback(page, PAGE_SIZE);
		modelMap.put("page", pageInfo);
		
		return VIEW_ADMIN_FEEDBACK;
	}
	
	@RequestMapping("feedbacks")
	public String feedback2(HttpServletRequest request, ModelMap modelMap) {
		
		commonInit(request, modelMap);
		
		return VIEW_FEEDBACK;
	}
	
	@RequestMapping(value = "feedbacks", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateBrand(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value="content", required = false) String content) {
		
		if (TextUtil.isEmpty(content)) {
			return new JsonResult<String>(StateCode.ERROR, "内容不能为空");
		}
		
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_UID);
		if (userId == null) {
			return new JsonResult<String>(StateCode.ERROR, "用户未登录，没有操作权限");
		}
		
		Feedback feedback = new Feedback();
		feedback.setContent(content);
		feedback.setUser(new User(userId));
		feedback.setType(0);
		
		try {
			feedbackService.save(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存反馈失败", e);
			return new JsonResult<String>(StateCode.ERROR, "服务器出错");
		}
		
		return new JsonResult<String>(StateCode.SUCCESS, "");
	}
	
	@RequestMapping("feedbacks_delete")
	@ResponseBody
	public Object getShop(@RequestParam(value="id") Integer id) {
		// TODO 安全验证 成功或失败
		feedbackService.deleteById(id);
		return new JsonResult<String>(StateCode.SUCCESS, "");
	}
}