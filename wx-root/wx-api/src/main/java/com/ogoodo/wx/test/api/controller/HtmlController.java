package com.ogoodo.wx.test.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HtmlController {

    @GetMapping("/static")  
    String test(HttpServletRequest request) {  
        //逻辑处理  
        request.setAttribute("key", "hello world");  
        return "index";  
    }
    
}
