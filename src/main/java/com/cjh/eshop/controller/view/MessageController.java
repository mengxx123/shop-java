package com.cjh.eshop.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Message;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IMessageService;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 文章相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class MessageController extends BaseController {
	
	@Resource(name = "messageService")
	private IMessageService messageService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	private static final String VIEW_MESSAGE = "message/messages";
	private static final String VIEW_MESSAGE2 = "message/messages2";
	private static final String VIEW_CHAT = "message/chat";
	
	@RequestMapping("messages/{id}")
	@ResponseBody
    public Object messages(@PathVariable("id") String messageId, HttpServletRequest request, 
    		ModelMap modelMap, HttpServletResponse response) {
		
		commonInit(request, modelMap);
		
		Message message = messageService.getById(messageId);
		
		return message;
	}
	
	@RequestMapping("messages/{id}/read")
	@ResponseBody
    public Object updateMessage(@PathVariable("id") String messageId, HttpServletRequest request, 
    		ModelMap modelMap, HttpServletResponse response) {
		
		commonInit(request, modelMap);
		
		// TODO 权限验证
		
		if (TextUtil.isEmpty(messageId)) {
			return new JsonResult<String>(StateCode.ERROR, "请求失败，参数id不能为空");
		}
		
		try {
			Message message = new Message();
			message.setId(messageId);
			message.setIsRead(1); // 标记为已读
			messageService.updateByIdSelective(message);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "服务器出错");
		}
		
	}
	
	@RequestMapping("messages/{id}/delete")
	@ResponseBody
    public Object deleteMessage(@PathVariable("id") String messageId, HttpServletRequest request, 
    		ModelMap modelMap, HttpServletResponse response) {
		
		commonInit(request, modelMap);
		
		// TODO 权限验证
		
		if (TextUtil.isEmpty(messageId)) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		
		try {
			messageService.deleteById(messageId);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败");
		}
		
	}
	
	@RequestMapping("users/{id}/messages")
    public String topics212(@PathVariable("id") String userId, HttpServletRequest request, 
    		ModelMap modelMap, HttpServletResponse response) {
		
		commonInit(request, modelMap);
		
		PageInfo<Message> pageInfo = messageService.getByUserId(userId, 1, 10);
		modelMap.put("page", pageInfo);
		
		return VIEW_MESSAGE;
	}
	
	@RequestMapping("chat")
	public String manager(HttpSession session, HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "from") String from) {
		
		return VIEW_CHAT;
	}
	
	@RequestMapping("users/{id}/messages/{friendId}")
    public String topics212212122(@PathVariable("id") String userId, @PathVariable("friendId") String friendId, HttpServletRequest request, 
    		ModelMap modelMap, HttpServletResponse response) {
		
		commonInit(request, modelMap);
		
		Message message = new Message();
		message.setUser(new User(userId));
		message.setSendUser(new User(friendId));
		
		PageInfo<Message> pageInfo = messageService.getByExample(message, 1, 10);
		System.out.println("大小"+pageInfo.getResult().size());
		modelMap.put("page", pageInfo);
		
		User sendUser = userService.getById(friendId);
		modelMap.put("sendUser", sendUser);
		
		return VIEW_MESSAGE2;
	}
}
