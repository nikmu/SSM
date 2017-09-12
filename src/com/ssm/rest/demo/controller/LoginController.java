package com.ssm.rest.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	private IUserService userService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(method = RequestMethod.POST)
	@IgnoreSecurity
	public Response login(@RequestBody @Valid User user, HttpServletRequest req, HttpServletResponse res) {
//		User user2 = userService.findByUsername(user.getUsername());
//		if(user2 == null || !user2.getPassword().equals(user.getPassword())) {
//			return new Response().failure();
//		}
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
//        model.addAttribute("error", error);
//        return "login";
//		TokenModel model = tokenManager.createToken(user.getUsername());
//		return new Response().success(model);
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Response logout() {
		tokenManager.deleteToken(WebContext.getRequest().getHeader("X-Token"));
		return new Response().success();
	}
}
