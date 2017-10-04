package com.ogoodo.wx.api.test;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.utils.valid.AjaxResult;
import com.ogoodo.wx.utils.valid.validator.FlagValidator;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



@RestController
public class ValidatorController {

//	public class TestEntityC {
//
//		@FlagValidator(values="1,2,3,a,b,c")
//		private String pageNum;
//
//		public String getPageNum() {
//			return pageNum;
//		}
//
//		public void setPageNum(String pageNum) {
//			this.pageNum = pageNum;
//		}
//
//	}
//
//	  @ApiOperation(value="测试自定义校验B", consumes="application/json;charset=utf-8")
//	   @ApiImplicitParam(name = "query", value = "自定义校验(不行)", required = true, dataType = "TestEntityC")
//	  @PostMapping(value = "/wx/api/test/validator/flagb", produces = {"application/json;charset=utf-8"})
//	  public AjaxResult hello1B(@RequestBody @Validated TestEntityC query) {
//	    return new AjaxResult().success("接口调用成功");
//	  }


	  @ApiOperation(value="测试自定义校验", consumes="application/json;charset=utf-8")
	   @ApiImplicitParam(name = "query", value = "获取用户列表", required = true, dataType = "ValidatorEntity")
	  @PostMapping(value = "/wx/api/test/validator/flag", produces = {"application/json;charset=utf-8"})
	  public AjaxResult hello1(@RequestBody @Validated ValidatorEntity query) {
		  //  @ModelAttribute
	    return new AjaxResult().success(query);
	  }
}
