package com.natsu.springbootshirodemo.service;

import com.natsu.springbootshirodemo.pojo.User;

public interface UserRoleService {

  void setRoles(User user, long[] roleIds);

  void deleteByUser(long userId);

  void deleteByRole(long roleId);
}
