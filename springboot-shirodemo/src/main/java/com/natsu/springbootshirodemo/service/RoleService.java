package com.natsu.springbootshirodemo.service;

import com.natsu.springbootshirodemo.pojo.Role;
import com.natsu.springbootshirodemo.pojo.User;
import java.util.List;
import java.util.Set;

public interface RoleService {

  Set<String> listRoleNames(String userName);

  List<Role> listRoles(String userName);

  List<Role> listRoles(User user);

  List<Role> list();

  void add(Role role);

  void delete(Long id);

  Role get(Long id);

  void update(Role role);
}
