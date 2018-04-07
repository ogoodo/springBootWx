package com.ogoodo.wx.api.test;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.utils.valid.AjaxResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@RestController
public class ValidatorEnumController {

	  @ApiOperation(value="测试自定义校验", consumes="application/json;charset=utf-8")
	   @ApiImplicitParam(name = "query", value = "获取用户列表", required = true, dataType = "ValidatorEnumEntity")
	  @PostMapping(value = "/wx/api/test/validatorEnum", produces = {"application/json;charset=utf-8"})
	  public AjaxResult hello1(@RequestBody @Validated ValidatorEnumEntity query) {
		  //  @ModelAttribute
//		throw new IllegalArgumentException("测试参数异常!");
	    return new AjaxResult().success(query);
	  }
}