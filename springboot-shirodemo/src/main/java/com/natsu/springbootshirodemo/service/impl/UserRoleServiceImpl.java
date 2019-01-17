package com.natsu.springbootshirodemo.service.impl;

import com.natsu.springbootshirodemo.mapper.UserRoleMapper;
import com.natsu.springbootshirodemo.pojo.User;
import com.natsu.springbootshirodemo.pojo.UserRole;
import com.natsu.springbootshirodemo.pojo.UserRoleExample;
import com.natsu.springbootshirodemo.service.UserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

  @Autowired
  UserRoleMapper userRoleMapper;

  @Override
  public void setRoles(User user, long[] roleIds) {
    // 删除用户当前已有角色
    UserRoleExample userRoleExample = new UserRoleExample();
    userRoleExample.createCriteria().andUidEqualTo(user.getId());
    List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
    for (UserRole userRole : userRoles) {
      userRoleMapper.deleteByPrimaryKey(userRole.getId());
    }

    // 设置新的角色关系
    if (roleIds!=null){
      for (long rid : roleIds) {
        UserRole userRole = new UserRole();
        userRole.setRid(rid);
        userRole.setUid(user.getId());
        userRoleMapper.insert(userRole);
      }
    }
  }

  @Override
  public void deleteByUser(long userId) {
    UserRoleExample userRoleExample = new UserRoleExample();
    userRoleExample.createCriteria().andUidEqualTo(userId);
    List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
    for (UserRole userRole : userRoles) {
      userRoleMapper.deleteByPrimaryKey(userRole.getId());
    }
  }

  @Override
  public void deleteByRole(long roleId) {
    UserRoleExample userRoleExample = new UserRoleExample();
    userRoleExample.createCriteria().andUidEqualTo(roleId);
    List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
    for (UserRole userRole : userRoles) {
      userRoleMapper.deleteByPrimaryKey(userRole.getId());
    }
  }
}
