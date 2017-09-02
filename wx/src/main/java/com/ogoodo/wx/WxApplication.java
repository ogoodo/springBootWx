package com.ogoodo.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ogoodo.wx.test.mybatis.mapper")
//@MapperScan("com.ogoodo.wx.test.mybatis.dao")
@ComponentScan(basePackages={"com.ogoodo","com.ogoodo.wx"})
//@ComponentScan({"com.ogoodo.wx.*"})
//@EntityScan("com.ogoodo.wx.*")
//@EnableJpaRepositories("com.delivery.repository")
public class WxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}
}
