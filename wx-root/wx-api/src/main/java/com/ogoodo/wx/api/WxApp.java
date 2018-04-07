package com.ogoodo.wx.api;


import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


//@MapperScan("com.ogoodo.wx.test.mybatis.mapper")
//@MapperScan("com.ogoodo.wx.test.mybatis.dao")
//@ComponentScan({"com.ogoodo.wx.*"})
//@EntityScan("com.ogoodo.wx.*")
//@EnableJpaRepositories("com.delivery.repository")

@SpringBootApplication
//@MapperScan("com.ogoodo.wx.test.dao.mapper")
//@MapperScan({"com.ogoodo.wx.db", "com.ogoodo.wx.test"})
@MapperScan("com.ogoodo.wx.db")
@ComponentScan(basePackages={
		"com.ogoodo.wx",
//		"com.ogoodo.wx.api.test.controller",
//		"com.ogoodo.wx.shiro",
//		"com.ogoodo.wx.api.test.shiro.controller",
//		"com.ogoodo.wx.api.test.main",
//		"com.ogoodo.wx.test.service",
//		"com.ogoodo.wx.test.dao"
	}) // 会自动扫描这个package下的注解
public class WxApp 
{

	public static void main(String[] args) {
		System.out.println("WX系统开始启动");
		SpringApplication.run(WxApp.class, args);
	}

}
