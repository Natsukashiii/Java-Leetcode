package com.natsu.springbootshirodemo.service;

import com.natsu.springbootshirodemo.pojo.Role;

public interface RolePermissionService {

  void setPermissions(Role role, long[] permissionIds);

  void deleteByRole(long roleId);

  void deleteByPermission(long permissionId);
}
