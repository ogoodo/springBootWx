package com.ogoodo.wx.api.shiro.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class MyShiroService {

	@RequiresRoles({"admin"})
	public Object testMethod() {
		System.out.println("testMethod");
		return "验证@RequiresRoles注解, admin_permission_success";
	}

	public Object testSessionTransferValue() {
		System.out.println("testSessionTransferValue");
		Session session = SecurityUtils.getSubject().getSession();
		Object value = session.getAttribute("myKey");
		System.out.println("MyShiroService.testMethod: " + value);
		return value;
	}
	
}

