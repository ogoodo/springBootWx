package com.ogoodo.wx.shiro.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class MyShiroService {

	@RequiresRoles({"admin"})
	public void testMethod() {
		System.out.println("testMethod");
		Session session = SecurityUtils.getSubject().getSession();
		Object value = session.getAttribute("key");
		System.out.println("MyShiroService.testMethod: " + value);
	}

}

