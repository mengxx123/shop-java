package com.cjh.eshop.controller.api.v1;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjh.eshop.common.Config;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.EmailUtil;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.PasswordUtil;
import com.cjh.eshop.util.StateCode;


/**
 * 注册相关的接口
 * 
 * @author 陈建杭
 * 
 */
@RestController
@RequestMapping("api/v1.0")
public class RegisterApiController {

	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Object registPost2(HttpServletRequest request, Map map) {
		  
		String account = request.getParameter("account");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String checkCode = request.getParameter("checkCode");
		
		if (account == null || account.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "账号不能为空");
		}
		if (nickname == null || nickname.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "昵称不能为空");
		}
		if (password == null || password.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "密码不能为空");
		}
		
		if (email == null || email.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "邮箱不能为空");
		}
		
		if (Config.NEED_EMAIL_VALID && (checkCode == null || checkCode.equals(""))) {
			return new JsonResult<String>(StateCode.ERROR, "验证码不能为空");
		}
		
		//System.out.println("账号：" + account);
		if (userService.getUserByUserName(account) != null) {
			User user = userService.getUserByUserName(account);
			System.out.println("存在：" + user.getId());
			return new JsonResult<String>(StateCode.ERROR, "账号已存在");
		}
		if (userService.getUserByUserNickname(nickname) != null) {
			return new JsonResult<String>(StateCode.ERROR, "昵称已存在");
		}
		
		// TODO 
//      Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email); 
        if (!m.matches()) {
        	return new JsonResult<String>(StateCode.ERROR, "邮箱格式不正确");
        }
        
		if (userService.getUserByUserEmail(email) != null) {
			return new JsonResult<String>(StateCode.ERROR, "邮箱已经被注册过");
		}
		
		if (Config.NEED_EMAIL_VALID) {
			String checkCode2 = (String) request.getSession().getValue(email);
			System.out.println("验证码：" + checkCode2);
			//System.out.println("eamil：" + (String) request.getSession().getAttribute(name));
			if (checkCode2 == null || !checkCode.equals(checkCode2)) {
				return new JsonResult<String>(StateCode.ERROR, "验证码不正确");
			}
		}
		
		
		User user = new User();
		user.setName(account);
		user.setNickname(nickname);
		user.setPassword(PasswordUtil.encode(password));
		user.setEmail(email);
		userService.save(user);
		
		return new JsonResult<String>(StateCode.SUCCESS, "");
	}

	@RequestMapping(value = "checkCode", method = RequestMethod.GET)
	public Object registPost222(HttpServletRequest request, Map map) {
		  
		String email = request.getParameter("email");
		
		if (email == null || email.equals("")) {
			return new JsonResult<String>(StateCode.ERROR, "邮箱不能为空");
		}
		Random random1 = new Random(System.currentTimeMillis());
		String checkCode = Integer.toString(Math.abs(random1.nextInt() % 10000));
		
		System.out.println("注册邮箱：" + email);
		//request.getSession()
		request.getSession().putValue(email, checkCode);
		request.getSession().putValue("email", "123");
		EmailUtil.sendEmail(email, "注册邮箱验证", "这是验证码：" + checkCode);
		
		return new JsonResult<String>(StateCode.SUCCESS, "");
		
	}
}
