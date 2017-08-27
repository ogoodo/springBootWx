package com.ogoodo.wx.test.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 参考 http://blog.didispace.com/springbootexception/
 * @author chen
 *
 */
@Controller
public class ExceptionController {

	@RequestMapping("/wx/test/exception")
    public String json() throws MyException {
        throw new MyException(10004, "发生错误2");
    }
}
