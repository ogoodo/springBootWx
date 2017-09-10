package com.ogoodo.wx.shiro.config;


import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.boot.autoconfigure.cache.CacheProperties.EhCache;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;


@Configuration
public class ShiroConfigMy {
	 /** 
     * FilterRegistrationBean 
     * @return 
     */  
    @Bean  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();  
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));   
        filterRegistration.setEnabled(true);  
        filterRegistration.addUrlPatterns("/*");   
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);  
        return filterRegistration;  
    }  
  
    /**  
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean  
     * @return  
     */  
    @Bean(name = "shiroFilter")  
    public ShiroFilterFactoryBean shiroFilter(){  
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();  
        bean.setSecurityManager(securityManager());  
        bean.setLoginUrl("/test/shiro/login-302.do");  
        bean.setUnauthorizedUrl("/test/shiro/unauthor.do");  
  
        Map<String, Filter>filters = new LinkedHashMap<>(); 
        filters.put("perms", urlPermissionsFilter());  
        filters.put("anyPerms", new MyAnyPermissionsAuthorizationFilter());  
        filters.put("anyRoles", new MyAnyRolesAuthorizationFilter());  
        filters.put("anon", new AnonymousFilter());  
        bean.setFilters(filters);  
  
        bean.setFilterChainDefinitionMap(builderFilterChainDefinitionMap());  
        return bean;  
    }  
  
	// 这里可以从数据库里读出来
	public LinkedHashMap<String, String> builderFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();

		// 验证自定义权限校验
		map.put("/test/shiro/custom/perms.do", "perms");
		map.put("/test/shiro/custom/anyPerms.do", "anyPerms[\"admin:delete\",\"admin:add\"]");
		map.put("/test/shiro/custom/anyRoles.do", "anyRoles[\"user,admin\"]");
		
		map.put("/test/shiro/list.jsp", "anon");
		map.put("/test/shiro/login.do", "anon");
		map.put("/test/shiro/login.jsp", "anon");
		// map.put("/test/shiro/logout.do", "logout");
//		map.put("/test/shiro/user.jsp", "anyRoles[\"user,admin\"]");
//		map.put("/test/shiro/user.jsp", "roles[admin]");
//		map.put("/test/shiro/user.jsp", "roles[user]");
		map.put("/test/shiro/admin.jsp", "roles[admin]");
		map.put("/test/shiro/**", "anon");
		
		return map;
	}
    /**  
     * @see org.apache.shiro.mgt.SecurityManager  
     * @return  
     */  
    @Bean(name="securityManager")  
    public DefaultWebSecurityManager securityManager() {  
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();  
        manager.setRealm(myRealm());  
        manager.setCacheManager(cacheManager());  
        manager.setSessionManager(defaultWebSessionManager());  
        return manager;  
    }  
  
    /** 
     * @see DefaultWebSessionManager 
     * @return 
     */  
    @Bean(name="sessionManager")  
    public DefaultWebSessionManager defaultWebSessionManager() {  
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();  
        sessionManager.setCacheManager(cacheManager());  
        sessionManager.setGlobalSessionTimeout(1800000);  
        sessionManager.setDeleteInvalidSessions(true);  
        sessionManager.setSessionValidationSchedulerEnabled(true);  
        sessionManager.setDeleteInvalidSessions(true);  
        return sessionManager;  
    }  

//  @Bean(name = "hashedCredentialsMatcher")  
  public HashedCredentialsMatcher hashedCredentialsMatcher() {  
      HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();  
      credentialsMatcher.setHashAlgorithmName("MD5");  
      credentialsMatcher.setHashIterations(1024);  
      credentialsMatcher.setStoredCredentialsHexEncoded(true);  
      return credentialsMatcher;  
  }
  
    /** 
     * @see MyRealm--->AuthorizingRealm 
     * @return 
     */  
    @Bean  
    @DependsOn(value="lifecycleBeanPostProcessor")  
    public MyRealm myRealm() {  
        MyRealm myRealm = new MyRealm();  
        myRealm.setCacheManager(cacheManager());  
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;  
    }  
  
    @Bean  
    public URLPermissionsFilter urlPermissionsFilter() {  
        return new URLPermissionsFilter();  
    }  
  
    @Bean  
    public EhCacheManager cacheManager() {  
        EhCacheManager cacheManager = new EhCacheManager();  
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");  
        return cacheManager;  
    }  
  
    @Bean  
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }  
} 
