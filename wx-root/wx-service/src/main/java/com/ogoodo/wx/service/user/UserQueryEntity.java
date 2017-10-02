package com.ogoodo.wx.service.user;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class UserQueryEntity {

	// 根据邮件过滤
	@NotBlank(message = "{user.name.notBlank}")
	private String email;
	
	// 显示第几页(从1开始)
	@Min(1)
	private int pageNum;
	
	// 每页显示数量
	private int pageSize;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
