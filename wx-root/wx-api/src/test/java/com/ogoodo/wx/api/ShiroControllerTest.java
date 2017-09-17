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
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ogoodo.wx.shiro.config.ShiroConfigMy;
import com.ogoodo.wx.shiro.test.controller.ShiroController;

public class ShiroControllerTest {
    private MockMvc mvc;
//    @Autowired
//    private ShiroConfigMy cfg;// = new com.ogoodo.wx.shiro.config.ShiroConfigMy();
    private ShiroConfigMy cfg = new ShiroConfigMy();
//    @Resource
//    org.apache.shiro.mgt.SecurityManager securityManager;
//  @Autowired
//  private SecurityManager sm;
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

	// @Test
	public void test2() throws Exception {

		JSONObject json = new JSONObject() ;
		json.put("username", "admin");
		json.put("password", "123456");
		json.put("remember", "false");
		String jsonstr = json.toString() ;
		System.out.println("================================请求入参："+jsonstr);
		String url = "/test/shiro/login.do";
		mvc.perform(MockMvcRequestBuilders.post(url)
	       .contentType(MediaType.APPLICATION_JSON_UTF8)
	       .content(jsonstr)
	       .accept(MediaType.APPLICATION_JSON))
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andDo(MockMvcResultHandlers.print())
	       .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("登录成功")));
	}
	
//	@Test
//	public void test1() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/test/shiro/login.do")
//	       .contentType(MediaType.APPLICATION_JSON_UTF8)
//	       .param("username", "admin")
//	       .param("password", "123456")
//	       .accept(MediaType.APPLICATION_JSON))
//	       .andExpect(MockMvcResultMatchers.status().isOk())
//	       .andDo(MockMvcResultHandlers.print())
//	       .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("登录成功")));
//	}

//	@Test
//	public void testGet() throws Exception {
//		JSONObject json = new JSONObject() ;
//		json.put("userId", "");
//		String jsonstr = json.toString() ;
//		System.out.println("================================请求入参："+jsonstr);
//		String url = "/test/shiro/login.do?username=admin&password=123456&remember=false";
//		RequestBuilder request = MockMvcRequestBuilders.get(url)
//		.contentType(MediaType.APPLICATION_JSON_UTF8)
//	//	.header("SESSIONNO", "");
//		.content(jsonstr) ;
//
//        MvcResult mvcResult = mvc.perform(request).andReturn() ;
//        System.out.println("调用返回");
////   
//        int status = mvcResult.getResponse().getStatus();  
//        String content = mvcResult.getResponse().getContentAsString();
//        
//        System.out.println("返回结果："+status);
//        System.out.println(content);
//        
//        Assert.assertTrue("正确", status == 200);  
//        Assert.assertFalse("错误", status != 200);  
//	}

	@Test
	public void testPost() throws Exception {
		JSONObject json = new JSONObject() ;
		json.put("username", "admin");
		json.put("password", "123456");
		json.put("remember", "false");
		String jsonstr = json.toString() ;
		String url = "/test/shiro/login.do";
		RequestBuilder request = MockMvcRequestBuilders.post(url)
		.contentType(MediaType.APPLICATION_JSON_UTF8)
	//	.header("SESSIONNO", "");
		.content(jsonstr);

        MvcResult mvcResult = mvc.perform(request)
//        	    .andExpect(MockMvcResultMatchers.status().isOk())
        	    .andDo(MockMvcResultHandlers.print())
        		.andReturn();

        int status = mvcResult.getResponse().getStatus();  
        String content = mvcResult.getResponse().getContentAsString();


		System.out.println("================================");
		System.out.println("url: " + url);
		
		System.out.println("请求入参:");
        System.out.println(jsonstr);

        System.out.println("返回结果:");
        System.out.println(content);
        
        Assert.assertTrue("正确", status == 200);  
        Assert.assertFalse("错误", status != 200);  
	}

}
