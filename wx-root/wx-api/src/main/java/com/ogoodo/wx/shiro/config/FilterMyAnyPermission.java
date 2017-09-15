package com.ogoodo.wx.shiro.config;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 

public class FilterMyAnyPermission implements Filter {  

    private final Logger log = LoggerFactory.getLogger(getClass());  
  
//    @Resource  
//    private CommonConfig commonConfig;  
  
    @Override  
    public void destroy() {  
        log.info("" + getClass() + " destroy");  
  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain mappedValue) throws IOException, ServletException {  
//        log.info("" + getClass() + " doFilter " + commonConfig);  
    		mappedValue.doFilter(request, response);  
//        Subject subject = getSubject(request, response);
//        String[] perms = (String[]) mappedValue;
//        for (String perm : perms) {
//            if (subject.isPermitted(perm)) {
//                return true;
//            }
//        }
//        return false;
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
        log.info("" + getClass() + " init");  
  
    }  
  
} 
