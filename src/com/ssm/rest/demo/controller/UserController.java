package com.ssm.rest.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.rest.demo.common.Response;
import com.ssm.rest.demo.common.WebContext;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.UserModel;
import com.ssm.rest.demo.security.IgnoreSecurity;
import com.ssm.rest.demo.security.impl.DefaultTokenManager;
import com.ssm.rest.demo.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Resource
	private DefaultTokenManager tokenManager;
	
	@IgnoreSecurity
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Response createUser(@RequestBody @Valid User user) {
		//do something to create user;
		return null;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Response searchAllUser() {
		
		return null;
	}
		
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@IgnoreSecurity
	public Response searchUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		Response response = new Response();
		String token = tokenManager.createToken(user.getUserName());
		Cookie cookie = new Cookie("X-Token", token);
		WebContext.getResponse().addCookie(cookie);
		response.success(user);
		return response;
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public Response updateUserById(@PathVariable Long id, @RequestBody UserModel userModel) {
		//do something to update user by id;
		return null;
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Response deleteUserById(@PathVariable Long id) {
		//do something to delete user by id;
		return null;
	}
}
