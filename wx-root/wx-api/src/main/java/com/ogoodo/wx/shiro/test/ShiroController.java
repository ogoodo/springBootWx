package com.ogoodo.wx.shiro.test;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ogoodo.wx.shiro.config.MyShiroService;
import com.ogoodo.wx.test.dao.pojo.URole;

//import com.ogoodo.springmvc.HelloWorldController;

@Controller
public class ShiroController {

	private static Logger logger = LoggerFactory.getLogger(ShiroController.class);

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
	public String shiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value123");
		myShiroService.testMethod();
		return "redirect:/test/shiro/list.jsp";
	}

   @RequestMapping(value = "/test/shiro/logout.do")
   public String logout() {
	   Subject subject = SecurityUtils.getSubject();  
	   subject.logout(); 
	   return "redirect:/test/shiro/login.jsp";
   }

    /**
     * http://localhost:8080/HelloSpringMVC/dologin?username=chen&password=123456
     * /test/shiro/login.do?username=chen&password=123456
     * 实际的登录代码
     * 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
     *
     * @param request
     * @param model
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/test/shiro/login.do")
    public Map<String,Object> shiroLogin(String username, String password, Model model) {
    		// HttpRequest request,
    		logger.debug("doLogin");
        String msg = "";
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
        // return msg;
//        String userName = "chen";
//        String password = "123456";

        Map<String,Object> map = new HashMap<String,Object>();
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                map.put("code", "10000");
                map.put("msg", "登录成功");
                return map;
//                return "redirect:./list.jsp";
            } else {
                map.put("code", "10004");
                map.put("msg", "登录失败");
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
            model.addAttribute("message", msg);
        }
        map.put("code", "10004");
        map.put("msg", "登录出错");
        return map;
    }
}


