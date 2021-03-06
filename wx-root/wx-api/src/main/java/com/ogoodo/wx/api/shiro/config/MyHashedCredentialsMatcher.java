package com.ogoodo.wx.api.shiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHashedCredentialsMatcher  extends HashedCredentialsMatcher {

 	private final static Logger logger = LoggerFactory.getLogger(MyHashedCredentialsMatcher.class);

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        logger.info("----->对比密码: 用户输入:" + tokenHashedCredentials + ",存:" + accountCredentials);
		boolean matches = super.doCredentialsMatch(token, info);
		return matches;
	}

//	@Autowired
//	private UserService service;
//
//	@Autowired
//	private EhCacheManager shiroEhcacheManager;
//
//	@Override
//	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//		Cache<String, AtomicInteger> passwordRetryCache = shiroEhcacheManager.getCache("passwordRetryCache");
//		String userno = (String) token.getPrincipal();
//		// retry count + 1
//		AtomicInteger retryCount = passwordRetryCache.get(userno);
//		if (retryCount == null) {
//			retryCount = new AtomicInteger(0);
//			passwordRetryCache.put(userno, retryCount);
//		}
//		if (retryCount.incrementAndGet() > 5) {
//			// if retry count > 5 throw
//			throw new ExcessiveAttemptsException();
//		}
//
//		boolean matches = super.doCredentialsMatch(token, info);
//		if (matches) {
//			// clear retry count
//			passwordRetryCache.remove(userno);
//
//			Result<User> userResult = service.findByUserNo(userno);
//			// 根据登录名查询用户
//			Subject subject = SecurityUtils.getSubject();
//			Session session = subject.getSession();
//			session.setAttribute("user", userResult.getResultData());
//		}
//		return matches;
//	}

}
