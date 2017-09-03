package com.ogoodo.wx.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogoodo.wx.dao.test.mapper.URoleMapper;
import com.ogoodo.wx.dao.test.pojo.URole;
import com.ogoodo.wx.dao.test.pojo.URoleExample;

@Service("mybatisService")
public class MybatisServiceImpl implements MybatisService{

    @Autowired  
    private URoleMapper userDao;

    public long countByExample(URoleExample example) {
    		return userDao.countByExample(example);
    }
    
	// @Override
	public URole selectByPrimaryKey(Long userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	// @Override
	public int insert(URole record) {
		return userDao.insert(record);
	}

	// @Override
	public int insertSelective(URole record) {
		return userDao.insertSelective(record);
	}

	// @Override
	public List<URole> selectByExample(URoleExample example) {
		return userDao.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(URole record, URoleExample example) {
		return userDao.updateByExampleSelective(record, example);
	}  

//	@Override
//    public URole selectByPrimaryKey(long userId) {  
//        return userDao.selectByPrimaryKey(userId);  
//    }
  
}  
