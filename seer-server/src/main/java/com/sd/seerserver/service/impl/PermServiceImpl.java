package com.sd.seerserver.service.impl;

import com.sd.seerserver.mapper.PermMapper;
import com.sd.seerserver.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public Set<String> getPermsByRoles(Set<String> roles) {
        Set<String> ans = new HashSet<>();
        for (String role : roles) {
            ans.addAll(permMapper.getPermsByRole(role));
        }
        return ans;
    }
}
