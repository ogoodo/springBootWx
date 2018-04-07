package com.ogoodo.wx.utils.valid.validator;

public class PhoneDemoModel {

	public PhoneDemoModel() {
	}
	
	public PhoneDemoModel(String str) {
		this.areaCode = "123";
		this.phoneNumber = "abc";
	}
	private String areaCode; 
    private String phoneNumber;

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	} 
}
