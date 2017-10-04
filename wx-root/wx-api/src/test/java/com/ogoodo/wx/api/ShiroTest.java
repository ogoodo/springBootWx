package com.ogoodo.wx.api;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogoodo.wx.shiro.test.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//@SpringBootTest(classes = App.class)
//@WebAppConfiguration
//@WebIntegrationTest("server.port:0")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WxApp.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ShiroTest {

//    private String dateReg;
//    private Pattern pattern;
//    private TestRestTemplate template = new TestRestTemplate();
    @Autowired
    private TestRestTemplate restTemplate;
//    @Value("${local.server.port}")// 注入端口号
    private int port=8011;

//    @Test
//    public void test3(){
//        String url = "http://localhost:"+port+"/test/shiro/login2.do?username=admin&password=123456&remember=true";
////        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
////        map.add("name", "Tom");  
////        map.add("name1", "Lily");
//        String result = restTemplate.getForObject(url, String.class);
//        System.out.println(result);
//        assertNotNull(result);
//        assertThat(result, Matchers.containsString("\"code\":\"10000\""));
//    }

    HttpEntity<String> getContent(String content) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>(content, headers);
    		return formEntity;
    	}
    @Test
    public void test4() throws JsonProcessingException{
//    		User user = new User();
//    		user.setUsername("admin");
//    		user.setPassword("123456");
//    		user.setRemember(true);
//    		ObjectMapper mapper = new ObjectMapper();
//        HttpEntity<String> formEntity = this.getContent(mapper.writeValueAsString(user));
//        String url = "http://localhost:"+port+"/test/shiro/login.do";
//        String result = restTemplate.postForObject(url, formEntity, String.class);
//        System.out.println(result);
//        assertNotNull(result);
//        assertThat(result, Matchers.containsString("\"code\":\"10000\""));
    }

    @Test
    public void webappBookIsbnApi() {
//        Book book = restTemplate.getForObject("http://localhost:" + port +"/books/9876-5432-1111", Book.class);
//        assertNotNull(book);
//        assertEquals("中文测试", book.getPublisher().getName());
    }

//    @Test  
//    public void testMain() {  
//        fail("Not yet implemented");  
//    }  
//  
//    @Test  
//    public void testTest() {  
//        System.out.println("@Test");//调用自己要测试的方法  
//    } 
    
//    @Test  
//    public void testAssert() {  
//        assertEquals("chenleixing","chenlei");  
//    }  
      
//    @Test(timeout=1)  
//    public void testTimeout() {  
//        System.out.println("超时测试");  
//    }  
  
    @Before  
    public void testBefore(){  
        System.out.println("@Before");  
    }  
      
    @BeforeClass  
    public static void testBeforeClass(){//必须为静态方法  
        System.out.println("@BeforeClass");  
    }  
      
    @After  
    public void testAfter(){  
        System.out.println("@After");  
    }  
      
    @AfterClass  
    public static void testAfterClass(){//必须为静态方法  
        System.out.println("@AfterClass");  
    }  
      
    @Ignore  
    public void testIgnore(){  
        System.out.println("@Ignore");  
    } 
    
}
