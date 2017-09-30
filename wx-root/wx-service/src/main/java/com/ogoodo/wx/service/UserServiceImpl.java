package com.ogoodo.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogoodo.wx.db.auto.dao.UUser;
import com.ogoodo.wx.db.auto.mapper.UUserMapper;


@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired  
    private UUserMapper userDao;
 
	@Override
	public int insert(UUser record) {
		return userDao.insert(record);
	}

	
}
