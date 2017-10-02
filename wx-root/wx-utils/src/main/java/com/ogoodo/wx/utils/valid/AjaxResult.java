package com.ogoodo.wx.utils.valid;

public class AjaxResult {
	private static final int SUCCESS_CODE = 10000;
	private static final int ERROR_CODE = 10004;
	private static final String SUCCESS_MSG = "接口成功";

	//	  private Meta meta;
	private int code;
	private String msg;
	private Object data;
	// 该出错的详细技术信息，提供给客户端的开发者阅读。可以包含Exception的信息、StackTrace，或者其它有用的技术信息
	// private String developerMessage;
	/**
	 *  请求ID，服务为每一个请求唯一生成一个请求ID，当客户端开发者无法自助解决问题时，可以联络服务开发者，同时提供该请求ID。
	 * 一个好的服务，服务开发者应当可以根据此ID，定位到该次请求的所有相关log，进而定位问题，解决问题。
	 */
	// private String requestId;

	public AjaxResult success() {
		this.code = SUCCESS_CODE;
		this.msg = SUCCESS_MSG;
		return this;
	}

	public AjaxResult success(Object data) {
		this.code = SUCCESS_CODE;
		this.msg = SUCCESS_MSG;
		this.data = data;
		return this;
	}

	public AjaxResult failure(String msg) {
		this.code = ERROR_CODE;
		this.msg = msg;
		return this;
	}

	public AjaxResult failure(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}

	public AjaxResult failure(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		return this;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

}

