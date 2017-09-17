package com.ogoodo.wx.test.api.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//    				.groupName("test")//创建多个分组
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ogoodo.wx.test.api.controller"))
////                .apis(RequestHandlerSelectors.basePackage("com.ogoodo.wx.shiro.test.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Spring Boot中使用Swagger2构建RESTful APIs")
//                .description("更多请关注：http://wwww.ogoodo.com/")
//                .termsOfServiceUrl("http://wwww.ogoodo.com/")
//                .contact("chen")
//                .version("1.0")
//                .build();
//    }

    
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//        			.groupName("demo")//创建多个分组
                .apiInfo(demoApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ogoodo.wx"))
//                .apis(RequestHandlerSelectors.basePackage("com.ogoodo.wx.shiro.test.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo demoApiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")//大标题
                .description("Swagger2 demo")//详细描述
                .termsOfServiceUrl("http://blog.ogoodo.com")
//                .contact("chen")//作者
                .version("1.0")//版本
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
 
}
