package com.ssm.rest.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.rest.demo.common.Response;
import com.ssm.rest.demo.common.WebContext;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.TokenModel;
import com.ssm.rest.demo.security.IgnoreSecurity;
import com.ssm.rest.demo.security.SecurityAspect;
import com.ssm.rest.demo.security.TokenManager;
import com.ssm.rest.demo.service.IUserService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(method = RequestMethod.POST)
	@IgnoreSecurity
	public Response login(@RequestBody @Valid User user) {
		User user2 = userService.getUserByName(user.getUserName());
		if(user2 == null || !user2.getPassword().equals(user.getPassword())) {
			return new Response().failure();
		}
		
		TokenModel model = tokenManager.createToken(user.getUserName());
		return new Response().success(model);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Response logout() {
		tokenManager.deleteToken(WebContext.getRequest().getHeader("X-Token"));
		return new Response().success();
	}
}
