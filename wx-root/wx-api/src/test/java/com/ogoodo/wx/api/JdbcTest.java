package com.ogoodo.wx.api;

import java.util.List;  

import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ogoodo.wx.shiro.config.MyShiroService;
  

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = App.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SpringApplicationConfiguration(classes = Application.class)// 1.4.0 前版本  
public class JdbcTest {  

	@Autowired
	private MyShiroService myShiroService;
	
	@Test  
	public void findAllUsers() {  
		System.out.println(myShiroService.testMethod());  
	}  
  
//        @Test  
//        public void findAllUsers()  {  
//            List<User> users = userRepository.findAll();  
//            System.out.println(users);  
//              
//  
//        }  
//  
//        @Test  
//        public void findUserById()  {  
//            User user = userRepository.findUserById(1);  
//          
//        }  
//        @Test  
//        public void updateById()  {  
//            User newUser = new User(3, "JackChen", "JackChen@qq.com");  
//            userRepository.update(newUser);  
//            User newUser2 = userRepository.findUserById(newUser.getId());  
//           
//        }  
//          
//          
//          
//        @Test  
//        public void createUser() {  
//            User user = new User(0, "tom", "tom@gmail.com");  
//            User savedUser = userRepository.create(user);  
//         
//        }  
}  

