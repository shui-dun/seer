package com.sd.seerserver.service;

import com.sd.seerserver.entity.User;

import java.util.Set;

public interface UserService {

    public User findUserByName(String username);

    public void signup(User user);

    public int deleteNotAdminUser(String name);

    Set<User> listAll();
}
