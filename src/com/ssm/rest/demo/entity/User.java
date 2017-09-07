package com.ssm.rest.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class User implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 12L;

	private Integer id;

    @NotEmpty
    private String userName;
    
    @NotEmpty
    private String password;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}