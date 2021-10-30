package com.sd.seerserver.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    public Set<String> getRolesByUserName(String name);
}
