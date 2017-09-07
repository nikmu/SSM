package com.ssm.rest.demo.model;

public class TokenModel {

	private String userName;
	
	private String token;

	public TokenModel(String userName, String token) {
		super();
		this.userName = userName;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userId) {
		this.userName = userId;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "TokenModel [userName=" + userName + ", token=" + token + "]";
	}
}
