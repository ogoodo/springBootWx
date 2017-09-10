package com.ogoodo.wx.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.boot.autoconfigure.cache.CacheProperties.EhCache;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.servlet.Filter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;

//@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager( securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		bean.setLoginUrl("/wx/login");
		// 登录成功后要跳转的链接
		bean.setSuccessUrl("/index");
		//未授权界面;
		bean.setUnauthorizedUrl("/wx/403");

		//拦截器.
//		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//		// 配置不会被拦截的链接 顺序判断
//		filterChainDefinitionMap.put("/static/**", "anon");
//		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//		filterChainDefinitionMap.put("/logout", "logout");
//		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//		filterChainDefinitionMap.put("/**", "authc");
		Map<String, Filter>filters = new LinkedHashMap<>();
		filters.put("perms", urlPermissionsFilter());
		filters.put("anon", new AnonymousFilter());
		bean.setFilters(filters);

		org.apache.shiro.web.filter.authc.AuthenticationFilter aaa;

		Map<String,String> filterChainDefinitionMap = this.builderFilterChainDefinitionMap();
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		bean.setFilters(filters);
		return bean;
	}
	@Bean
	public URLPermissionsFilter urlPermissionsFilter() {
		return new URLPermissionsFilter();
	}
//	  这种配置不方便
//    @Bean  
//    public FilterRegistrationBean filterDemo3Registration() {  
//        FilterRegistrationBean registration = new FilterRegistrationBean();  
//        registration.setFilter(new FilterMyAnyPermission());  
//        registration.addUrlPatterns("/*");  
//        registration.addInitParameter("paramName", "paramValue");  
//        registration.setName("filterDemo3");  
//        registration.setOrder(6);  
//        return registration;  
//    } 
 
	// 这里可以从数据库里读出来
	public LinkedHashMap<String, String> builderFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();


//		map.put("/test/shiro/anyPerms.jsp", "anyPerms[\"admin:delete\",\"admin:add\"]");
		map.put("/test/shiro/self/perms", "perms");
		
		map.put("/test/shiro/list.jsp", "anon");
		map.put("/test/shiro/login.do", "anon");
		map.put("/test/shiro/login.jsp", "anon");
		// map.put("/test/shiro/logout.do", "logout");
//		map.put("/test/shiro/user.jsp", "anyRoles[\"user,admin\"]");
//		map.put("/test/shiro/user.jsp", "roles[admin]");
//		map.put("/test/shiro/user.jsp", "roles[user]");
		map.put("/test/shiro/admin.jsp", "roles[admin]");
		map.put("/test/shiro/**", "anon");
		
		
		
//		map.put("/HelloSpringMVC/test/shiro/list.jsp", "anon");
//		map.put("/HelloSpringMVC/test/shiro/login", "anon");
//		map.put("/HelloSpringMVC/test/shiro/login.jsp", "anon");
//		map.put("/HelloSpringMVC/test/shiro/logout", "logout");
//		map.put("/HelloSpringMVC/test/shiro/user.jsp", "roles[user]");
//		map.put("/HelloSpringMVC/test/shiro/admin.jsp", "roles[admin]");
//		map.put("/HelloSpringMVC/test/shiro/**", "authc");
		
//		map.put("/HelloSpringMVC/**", "anon");
//		map.put("/HelloSpringMVC/**", "authc");
		return map;
	}

//    @Bean(name = "hashedCredentialsMatcher")  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();  
        credentialsMatcher.setHashAlgorithmName("MD5");  
        credentialsMatcher.setHashIterations(1024);  
        credentialsMatcher.setStoredCredentialsHexEncoded(true);  
        return credentialsMatcher;  
    } 
	

//    @Bean(name = "shiroRealm")  
//    @DependsOn("lifecycleBeanPostProcessor")  
	@Bean
	public MyRealm myShiroRealm(){
		MyRealm myShiroRealm = new MyRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

    @Bean(name = "ehCacheManager")  
//    @DependsOn("lifecycleBeanPostProcessor")  
    public EhCacheManager ehCacheManager(){  
        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;  
    }

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(ehCacheManager()); 
		return securityManager;
	}
	

}

