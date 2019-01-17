package com.natsu.springbootshirodemo.realm;

import com.natsu.springbootshirodemo.pojo.User;
import com.natsu.springbootshirodemo.service.PermissionService;
import com.natsu.springbootshirodemo.service.RoleService;
import com.natsu.springbootshirodemo.service.UserService;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseRealm extends AuthorizingRealm {

  @Autowired
  UserService userService;

  @Autowired
  RoleService roleService;

  @Autowired
  PermissionService permissionService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    // 进入到这,说明账号已经通过验证
    String userName = (String) principalCollection.getPrimaryPrincipal();
    // 通过servie获取角色和权限
    Set<String> permissions = permissionService.listPermissions(userName);
    Set<String> roles = roleService.listRoleNames(userName);
    // 授权对象
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    // 加入通过service获取到的角色和权限
    simpleAuthorizationInfo.setRoles(roles);
    simpleAuthorizationInfo.setStringPermissions(permissions);
    return simpleAuthorizationInfo;
  }

  /**
   * SecurityManager 委托给其进行身份验证
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    // 获取账号密码
    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
    String userName = authenticationToken.getPrincipal().toString();
    // 获取数据库中的密码
    User user = userService.getByName(userName);
    String passwordInDB = user.getPassword();
    String salt = user.getSalt();
    // 认证信息里存放账号密码, getName() 是当前Realm的集成方法, 通常返回当前类名: databaseRealm
    // 放入盐,通过配置的HashedCredentialsMatcher自助校验
    SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(userName,
        passwordInDB,
        ByteSource.Util.bytes(salt), getName());
    return simpleAuthorizationInfo;
  }
}
