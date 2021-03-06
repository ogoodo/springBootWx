package com.ogoodo.wx.api.shiro.config;


import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ogoodo.wx.db.auto.dao.UUser;
import com.ogoodo.wx.db.auto.dao.UUserExample;
import com.ogoodo.wx.db.auto.mapper.UUserMapper;
import com.ogoodo.wx.service.user.UserService;


public class MyRealm extends AuthorizingRealm {

 	private final static Logger logger = LoggerFactory.getLogger(MyRealm.class);
 	
 	@Autowired
 	UserService userService;
// 	UUserMapper usermapper;
    /**
     * 为当前登录的Subject授予角色和权限
     * 经测试:本例中该方法的调用时机为需授权资源被访问时
     * 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
	/**
	 * 本函数其实就是shiro查询当前用户包含了哪些权限
	 * 本例中该方法的调用时机为需授权资源被访问时
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){

        logger.info("权限配置-->doGetAuthorizationInfo");
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String)super.getAvailablePrincipal(principals);
//      这里实现自己的业务逻辑
//      根据业务逻辑，设置角色和权限

        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得
        if(null!= currentUsername) {
	        if("admin".equals(currentUsername) || "su".equals(currentUsername)){
	            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
	            simpleAuthorInfo.addRole("admin");
	            simpleAuthorInfo.addStringPermission("admin:manage");
	            simpleAuthorInfo.addStringPermission("admin:delete");
	            simpleAuthorInfo.addStringPermission("admin:add");
	        }
	        //超级管理员
	        if("su".equals(currentUsername)){
	            simpleAuthorInfo.addRole("su");
	            simpleAuthorInfo.addStringPermission("admin:super");
	        }
	        if("user".equals(currentUsername)){
	            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
	            simpleAuthorInfo.addRole("user");         
	            simpleAuthorInfo.addStringPermission("user:manage"); //添加权限return simpleAuthorInfo;
	        }
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        logger.info("----->用户"+ currentUsername + "具有的角色:" + simpleAuthorInfo.getRoles());
        logger.info("----->用户"+ currentUsername + "具有的权限：" + simpleAuthorInfo.getStringPermissions());
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return simpleAuthorInfo;
    }

    	/**
    	 * 找到验证密码部分的代码了，原来这样写的doGetAuthenticationInfo只充当了Dao的功能，将用户信息拿出来，
    	 * 真正校验就交给assertCredentialsMatch方法(XX.AuthenticatingRealm.assertCredentialsMatch) 
    	 * 所以，在自己的realm中重写该方法就可以自定义密码之间的equals 额
    	 */
    /**
     *   验证当前登录的Subject
     *   经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {//获取基于用户名和密码的令牌

        logger.info("正在验证身份...");
	    UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
	    String username= token.getUsername();
	    String saltPassword = userService.GetPasswordByUsername(username);
	    if(saltPassword.isEmpty()) {
	        logger.info("----->用户:" + username + "没有存在");
	    		return null;
	    } else {
	        logger.info("----->用户:" + username + "存在,ps:" + saltPassword);
	    }
//	    	String password="123";
	    	// password = "fc1709d0a95a6be30bc5926fdb7f22f4";
	    	// MD5盐值加密生成复杂密码
			ByteSource credSalt = ByteSource.Util.bytes(username);
			// 这个值应该是注册的时候存储到数据库里面
	    // Object saltPassword = new SimpleHash("MD5", password, credSalt, 1024);
//	    	String saltPassword1 = new SimpleHash("MD5", password, credSalt, 1024).toString();
//	        logger.info("----->用户:" + username + "存在,p2:" + saltPassword1);
	    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, saltPassword, credSalt, getName());
	    	return info;
	    	
//    		// MD5盐值加密生成复杂密码
//		ByteSource credSalt = ByteSource.Util.bytes(username);
//		DefaultPasswordService dps = new DefaultPasswordService();
////		Object ps = dps.encryptPassword(saltPassword);
////		SimpleHash ps = new SimpleHash("MD5", saltPassword);
//		SimpleHash ps = new SimpleHash("MD5");
//		ps.setBytes(saltPassword.getBytes());
////	    // Object saltPassword = new SimpleHash("MD5", password, credSalt, 1024);
//	    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, ps, credSalt, getName());
//	    	// SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userId, password, getName());
//	
//	    	return info;

	    
	    
	    
////	    UUserExample example = new UUserExample();
////	    UUserExample.Criteria criteria = example.createCriteria();
////		criteria.andEmailEqualTo(username);
//////		UUser user = usermapper.selectByPrimaryKey((long)1);
////		// 如果这个地方提示类找不到， 很有可能是wx-db里xml没有打包到jar里去
////		List<UUser> user = usermapper.selectByExample(example);
//	    if(!username.equals("admin") && !username.equals("user") && !username.equals("test")) {
//	        logger.info("----->用户:" + username + "没有登录权限");
//	    		return null;
//	    } else {
//	        logger.info("----->用户:" + username + "存在");
//	    }
//	    String userId= (String) authcToken.getPrincipal();
//	    logger.info("----->userId:" + userId);
//	    	//模拟从数据库取得的信息
//	    	String password="123456";
//	    	// password = "fc1709d0a95a6be30bc5926fdb7f22f4";
//	    	// MD5盐值加密生成复杂密码
//			ByteSource credSalt = ByteSource.Util.bytes(username);
//			// 这个值应该是注册的时候存储到数据库里面
//	    // Object saltPassword = new SimpleHash("MD5", password, credSalt, 1024);
//	    	String saltPassword = new SimpleHash("MD5", password, credSalt, 1024).toString();
//	    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, saltPassword, credSalt, getName());
//	    	// SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userId, password, getName());
//
//	    	return info;
//
//
////        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
////        //两个token的引用都是一样的
////        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
////        // log.info("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
////        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
////        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)
////        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
////      /*  if("mike".equals(token.getUsername())){
////            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("mike", "mike", this.getName());
////            authcInfo.getCredentials();
////            this.setSession("currentUser", "mike");
////            return authcInfo;
////        }*///没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
////        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *   比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser) {
			Session session = currentUser.getSession();
			if(null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}

