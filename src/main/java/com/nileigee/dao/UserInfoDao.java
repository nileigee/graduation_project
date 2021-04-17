package com.nileigee.dao;

import com.nileigee.entity.UserInfo;

public interface UserInfoDao {
    /**
     * 1.登陆方法
     * @param loginUser
     * @return
     */
     UserInfo findUserByNameAndPassword(UserInfo loginUser);
}
