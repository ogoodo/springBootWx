package com.ogoodo.wx.utils.valid.formatter;

import javax.persistence.Entity;

@Entity
public class MyPhoneNumberModel {  
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
	public MyPhoneNumberModel() {
		System.out.println("MyPhoneNumberModel()");
	}
	// public MyPhoneNumberModel(String str) {
	// 	System.out.println("MyPhoneNumberModel(String str)");
	// }
}
