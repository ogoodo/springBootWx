package com.ogoodo.wx.api.shiro.config;


import javax.servlet.ServletRequest;    
import javax.servlet.ServletResponse;    
    
import org.apache.shiro.subject.Subject;    
import org.apache.shiro.web.filter.authz.AuthorizationFilter; 

public class MyAnyRolesAuthorizationFilter extends AuthorizationFilter {    
    
	/**
	 * 修改默认行为,用户只要拥有其中一个权限就能通过
	 */
    @Override    
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {    
        Subject subject = getSubject(req, resp);    
        String[] rolesArray = (String[]) mappedValue;    
    
        // 没有角色限制，有权限访问 
        if (rolesArray == null || rolesArray.length == 0) {   
            return true;    
        }
        
        // 若当前用户是rolesArray中的任何一个，则有权限访问 
        for(String role : rolesArray) {  
            if(subject.hasRole(role)) {  
                return true;  
            }  
        }
        return false;    
    }    
} 

