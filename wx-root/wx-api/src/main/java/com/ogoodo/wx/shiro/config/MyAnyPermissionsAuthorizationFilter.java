package com.ogoodo.wx.shiro.config;


import java.util.Arrays;

import javax.servlet.ServletRequest;    
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject; 
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAnyPermissionsAuthorizationFilter extends AuthorizationFilter {

 	private final static Logger logger = LoggerFactory.getLogger(MyAnyPermissionsAuthorizationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("anyPerms校验权限:isAccessAllowed");
        Subject subject = getSubject(request, response);
        Object username = subject.getPrincipal();
        String[] perms = (String[]) mappedValue;
        logger.info("----->url: " + this.getRequestUrl(request));
        logger.info("----->url需要权限: " + Arrays.toString(perms));
        logger.info("----->username: " + username);
        for (String perm : perms) {
            if (subject.isPermitted(perm)) {
                return true;
            }
        }
        return false;
    }

	/**
	 * 获取当前URL+Parameter
	 * @param request	拦截请求request
	 * @return			返回完整URL
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();

		queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
		return req.getRequestURI()+queryString;
	}
}
