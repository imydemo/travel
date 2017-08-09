package org.vie.mydemo520.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vie.mydemo520.service.sys.UserService;

/*
 * 第一步：收集用户身份和证明
 * UsernamePasswordToken token = new UsernamePasswordToken(username, password);
 * token.setRememberMe(true);
 * 第二步：提交身份和证明
 * Subject currentUser =SecurityUtils.getSubject();
 * currentUser.login(token);
 * 第三步：处理成功或失败
 * 当login函数没有返回信息时表明验证通过了。程序可以继续运行，
 * 此时执行SecurityUtils.getSubject()将返回验证后的Subject实例，
 * subject.isAuthenticated()将返回true
 */
@Controller
public class LoginController {
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, Model model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		// 获取登录错误信息
		String authExp = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String error = null;
	    if (LockedAccountException.class.getName().equals(authExp)) {
			error = "用户帐号被锁定，请重试";
		} else {
			error = "用户名或密码错误, 请重试";
		}
		model.addAttribute("error", error);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return "login";
	}
}
