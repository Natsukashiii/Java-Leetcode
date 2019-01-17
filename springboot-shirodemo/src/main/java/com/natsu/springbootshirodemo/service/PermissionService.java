package com.natsu.springbootshirodemo.service;

import com.natsu.springbootshirodemo.pojo.Permission;
import com.natsu.springbootshirodemo.pojo.Role;
import java.util.List;
import java.util.Set;

public interface PermissionService {

   Set<String> listPermissions(String userName);

   List<Permission> list();

   void add(Permission permission);

   void delete(Long id);

   Permission get(Long id);

   void update(Permission permission);

   List<Permission> list(Role role);

   boolean needInterceptor(String requestURI);

   Set<String> listPermissionURLs(String userName);

}