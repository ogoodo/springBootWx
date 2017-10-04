package com.ogoodo.wx.api.test;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.utils.valid.AjaxResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class MessageConverterController {

	  @ApiOperation(value="测试自定义校验", consumes="application/json;charset=utf-8")
	   @ApiImplicitParam(name = "query", value = "获取用户列表", required = true, dataType = "MessageConverterEntity")
	  @PostMapping(value = "/wx/api/test/messageConverter", produces = {"application/json;charset=utf-8"})
	  public AjaxResult hello1(@RequestBody @Validated MessageConverterEntity query) {
		  //  @ModelAttribute
	    return new AjaxResult().success(query);
	  }
}
