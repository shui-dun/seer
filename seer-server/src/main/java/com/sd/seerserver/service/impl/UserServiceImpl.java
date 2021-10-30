package com.sd.seerserver.service.impl;

import com.sd.seerserver.entity.User;
import com.sd.seerserver.mapper.CommentMapper;
import com.sd.seerserver.mapper.RoleMapper;
import com.sd.seerserver.mapper.UserMapper;
import com.sd.seerserver.service.RoleService;
import com.sd.seerserver.service.UserService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    @Transactional
    public void signup(User user) {
        userMapper.insertUser(user);
        roleMapper.insertUserRole(user.getName(), "normal");
    }

    @Override
    @Transactional
    public int deleteNotAdminUser(String name) {
        Set<String> roleSet = roleService.getRolesByUserName(name);
        if (roleSet.contains("admin")) {
            throw new UnauthorizedException("无权删除该用户");
        }
        commentMapper.deleteByUsername(name);
        roleMapper.deleteByUsername(name);
        return userMapper.deleteUser(name);
    }

    @Override
    public Set<User> listAll() {
        return userMapper.listAll();
    }
}
