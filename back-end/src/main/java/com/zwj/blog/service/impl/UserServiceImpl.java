package com.zwj.blog.service.impl;

import com.zwj.blog.domain.User;
import com.zwj.blog.repository.UserRepository;
import com.zwj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * code on 2017-11-10
 * 处理用户业务
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void updateAvatar(String url, String username) {

    }

    @Override
    public void updatePassword(User user) {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User loadUserByUserId(String userId) {
        return  this.userRepository.findOne(userId);
    }


}
