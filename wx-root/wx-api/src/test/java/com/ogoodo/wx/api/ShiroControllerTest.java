package com.ogoodo.wx.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ogoodo.wx.shiro.test.ShiroController;

public class ShiroControllerTest {
    private MockMvc mvc;
    private com.ogoodo.wx.shiro.config.ShiroConfigMy cfg = new com.ogoodo.wx.shiro.config.ShiroConfigMy();
    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;
  @Autowired
  private SecurityManager sm;
    //初始化执行
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ShiroController()).build();
//		SecurityManager securityManger = mock(SecurityManager.class, RETURNS_DEEP_STUBS);
	    ThreadContext.bind(cfg.securityManager());
//        SecurityManager securityManager = (SecurityManager)appCtx.getBean("securityManager");
//        SecurityUtils.setSecurityManager(securityManager);
    }
 
    //验证controller是否正常响应并打印返回结果
//    @Test
//    public void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
     
    //验证controller是否正常响应并判断返回结果是否正确
//    @Test
//    public void testHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/test/shiro/login.do?username=admin&password=123456&remember=false")
//        		.accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("Hello World")));
//    }
    

@Test
public void test1() throws Exception {
	mvc.perform(MockMvcRequestBuilders.get("/test/shiro/login.do")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("username", "admin")
            .param("password", "123456")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("登录成功")));
    
}
}
