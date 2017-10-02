package com.ogoodo.wx.api.test;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.utils.valid.ServiceException;

@RestController
public class ValidController {

	  @GetMapping("/wx/api/test/valid/hello1")
	  public String hello1() {
	    int i = 1 / 0;
	    return "hello";
	  }

	  @GetMapping("/wx/api/test/valid/hello2")
	  public String hello2(Long id) {
	    String string = null;
	    string.length();
	    return "hello";
	  }

	  @GetMapping("/wx/api/test/valid/hello3")
	  public List<String> hello3() {
	    throw new ServiceException("自定义服务器错误异常");
	  }
}
