package com.sd.seerserver.config;

import com.sd.seerserver.entity.User;
import com.sd.seerserver.service.PermService;
import com.sd.seerserver.service.RoleService;
import com.sd.seerserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private UserService userService;
    @Lazy
    @Autowired
    private RoleService roleService;
    @Lazy
    @Autowired
    private PermService permService;

    //告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(3);
        this.setCredentialsMatcher(hashMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = user.getRoles();
        Set<String> perms = user.getPerms();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        User userDB = userService.findUserByName(username);

        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        // 查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        // SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限

        Set<String> roles = roleService.getRolesByUserName(userDB.getName());
        userDB.getRoles().addAll(roles);
        Set<String> perms = permService.getPermsByRoles(userDB.getRoles());
        userDB.getPerms().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPasswd(), getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }

        return info;
    }
}
