package com.zwj.blog.service;

import com.zwj.blog.domain.User;

public interface UserService {

    User loadUserByUsername(String userName);

    void updateAvatar(String url, String userName);

    void updatePassword(User user);

    User getCurrentUser();

    User loadUserByUserId(String userId);
}
