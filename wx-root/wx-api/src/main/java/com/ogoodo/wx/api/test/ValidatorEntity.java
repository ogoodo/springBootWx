package com.ogoodo.wx.api.test;

import java.sql.Timestamp;

import com.ogoodo.wx.utils.valid.formatter.MyPhoneNumberFormat;
import com.ogoodo.wx.utils.valid.formatter.TimestampFormat;
import com.ogoodo.wx.utils.valid.formatter.MyPhoneNumberModel;
import com.ogoodo.wx.utils.valid.validator.FlagValidator;
import com.ogoodo.wx.utils.valid.validator.MyMin;
import com.ogoodo.wx.utils.valid.validator.MyMobile;
import com.ogoodo.wx.utils.valid.validator.MyTelModel;

public class ValidatorEntity {

	@FlagValidator(values="1,2,3,a,b,c")
	private String pageNum;

//    @MyPhoneNumberFormat(min = 8, max = 9, message="{user.phone.error}")
//    private MyPhoneNumberModel myPhoneNumberFormat;
    
//    @MyTel(message="{my.user.tel}", min=3)
    private MyTelModel tel;
  
    @MyMobile(message="{my.user.mymobile}", min=3, pattern="XXX?-XXXXXXX?")
    private String mobile;
 
	@MyPhoneNumberFormat(min = 8, max = 9, message="{user.phone.error}")
    private MyPhoneNumberModel phone;

    @MyMin(message="{my.user.min}", min=3)
    private String mymin;
    

	@TimestampFormat
    private Timestamp startTime;

    

    public MyPhoneNumberModel getPhone() {
		return phone;
	}

	public void setPhone(MyPhoneNumberModel phone) {
		this.phone = phone;
	}

    public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

 
	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public MyTelModel getTel() {
		return tel;
	}

	public void setTel(MyTelModel tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMymin() {
		return mymin;
	}

	public void setMymin(String mymin) {
		this.mymin = mymin;
	}

}
