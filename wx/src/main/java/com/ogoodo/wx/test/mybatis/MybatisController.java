package com.ogoodo.wx.test.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.ogoodo.wx.test.mybatis.pojo.URole;
import com.ogoodo.wx.test.mybatis.pojo.URoleExample;
import com.ogoodo.wx.test.mybatis.service.MybatisService;

@Controller
public class MybatisController {


	@Autowired  
    private MybatisService mybatisService; 
	
	
	private static Logger logger = LoggerFactory.getLogger(MybatisController.class);

	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/select?userid=13
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/select")
	public Map<String,Object> select(long userid, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
		try {
			URole user = mybatisService.selectByPrimaryKey(userid);
			map.put("data", user);
	        logger.debug("查找结果" + user);
		} catch(Exception ex) {
			System.out.println("捕获到错误: " + ex.getMessage());
		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}

	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/page?page=1&count=10
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/page")
	public Map<String,Object> page(int page, int count, Locale locale){
		System.out.println("/test/mybatis/page: ");
        Map<String,Object> map=new HashMap<String,Object>();
		try {
			URoleExample example = new URoleExample();
			example.setOffset(page * count);
			example.setLimit(count);
			example.setOrderByClause("name desc, type asc");
			PageHelper.offsetPage(1, 13);
			List<URole> list = mybatisService.selectByExample(example);
			map.put("data", list);
	        logger.debug("查找结果" + list);
		} catch(Exception ex) {
			System.out.println("捕获到错误: " + ex.getMessage());
		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}

	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/page-helper?like=ch&pageNum=1&pageSize=8
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/page-helper")
	public Map<String,Object> pageHelper(String like, int pageNum, int pageSize, Locale locale){
//		PageHelper.offsetPage(pageNum, pageSize);
		// List<URole> list = mybatisService.selectByExample(null);
//		List<URole> list2 = PageTool.execute(mybatisService, "selectByExample", URoleExample.class);
//		Map<String,Object> map = PageUtil.Change(list2, "请求参数校验成功！@@");

		URoleExample example = new URoleExample();
		if(StringUtils.hasText(like)){
			URoleExample.Criteria criteria = example.createCriteria();  
			like = "%" + like + "%";
			criteria.andNameLike(like); 	 
		}  
		// Map<String,Object> map = PageTool.select(mybatisService, example, pageNum, pageSize, "请求参数校验成功！@1@");
        Map<String,Object> map=new HashMap<String,Object>();
        return map;
	}

	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/insert?name=chen&type=123
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/insert?name=chen&type=
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/insert")
	public Map<String,Object> insert(String name, String type, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
		try {
			URole role = new URole();
			role.setName(name);
			if(StringUtils.hasText(type)) {
				role.setType(type);
			}
			long insertCount = mybatisService.insert(role);
			// long result = mybatisService.insertSelective(role);
			map.put("data", role);
			map.put("insertCount", insertCount);
			map.put("newId", role.getId());
	        logger.debug("查找结果" + role);
		} catch(Exception ex) {
			System.out.println("捕获到错误: " + ex.getMessage());
		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}
	
	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/bat-insert?name=chen&type=123
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/bat-insert")
	public Map<String,Object> bat(String name, String type, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
		try {
			for(int i = 0; i < 88; i++) {
				URole role = new URole();
				role.setName(name + i);
				role.setType(type + i);
				long result = mybatisService.insertSelective(role);
				map.put("data" + i, role);
				map.put("result" + i, result);
		        logger.debug("查找结果" + role);
			}
		} catch(Exception ex) {
			System.out.println("捕获到错误: " + ex.getMessage());
		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}

	/**
	 * http://localhost:8080/HelloSpringMVC/test/mybatis/count
	 */
	@ResponseBody
	@RequestMapping(value="/test/mybatis/count")
	public Map<String,Object> count(Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
		try {
			long result = mybatisService.countByExample(null);
			map.put("data", result);
		} catch(Exception ex) {
			System.out.println("捕获到错误: " + ex.getMessage());
		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
	}

    	/**
    	 * http://localhost:8080/HelloSpringMVC/test/mybatis/count-where?like=chen
    	 */
    	@ResponseBody
    	@RequestMapping(value="/test/mybatis/count-where")
    	public Map<String,Object> countWhere(String like, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
    		try {
    			URoleExample example = new URoleExample();
    			if(StringUtils.hasText(like)){
    				URoleExample.Criteria criteria = example.createCriteria();  
    				like = "%" + like + "%";
    				criteria.andNameLike(like); 	 
    			}  
    			long result = mybatisService.countByExample(example);
    			map.put("data", result);
    		} catch(Exception ex) {
    			System.out.println("捕获到错误: " + ex.getMessage());
    		}
            map.put("code", "10001");
            map.put("msg", "请求参数校验成功！！！");
            return map;
    	}
	

    	/**
    	 * http://localhost:8080/HelloSpringMVC/test/mybatis/update-where?name=updateWhere&type=&like=chen
    	 * http://localhost:8080/HelloSpringMVC/test/mybatis/update-where?name=updateWhere&type=123&like=chen
    	 */
    	@ResponseBody
    	@RequestMapping(value="/test/mybatis/update-where")
    	public Map<String,Object> updateWhere(String name, String type, String like, Locale locale){
        Map<String,Object> map=new HashMap<String,Object>();
		try {
    			URoleExample example = new URoleExample();
    			if(StringUtils.hasText(like)){
    				URoleExample.Criteria criteria = example.createCriteria();
    				like = "%" + like + "%";
    				criteria.andNameLike(like);
//    				criteria.andAgeIn(Arrays.asList({10,20,30}));
    			}
			URole role = new URole();
			role.setName(name);
			role.setType(type);
    			long result = mybatisService.updateByExampleSelective(role, example);
    			map.put("data", result);
    		} catch(Exception ex) {
    			System.out.println("捕获到错误: " + ex.getMessage());
    		}
        map.put("code", "10001");
        map.put("msg", "请求参数校验成功！！！");
        return map;
    	}
    	
}

