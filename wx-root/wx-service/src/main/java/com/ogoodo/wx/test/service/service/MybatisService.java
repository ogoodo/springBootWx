package com.ogoodo.wx.test.service.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ogoodo.wx.test.dao.pojo.URole;
import com.ogoodo.wx.test.dao.pojo.URoleExample;


public interface MybatisService {

	long countByExample(URoleExample example);
	
    URole selectByPrimaryKey(Long userId);  

    List<URole> selectByExample(URoleExample example);

    int insert(URole record);

    int insertSelective(URole record);

    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);
}