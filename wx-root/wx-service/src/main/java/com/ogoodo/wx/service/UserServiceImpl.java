package com.ogoodo.wx.service;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogoodo.wx.db.auto.dao.UUser;
import com.ogoodo.wx.db.auto.dao.UUserExample;
import com.ogoodo.wx.db.auto.mapper.UUserMapper;


@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired  
    private UUserMapper userDao;
// 	@Autowired
// 	UUserMapper usermapper;
 
	@Override
	public int insert(UUser record) {

		// MD5盐值加密生成复杂密码
		ByteSource credSalt = ByteSource.Util.bytes(record.getEmail());
		// 这个值应该是注册的时候存储到数据库里面
		SimpleHash saltPassword = new SimpleHash("MD5", record.getPswd(), credSalt, 1024);
		record.setPswd(saltPassword.toString());
		return userDao.insert(record);
	}

	@Override
	public String GetPasswordByUsername(String username) {
	    UUserExample example = new UUserExample();
	    UUserExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(username);
//		UUser user = usermapper.selectByPrimaryKey((long)1);
		// 如果这个地方提示类找不到， 很有可能是wx-db里xml没有打包到jar里去
		List<UUser> user = userDao.selectByExample(example);
		return user.get(0).getPswd();
	}

	
}
