package com.ssm.rest.demo.model;

import javax.validation.constraints.NotBlank;

public class UserModel {

	private String username;
	private String password;
	private String age;
	
	public UserModel() {
		
	}
	
	public UserModel(String username, String password, String age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}

	@NotBlank(message="username can't empty!")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", password=" + password + ", age=" + age + "]";
	}
	
}
