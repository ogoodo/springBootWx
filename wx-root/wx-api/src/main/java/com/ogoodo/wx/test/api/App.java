package com.ogoodo.wx.test.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@MapperScan("com.ogoodo.wx.test.mybatis.mapper")
//@MapperScan("com.ogoodo.wx.test.mybatis.dao")
//@ComponentScan({"com.ogoodo.wx.*"})
//@EntityScan("com.ogoodo.wx.*")
//@EnableJpaRepositories("com.delivery.repository")

@SpringBootApplication
@MapperScan("com.ogoodo.wx.test.dao.mapper")
@ComponentScan(basePackages={"com.ogoodo.wx.test.api", "com.ogoodo.wx.test.service", "com.ogoodo.wx.test.dao"}) // 会自动扫描这个package下的注解
public class App 
{
	public static void main(String[] args) {
		System.out.println("系统启动成功");
		SpringApplication.run(App.class, args);
	}
}
