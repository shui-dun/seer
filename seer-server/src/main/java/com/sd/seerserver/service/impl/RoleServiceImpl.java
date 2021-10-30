package com.sd.seerserver.service.impl;

import com.sd.seerserver.mapper.RoleMapper;
import com.sd.seerserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getRolesByUserName(String name) {
        return roleMapper.getRolesByUserName(name);
    }
}
