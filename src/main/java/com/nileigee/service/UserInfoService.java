package com.nileigee.service;

import com.nileigee.entity.UserInfo;

public interface UserInfoService {

    /**
     * 1.登陆方法
     * @param loginUser
     * @return
     */
    UserInfo findUserByNameAndPassword(UserInfo loginUser);
}
