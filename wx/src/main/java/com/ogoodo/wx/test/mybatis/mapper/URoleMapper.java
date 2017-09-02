package com.ogoodo.wx.test.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ogoodo.wx.test.mybatis.pojo.URole;
import com.ogoodo.wx.test.mybatis.pojo.URoleExample;

public interface URoleMapper {
	
	// 按条件计数
    long countByExample(URoleExample example);

    // 按条件删除
    int deleteByExample(URoleExample example);

    // 按主键删除
    int deleteByPrimaryKey(Long id);

    // 插入数据 返回插入条数?
    // 如果支持返回id xml里insert要加上这三个属性 keyProperty="id" keyColumn="id" useGeneratedKeys="true"
    int insert(URole record);

    int insertSelective(URole record);

    // 可以分页
    List<URole> selectByExample(URoleExample example);

    // 选择由主键
    URole selectByPrimaryKey(Long id);

    /**
     * @param record 第一个参数 是要修改的部分值组成的对象，其中有些属性为null则表示该项不修改。
     * @param example 第二个参数 是一个对应的查询条件的类， 通过这个类可以实现 order by 和一部分的where 条件。
     */
    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByExample(@Param("record") URole record, @Param("example") URoleExample example);

    // updateByPrimaryKeySelective会对字段进行判断再更新(如果为Null就忽略更新)，如果你只想更新某一字段，可以用这个方法
    int updateByPrimaryKeySelective(URole record);

    	// updateByPrimaryKey对你注入的字段全部更新
    int updateByPrimaryKey(URole record);
}
