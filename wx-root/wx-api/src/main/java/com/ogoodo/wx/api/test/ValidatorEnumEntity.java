package com.ogoodo.wx.api.test;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.alibaba.fastjson.annotation.JSONField;
import com.ogoodo.wx.utils.valid.converter.FuncEnum;
import com.ogoodo.wx.utils.valid.validator.EnumDemo;
import com.ogoodo.wx.utils.valid.validator.EnumValidator;
import com.ogoodo.wx.utils.valid.validator.Gender;
import com.ogoodo.wx.utils.valid.validator.GenderValidator;
import com.ogoodo.wx.utils.valid.validator.PhoneDemoModel;
//import com.ogoodo.wx.utils.valid.converter.Gender;
import com.ogoodo.wx.utils.valid.validator.ValidateEnum;
import com.ogoodo.wx.utils.valid.validator.ValidateString;

import io.swagger.annotations.ApiModelProperty;

public class ValidatorEnumEntity {

	public enum CountryCode {
	  	   US,
	  	   GB,
	  	   CA;
	  	}
	public enum SexEnum {
	
	    MAIL("男性"), FMAIL("女性");
	
	    private String value;
	
	    private SexEnum(String value) {
	        this.value = value;
	    }
	
	    @Override
	    public String toString() {
	        return this.value;
	    }
	}
//  	@ValidateString(CountryCode.STRING) 
//  	String code;
 
  	@ValidateString(acceptedValues={"Integer", "String"}, message="Invalid dataType")
	@JSONField(serialize = false)
	@ApiModelProperty(example="String", value="Integer,String")
  	String dataType;
  	
  	@ValidateEnum( enumClazz=CountryCode.class, ignoreCase=true, message="请填写正确的枚举类型")
	@ApiModelProperty(example="US", value="US,GB,CA")
    private String testEnum;

    @GenderValidator(message="参数校验不通过:枚举写错了吧")
    private Gender gender;

    @EnumValidator(message="是无效的枚举值！！！")
    EnumDemo enumDemo;
    
    public PhoneDemoModel getPhoneModel() {
		return phoneModel;
	}
    
    public String getPhoneModelStr() {
		return phoneModel.getAreaCode() + "--" + phoneModel.getPhoneNumber();
	}

	public void setPhoneModel(PhoneDemoModel phoneModel) {
		this.phoneModel = phoneModel;
	}

    // @ModelValidator(message="是无效的枚举值！！！")
	@JSONField(serialize=false)
	private PhoneDemoModel phoneModel;
  	
//  	@ValidateEnum( enumClazz=CountryCode.class, message="This error is coming from the enum class")
//    @EnumValidator
//    private CountryCode enumobj;
   
//    private FuncEnum funcEnum;
    
//    @Enumerated(EnumType.STRING)
//    private SexEnum sexEnumString;
//
//	@Enumerated(EnumType.ORDINAL)
//    private SexEnum sexEnumOrdinal;

    public EnumDemo getEnumDemo() {
		return enumDemo;
	}

	public void setEnumDemo(EnumDemo enumDemo) {
		this.enumDemo = enumDemo;
	}

	@JSONField(serialize=false)
	public String getDataType() {
  		return dataType;
  	}

  	public void setDataType(String dataType) {
  		this.dataType = dataType;
  	}

  	
//    public FuncEnum getFuncEnum() {
//        return funcEnum;
//    }
//
//    public void setFuncEnum(FuncEnum funcEnum) {
//        this.funcEnum = funcEnum;
//    }

	public Gender getGender() {
		return gender;
	}

	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getTestEnum() {
		return testEnum;
	}

	public void setTestEnum(String testEnum) {
		this.testEnum = testEnum;
	}

//	public CountryCode getEnumobj() {
//		return enumobj;
//	}
//
//	public void setEnumobj(CountryCode enumobj) {
//		this.enumobj = enumobj;
//	}

    
//    public SexEnum getSexEnumString() {
//		return sexEnumString;
//	}
//
//	public void setSexEnumString(SexEnum sexEnumString) {
//		this.sexEnumString = sexEnumString;
//	}
//
//	public SexEnum getSexEnumOrdinal() {
//		return sexEnumOrdinal;
//	}
//
//	public void setSexEnumOrdinal(SexEnum sexEnumOrdinal) {
//		this.sexEnumOrdinal = sexEnumOrdinal;
//	}	

}
