package com.ogoodo.wx.api;


import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.shiro.util.ThreadContext;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ogoodo.wx.test.api.controller.MybatisController;
import com.ogoodo.wx.test.api.main.App;


//import com.alibaba.fastjson.JSONObject;
//import com.controller.user.UserController;


//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class MockMvcTest {


    
//  @Autowired
//  MybatisController userController ;
//@Autowired
     private MockMvc mockMvc;
     
     @Autowired  
     private WebApplicationContext wac;  
//     @Autowired
//     private SecurityManager sm;
     
     @Before
     public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		SecurityManager securityManger = mock(SecurityManager.class, RETURNS_DEEP_STUBS);
//		SecurityManager securityManger = mock(ShiroConfigMy.class, RETURNS_DEEP_STUBS);
	    ThreadContext.bind(securityManger);
     }

//     @Test
//     public void demo() throws Exception {
//         mockMvc.perform(get("/test/shiro/login.do").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().json("{'foo':'bar'}"));
//     }

//	@Test
//	public void webappPublisherApi() throws Exception {
//	
//		  mockMvc.perform(get("/test/shiro/login.do?username=admin&password=123456&remember=true")
//		  .accept(MediaType.APPLICATION_JSON_UTF8))
//		
//		  .andExpect(status().isOk());
////		  .andExpect(content().string(containsString("中文测试")))
////		  .andExpect(jsonPath("$.name").value("中文测试"));
//	}

@Test
public void testInfo() throws Exception {
	JSONObject param = new JSONObject() ;
	param.put("userId", "");
	String jsonstr = param.toString() ;
	System.out.println("================================请求入参："+jsonstr);
	RequestBuilder request = MockMvcRequestBuilders.get("/test/shiro/login.do?username=admin&password=123456&remember=false");
//	.contentType(MediaType.APPLICATION_JSON_UTF8)
//	.header("SESSIONNO", "");
	//.content(json) ;

//	mockMvc.perform(request);
        MvcResult mvcResult = mockMvc.perform(request).andReturn() ;
        System.out.println("调用返回");
//   
        int status = mvcResult.getResponse().getStatus();  
        String content = mvcResult.getResponse().getContentAsString();
        
        System.out.println("返回结果："+status);
        System.out.println(content);
        
        Assert.assertTrue("正确", status == 200);  
        Assert.assertFalse("错误", status != 200);  

}
@Test
public void test1() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/test/shiro/login.do")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("username", "admin")
            .param("password", "123456")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
    
}

}



/*
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:**web-config.xml")
@WebAppConfiguration
public class MockMvcTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

  @Test
  public void webappPublisherApi() throws Exception {

	  mockMvc.perform(get("/test/shiro/login.do?username=admin&password=123456&remember=true")
	  .accept(MediaType.APPLICATION_JSON_UTF8))
	
	  .andExpect(status().isOk())
	  .andExpect(content().string(containsString("中文测试")))
	  .andExpect(jsonPath("$.name").value("中文测试"));
  }
 
}
// */

//import static org.hamcrest.CoreMatchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.ogoodo.wx.test.api.main.App;


//
////////@RunWith(SpringJUnit4ClassRunner.class)
//////@ContextConfiguration(classes = App.class)
////////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//////@RunWith(SpringRunner.class)
//////@SpringBootTest
////////@AutoConfigureMockMvc
////@RunWith(SpringJUnit4ClassRunner.class)
//////@SpringApplicationConfiguration(classes=MockServletContext.class)//MockServletContext.class
////@ContextConfiguration(classes = MockServletContext.class)
////@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:**web-config.xml")
//@WebAppConfiguration
//public class MockMvcTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired    
//    private WebApplicationContext wac; // 注入WebApplicationContext    
//    
////    @Autowired    
////    private MockHttpSession session;// 注入模拟的http session    
////        
////    @Autowired    
////    private MockHttpServletRequest request;// 注入模拟的http request\    
//    
//    @Before // 在测试开始前初始化工作    
//    public void setup() {    
////        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();    
//    } 
// 
////    @MockBean
////    private UserVehicleService userVehicleService;
//
//    @Test
//    public void testExample() throws Exception {
////        given(this.userVehicleService.getVehicleDetails("sboot"))
////        		.willReturn(new VehicleDetails("Honda", "Civic"));
//    	
////        mvc.perform
////        		(get("/sboot/vehicle")
////        		.accept(MediaType.TEXT_PLAIN))
////        		
////        		.andExpect(status().isOk())
////        		.andExpect(content().string("Honda Civic"));
//    }
//
//    @Test
//    public void webappPublisherApi() throws Exception {
//        //MockHttpServletRequestBuilder.accept方法是设置客户端可识别的内容类型
//        //MockHttpServletRequestBuilder.contentType,设置请求头中的Content-Type字段,表示请求体的内容类型
//
////    		mvc.perform(get("/publishers/1")
////            .accept(MediaType.APPLICATION_JSON_UTF8))
////
////            .andExpect(status().isOk())
////            .andExpect(content().string(containsString("中文测试")))
////            .andExpect(jsonPath("$.name").value("中文测试"));
//    }
//
//}
