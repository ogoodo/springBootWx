package com.ogoodo.wx.service.entity;

public class UserQueryEntity {

	// 根据邮件过滤
	private String email;
	
	// 显示第几页(从1开始)
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
