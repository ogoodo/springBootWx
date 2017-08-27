package com.ogoodo.wx.test.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoadProperty {

	@Value("${com.ogoodo.wx.test.properties.username}")
    private String name;
    
    @Value("${com.ogoodo.wx.test.properties.password}")
    private String password;
    
    @Value("${com.ogoodo.wx.test.properties.param}")
    private String param;
	
    public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
