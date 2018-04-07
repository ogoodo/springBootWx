package com.ogoodo.wx.api.test.shiro.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.db.test.dao.pojo.TestEntity;

@RestController
public class TestShiroController {

//	@Autowired
//	ValidatorEntity entity = new ValidatorEntity();

	@RequestMapping(value="/test")
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();

//		ValidatorEntity entity2 = new ValidatorEntity();
//		entity2.setUssername("chenxb2");
//		entity2.setPassword("1234562");
//		
//
//		entity.setUssername("chenxb");
//		entity.setPassword("123456");
//
//		map.put("code", "10001");
//		map.put("msg", "接口调用成功");
//		map.put("entity", entity);
//		map.put("entity2", entity2);
		return map;
	}
}
