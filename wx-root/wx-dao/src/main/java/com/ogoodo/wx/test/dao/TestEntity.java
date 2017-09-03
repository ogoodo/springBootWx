package com.ogoodo.wx.test.dao;

import org.springframework.stereotype.Component;;

@Component("testEntity")
public class TestEntity {

	private String ussername;
	public String getUssername() {
		return ussername;
	}
	public void setUssername(String ussername) {
		this.ussername = ussername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
}
