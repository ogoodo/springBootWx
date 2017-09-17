package com.ogoodo.wx.shiro.test.controller;

public class User {
	// 用户名
	private String username;
	// 密码
	private String password;
	// 记住我
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
