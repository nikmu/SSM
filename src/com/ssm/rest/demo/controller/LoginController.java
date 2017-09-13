package com.ssm.rest.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.rest.demo.common.Response;
import com.ssm.rest.demo.common.WebContext;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.TokenModel;
import com.ssm.rest.demo.security.IgnoreSecurity;
import com.ssm.rest.demo.security.TokenManager;
import com.ssm.rest.demo.service.IUserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(method = RequestMethod.POST)
	@IgnoreSecurity
	public Response login(@RequestBody @Valid User user,HttpServletRequest req) {
		Response response = new Response();
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
			try {
				subject.login(token);
			}catch (UnknownAccountException | IncorrectCredentialsException e) {
				return response.failure("用户名/密码错误");
			}catch (AuthenticationException  e) {
				return response.failure(e.getMessage());
			}
		}
		TokenModel tokenModel = tokenManager.createToken(user.getUsername());
		return response.success(tokenModel);
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	@IgnoreSecurity
//	public ModelAndView showLoginForm(HttpServletRequest req, Model model) {
//        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
//        String error = null;
//        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(exceptionClassName != null) {
//            error = "其他错误：" + exceptionClassName;
//        }
//        model.addAttribute("error", error);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Response logout() {
		tokenManager.deleteToken(WebContext.getRequest().getHeader("X-Token"));
		return new Response().success();
	}
}
