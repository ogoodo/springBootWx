package com.ogoodo.wx.api.test.shiro.controller;

import io.swagger.annotations.ApiModelProperty;

public class User {
	// 用户名
	@ApiModelProperty(notes = "用户名", required = true) 
	private String username;
	// 密码
	@ApiModelProperty(notes = "密码", required = true) 
	private String password;
	// 记住我
	@ApiModelProperty(notes = "记住我") 
	private boolean remember;
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
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
}
