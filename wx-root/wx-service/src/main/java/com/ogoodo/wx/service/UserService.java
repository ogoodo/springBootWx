package com.ogoodo.wx.service;

import java.util.Map;

import com.ogoodo.wx.db.auto.dao.UUser;
import com.ogoodo.wx.service.entity.UserQueryEntity;

public interface UserService {

    int insert(UUser record);
    
    String GetPasswordByUsername(String username);

    Map<String,Object> getUserList(UserQueryEntity query);
}
