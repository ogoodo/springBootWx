package com.ogoodo.wx.db.auto.mapper;

import com.ogoodo.wx.db.auto.dao.UPermission;
import com.ogoodo.wx.db.auto.dao.UPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UPermissionMapper {
    long countByExample(UPermissionExample example);

    int deleteByExample(UPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    List<UPermission> selectByExample(UPermissionExample example);

    UPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByExample(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);
}