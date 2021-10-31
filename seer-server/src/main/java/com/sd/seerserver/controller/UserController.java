package com.sd.seerserver.controller;

import com.sd.seerserver.entity.Response;
import com.sd.seerserver.entity.User;
import com.sd.seerserver.enumeration.StatusCodeEnum;
import com.sd.seerserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresGuest
    @PostMapping("/login")
    public Response<?> login(String username, String passwd) {
        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username, passwd);
            subject.login(token);
            return new Response<>(StatusCodeEnum.SUCCESS);
        } catch (UnknownAccountException e) {
            return new Response<>(StatusCodeEnum.USER_NOT_EXIST);
        } catch (IncorrectCredentialsException e) {
            return new Response<>(StatusCodeEnum.INCORRECT_CREDENTIALS);
        } catch (Exception e) {
            return new Response<>(StatusCodeEnum.SERVER_ERROR);
        }
    }

    @RequiresGuest
    @PostMapping("/signup")
    public Response<?> signup(String username, String passwd) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String newPasswd = new SimpleHash(Sha256Hash.ALGORITHM_NAME, passwd, salt, 3).toBase64();
        User user = new User(username, newPasswd, salt);
        try {
            userService.signup(user);
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username, passwd);
            subject.login(token);
        } catch (DataIntegrityViolationException e) {
            return new Response<>(StatusCodeEnum.USER_ALREADY_EXIST);
        }
        return new Response<>(StatusCodeEnum.SUCCESS);
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Response<?> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return new Response<>(StatusCodeEnum.SUCCESS);
    }

    @ApiOperation("根据用户名删除用户")
    @RequiresPermissions("user:delete")
    @GetMapping("/delete/{name}")
    public Response<?> delete(@PathVariable String name) {
        try {
            int ans = userService.deleteNotAdminUser(name);
            if (ans == 0) {
                return new Response<>(StatusCodeEnum.USER_NOT_EXIST);
            } else {
                return new Response<>(StatusCodeEnum.SUCCESS);
            }
        } catch (UnauthorizedException e) {
            return new Response<>(StatusCodeEnum.UNAUTHORIZED);
        }
    }

    @ApiOperation("列出所有用户的信息")
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public Response<Set<User>> list() {
        return new Response<>(StatusCodeEnum.SUCCESS, userService.listAll());
    }

    @ApiOperation("测试当前用户是否是登陆状态")
    @RequiresAuthentication
    @GetMapping("/isLogin")
    public Response<?> isLogin() {
        String name = ((User) SecurityUtils.getSubject().getPrincipal()).getName();
        return new Response<>(StatusCodeEnum.SUCCESS, name);
    }
}
