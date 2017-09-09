package com.ogoodo.wx.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.boot.autoconfigure.cache.CacheProperties.EhCache;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;

/**
 * 参考: http://weiqingfei.iteye.com/blog/2307860
 */
@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager( securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/wx/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/wx/403");

		//拦截器.
//		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//		// 配置不会被拦截的链接 顺序判断
//		filterChainDefinitionMap.put("/static/**", "anon");
//		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//		filterChainDefinitionMap.put("/logout", "logout");
//		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//		filterChainDefinitionMap.put("/**", "authc");
		Map<String,String> filterChainDefinitionMap = this.builderFilterChainDefinitionMap();
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}
	// 这里可以从数据库里读出来
	public LinkedHashMap<String, String> builderFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();


//		map.put("/test/shiro/anyPerms.jsp", "anyPerms[\"admin:delete\",\"admin:add\"]");
		
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
        return ehCacheManager;  
    }

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
//		securityManager.setCacheManager(ehCacheManager()); 
		return securityManager;
	}
}

