package com.ogoodo.wx.service.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogoodo.wx.dao.test.TestEntity;

@RestController
public class IndexController {

	@Autowired
	TestEntity entity = new TestEntity();

	@RequestMapping(value="/index")
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();

		TestEntity entity2 = new TestEntity();
		entity2.setUssername("chenxb2");
		entity2.setPassword("1234562");
		

		entity.setUssername("chenxb");
		entity.setPassword("123456");

		map.put("code", "10001");
		map.put("msg", "接口调用成功");
		map.put("entity", entity);
		map.put("entity2", entity2);
		return map;
	}
}
