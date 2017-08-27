package com.ogoodo.wx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping(value="/index")
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("code", "10001");
		map.put("msg", "接口调用成功");
		return map;
	}
}
