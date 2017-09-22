package com.ogoodo.wx.shiro.test.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.shiro.config.MyRealm;
import com.ogoodo.wx.shiro.config.MyShiroService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;  
import io.swagger.annotations.ApiResponses; 

//import com.ogoodo.springmvc.HelloWorldController;

// 
/**
 * 参考: http://weiqingfei.iteye.com/blog/2307860
 * http://www.jianshu.com/p/05c8be17c80a  不知道是不是增加多组shiro验证, 不同路由走不通shiro bean
 * http://lihao312.iteye.com/blog/2309788 这个可以用
 * http://www.cnblogs.com/hyyq/p/6886004.html  这个一个用户-角色-权限图不错, 登录过程可以参考
 * http://blog.csdn.net/u014695188/article/details/52357379 讲验证码校验
 */
//@Controller
@RestController
public class ShiroController {

 	private final static Logger logger = LoggerFactory.getLogger(ShiroController.class);

	@Autowired
	private MyShiroService myShiroService;


	@ResponseBody
	@RequestMapping(value="/test/shiro/index")
	public Map<String,Object> insert(String name, String type, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}

	@ResponseBody
	@RequestMapping(value = "/test/shiro/annotation.do")
	public Map<String,Object> shiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value123");
		Object value = myShiroService.testMethod();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", "10000");
        map.put("msg", "测试通过session传值");
        map.put("sessionValue", value);
        return map;
//		return "redirect:/test/shiro/list.jsp";
	}


	/**
	 * /test/shiro/sessionTransferValue.do?value=我传入的值123
	 * 测试用session传值给service
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/test/shiro/sessionTransferValue.do")
	public Map<String,Object> sessionTransferValue(String value, HttpSession session) {
		session.setAttribute("myKey", value);
		Object sessionTransferValue = myShiroService.testSessionTransferValue();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", "10000");
        map.put("msg", "测试通过session传值");
        map.put("sessionTransferValue", sessionTransferValue);
        return map;
//		return "redirect:/test/shiro/list.jsp";
	}
	

   @GetMapping(value = "/test/shiro/logout.do")
   public Map<String,Object> logout() {
	   Subject subject = SecurityUtils.getSubject();  
	   subject.logout(); 
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("code", "10000");
       map.put("msg", "退出成功");
       return map;
   }

   
   @ApiOperation(value="login api", notes="notes", consumes="application/json;charset=utf-8")
   @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
   @ApiResponses(value = {  
	        @ApiResponse(code = 200, message = "成功"),  
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),  
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),  
	        @ApiResponse(code = 404, message = "接口不存在")  
		}  
	)
  @PostMapping(value = "/test/shiro/login.do", produces = {"application/json;charset=utf-8"})
  public Map<String,Object> shiroLogin(
  		@RequestBody User user, Model model) {
	   return this.shiroLogin2(user.getUsername(), user.getPassword(), user.isRemember(), model);
   }
   
   
    /**
     * http://localhost:8080/HelloSpringMVC/dologin?username=chen&password=123456
     * /test/shiro/login.do?username=admin&password=123456&remember=false
     * /test/shiro/login.do?username=user&password=123456&remember=false
     * 实际的登录代码
     * 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
     *
     * @param request
     * @param model
     * @return
     */
   @ApiOperation(value="login api", notes="notes", consumes="application/json;charset=utf-8")
//   @ApiOperation(value="login api", notes="notes", consumes="application/x-www-form-urlencoded")
   @ApiImplicitParams({
       @ApiImplicitParam(name = "username", value = "admin", required = true, dataType = "String"),
       @ApiImplicitParam(name = "password", value = "123456", required = true, dataType = "String")
   })
   /**
{
username:"admin"
password:"123456"
}
    */
//	@ResponseBody
    @RequestMapping(value = "/test/shiro/login2.do", method= RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public Map<String,Object> shiroLogin2(
//    		@RequestBody String username,
//    		@RequestBody String password,
    		String username,
    		String password,
    		boolean remember, Model model) {

    		logger.info("用户登录:" + username + " " + password);
        String msg = "";

        Map<String,Object> map = new HashMap<String,Object>();
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if(remember) {
    			logger.info("------>" + username + "  rememberme");
            token.setRememberMe(true);
        } else {
			logger.info("------>" + username + "  not rememberme");
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                map.put("code", "10000");
                map.put("msg", "登录成功");
                return map;
//                return "redirect:./list.jsp";
            } else {
                map.put("code", "10005");
                map.put("msg", "登录失败");
                map.put("data", subject.toString());
                return map;
//                return "login";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (AuthenticationException e) {
            msg = "密码不对！" + e.getMessage();
            e.printStackTrace();
            model.addAttribute("message", msg);
        }
        map.put("code", "10004");
        map.put("msg", msg);
        map.put("data", "登录错误");
        return map;
    }

	// 用户未有权限
    	@ResponseBody
    	@RequestMapping(value="/test/shiro/unauthor.do")
    	public Map<String,Object> unauthor( Locale locale){
    	    Map<String,Object> map=new HashMap<String,Object>();
    	    map.put("code", "10001");
    	    map.put("msg", "未有此权限");
    	    return map;
    	}

	@ResponseBody
	@RequestMapping(value="/test/shiro/custom/perms.do")
	public Map<String,Object> customPerms( Locale locale){
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("code", "10000");
	    map.put("msg", "自定义校验perms");
	    return map;
	}


	@ResponseBody
	@RequestMapping(value="/test/shiro/custom/anyPerms.do")
	public Map<String,Object> customAnyPerms( Locale locale){
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("code", "10000");
	    map.put("msg", "自定义校验anyPerms");
	    return map;
	}

	@ResponseBody
	@RequestMapping(value="/test/shiro/custom/anyRoles.do")
	public Map<String,Object> customAnyRoles( Locale locale){
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("code", "10000");
	    map.put("msg", "自定义校验anyRoles");
	    return map;
	}
}
