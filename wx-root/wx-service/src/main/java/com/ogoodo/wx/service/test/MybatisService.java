package com.ogoodo.wx.service.test;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ogoodo.wx.db.auto.dao.URole;
import com.ogoodo.wx.db.auto.dao.URoleExample;



public interface MybatisService {

	long countByExample(URoleExample example);
	
    URole selectByPrimaryKey(Long userId);  

    List<URole> selectByExample(URoleExample example);

    int insert(URole record);

    int insertSelective(URole record);

    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);
}
