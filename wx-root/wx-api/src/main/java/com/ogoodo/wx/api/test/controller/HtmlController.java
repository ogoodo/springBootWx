package com.ogoodo.wx.api.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HtmlController {

    @GetMapping("/statichtml")  
    String test(HttpServletRequest request) {  
        //逻辑处理  
        request.setAttribute("key", "hello world");  
        return "index";  
    }

//    @GetMapping("/html-static")  
//    String test2(HttpServletRequest request) {  
//        //逻辑处理  
//        request.setAttribute("key", "hello world");  
//        return "index";  
//    }
    
}
