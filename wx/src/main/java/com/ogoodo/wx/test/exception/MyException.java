package com.ogoodo.wx.test.exception;

public class MyException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = 10001;

	public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
    
}
