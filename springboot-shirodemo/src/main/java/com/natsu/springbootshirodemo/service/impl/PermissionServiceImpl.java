package com.natsu.springbootshirodemo.service.impl;

import com.natsu.springbootshirodemo.mapper.PermissionMapper;
import com.natsu.springbootshirodemo.mapper.RolePermissionMapper;
import com.natsu.springbootshirodemo.pojo.Permission;
import com.natsu.springbootshirodemo.pojo.PermissionExample;
import com.natsu.springbootshirodemo.pojo.Role;
import com.natsu.springbootshirodemo.pojo.RolePermission;
import com.natsu.springbootshirodemo.pojo.RolePermissionExample;
import com.natsu.springbootshirodemo.service.PermissionService;
import com.natsu.springbootshirodemo.service.RoleService;
import com.natsu.springbootshirodemo.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  PermissionMapper permissionMapper;
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;
  @Autowired
  RolePermissionMapper rolePermissionMapper;

  @Override
  public Set<String> listPermissions(String userName) {
    Set<String> result = new HashSet<>();
    List<Role> roles = roleService.listRoles(userName);

    List<RolePermission> rolePermissions = new ArrayList<>();

    for (Role role : roles) {
      RolePermissionExample example = new RolePermissionExample();
      example.createCriteria().andRidEqualTo(role.getId());
      List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
      rolePermissions.addAll(rps);
    }

    for (RolePermission rolePermission : rolePermissions) {
      Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
      result.add(p.getName());
    }

    return result;
  }

  @Override
  public void add(Permission u) {
    permissionMapper.insert(u);
  }

  @Override
  public void delete(Long id) {
    permissionMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void update(Permission u) {
    permissionMapper.updateByPrimaryKeySelective(u);
  }

  @Override
  public Permission get(Long id) {
    return permissionMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<Permission> list() {
    PermissionExample example = new PermissionExample();
    example.setOrderByClause("id desc");
    return permissionMapper.selectByExample(example);

  }

  @Override
  public List<Permission> list(Role role) {
    List<Permission> result = new ArrayList<>();
    RolePermissionExample example = new RolePermissionExample();
    example.createCriteria().andRidEqualTo(role.getId());
    List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
    for (RolePermission rolePermission : rps) {
      result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
    }

    return result;
  }

  @Override
  public boolean needInterceptor(String requestURI) {
    List<Permission> ps = list();
    for (Permission p : ps) {
      if (p.getUrl().equals(requestURI)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Set<String> listPermissionURLs(String userName) {
    Set<String> result = new HashSet<>();
    List<Role> roles = roleService.listRoles(userName);

    List<RolePermission> rolePermissions = new ArrayList<>();

    for (Role role : roles) {
      RolePermissionExample example = new RolePermissionExample();
      example.createCriteria().andRidEqualTo(role.getId());
      List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
      rolePermissions.addAll(rps);
    }

    for (RolePermission rolePermission : rolePermissions) {
      Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
      result.add(p.getUrl());
    }

    return result;
  }

}
