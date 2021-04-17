package com.nileigee.service;

import com.nileigee.dao.UserInfoDao;
import com.nileigee.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserInfo findUserByNameAndPassword(UserInfo loginUser) {
        return userInfoDao.findUserByNameAndPassword(loginUser);
    }
}
