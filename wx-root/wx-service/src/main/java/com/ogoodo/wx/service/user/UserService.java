package com.ogoodo.wx.service.user;

import java.util.Map;

import com.ogoodo.wx.db.auto.dao.UUser;

public interface UserService {

    int insert(UUser record);
    
    String GetPasswordByUsername(String username);

    Map<String,Object> getUserList(UserQueryEntity query);
}
