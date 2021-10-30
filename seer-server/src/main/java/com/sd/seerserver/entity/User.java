package com.sd.seerserver.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户")
public class User {

    private String name;
    private String passwd;

    @ApiModelProperty("密码的盐")
    private String salt;

    @ApiModelProperty("用户所有角色值，用于shiro做角色权限的判断")
    private Set<String> roles = new HashSet<>();

    @ApiModelProperty("用户所有权限值，用于shiro做资源权限的判断")
    private Set<String> perms = new HashSet<>();

    public User(String name, String passwd, String salt) {
        this.name = name;
        this.passwd = passwd;
        this.salt = salt;
    }
}
