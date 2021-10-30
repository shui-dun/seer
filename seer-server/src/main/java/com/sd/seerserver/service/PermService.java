package com.sd.seerserver.service;

import java.util.Set;

public interface PermService {
    public Set<String> getPermsByRoles(Set<String> roles);
}
