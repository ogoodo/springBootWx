package com.ogoodo.wx.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.ogoodo.wx.dao.test.mapper")
@ComponentScan(basePackages={"com.ogoodo.wx.dao.test"}) // 会自动扫描这个package下的注解
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
