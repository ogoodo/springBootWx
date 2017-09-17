package com.ogoodo.wx.test.api.main;


import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
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
@MapperScan("com.ogoodo.wx.test.dao.mapper")
@ComponentScan(basePackages={
		"com.ogoodo.wx",
		"com.ogoodo.wx.test.api.controller",
		"com.ogoodo.wx.shiro",
		"com.ogoodo.wx.shiro.test.controller",
		"com.ogoodo.wx.test.api.main",
		"com.ogoodo.wx.test.service",
		"com.ogoodo.wx.test.dao"
	}) // 会自动扫描这个package下的注解
//@Configuration
public class App // extends WebMvcConfigurerAdapter
{
	public static void main(String[] args) {
		System.out.println("系统启动成功");
		SpringApplication.run(App.class, args);
	}
	
//    /**
//     * 利用fastjson替换掉jackson，且解决中文乱码问题
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    		System.out.print("============================================configureMessageConverters");;
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //处理中文乱码问题
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//    }
}
