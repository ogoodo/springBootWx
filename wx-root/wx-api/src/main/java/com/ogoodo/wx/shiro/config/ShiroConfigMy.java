package com.ogoodo.wx.shiro.config;


import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
        // 自定义过滤器
        filters.put("perms", urlPermissionsFilter());  
        filters.put("anyPerms", new MyAnyPermissionsAuthorizationFilter());  
        filters.put("anyRoles", new MyAnyRolesAuthorizationFilter());
        // 内置过滤器, 方便理解所以配置在下面, 其实是没必要的
        // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new FormAuthenticationFilter());
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
		map.put("/test/shiro/**", "authc"); // 其他地址都需要权限验证
		
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
        // 设置rememberMe管理器
        manager.setRememberMeManager(rememberMeManager());
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
        sessionManager.setSessionIdCookie(getSessionIdCookie());
		return sessionManager;  
    }  

////  @Bean(name = "hashedCredentialsMatcher")  
//  public HashedCredentialsMatcher hashedCredentialsMatcher() {  
//      HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();  
//      credentialsMatcher.setHashAlgorithmName("MD5");  
//      credentialsMatcher.setHashIterations(1024);  
//      credentialsMatcher.setStoredCredentialsHexEncoded(true);  
//      return credentialsMatcher;  
//  }
 
    // 用自定义类代替
//	@Bean(name="credentialsMatcher")
	public MyHashedCredentialsMatcher hashedCredentialsMatcher(){
		MyHashedCredentialsMatcher platFormCredentialsMatcher = new MyHashedCredentialsMatcher();
		platFormCredentialsMatcher.setHashAlgorithmName("MD5");
		platFormCredentialsMatcher.setHashIterations(1024);
		platFormCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return platFormCredentialsMatcher;
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
        // myRealm.setAuthenticationTokenClass(UserAuthenticationToken.class);
        return myRealm;  
    }  
  
    @Bean  
    public URLPermissionsFilter urlPermissionsFilter() {  
        return new URLPermissionsFilter();  
    }  
  
    @Bean  
    public EhCacheManager cacheManager() {  
        EhCacheManager cacheManager = new EhCacheManager();  
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");  
        return cacheManager;  
    }  
  
	@Bean(name = "sessionIdCookie")
	public SimpleCookie getSessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("test-sid");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		return cookie;
	}
	
    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
//        log.info("rememberMeCookie()");
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("test-shiro-wx-remember-me");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 记住我管理器 cookie管理对象;
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
//        System.out.println("rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
    
//    /**
//     * 不知道有啥用: cxb:2017-09-10
//     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
//            SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

    @Bean  
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }  
} 
