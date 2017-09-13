package com.ssm.rest.demo.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.rest.demo.common.Response;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.UserModel;
import com.ssm.rest.demo.security.IgnoreSecurity;
import com.ssm.rest.demo.service.IOrganizationService;
import com.ssm.rest.demo.service.IRoleService;
import com.ssm.rest.demo.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IRoleService roleService;
	
	@RequiresPermissions("user:create")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Response createUser(@RequestBody @Valid User user) {
		userService.addUser(user);
		return new Response().success(user);
	}
	
	@RequiresPermissions("user:view")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Response searchAllUser() {
		return new Response().success(userService.findAll());
	}
	
	@RequiresPermissions("user:view")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Response searchUserById(@PathVariable Long id) {
		User user = userService.findById(id);
		Response response = new Response();
		response.success(user);
		return response;
	}
	
	@RequiresPermissions("user:update")//?
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public Response updateUserById(@PathVariable Long id, @RequestBody User user) {
		userService.updateUser(user);
		return new Response().success(user);
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Response deleteUserById(@PathVariable Long id) {
		userService.deleteUser(id);
		return new Response().success();
	}
}
