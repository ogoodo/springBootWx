package com.ogoodo.wx.utils.valid;

/**
 * @description:自定义异常类
 */
public class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
	    super(msg);
	  }
}
