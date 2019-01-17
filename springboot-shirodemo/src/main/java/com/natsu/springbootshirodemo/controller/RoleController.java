package com.natsu.springbootshirodemo.controller;

import com.natsu.springbootshirodemo.pojo.Permission;
import com.natsu.springbootshirodemo.pojo.Role;
import com.natsu.springbootshirodemo.service.PermissionService;
import com.natsu.springbootshirodemo.service.RolePermissionService;
import com.natsu.springbootshirodemo.service.RoleService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("config")
public class RoleController {

  @Autowired
  RoleService roleService;
  @Autowired
  RolePermissionService rolePermissionService;
  @Autowired
  PermissionService permissionService;

  @RequestMapping("listRole")
  public String list(Model model) {
    List<Role> roles = roleService.list();
    model.addAttribute("rs", roles);

    Map<Role, List<Permission>> role_permissions = new HashMap<>();

    for (Role role : roles) {
      List<Permission> permissions = permissionService.list(role);
      role_permissions.put(role, permissions);
    }
    model.addAttribute("role_permissions", role_permissions);
    return "listRole";
  }

  @RequestMapping("editRole")
  public String list(Model model, long id) {
    Role role = roleService.get(id);
    model.addAttribute("role", role);

    List<Permission> ps = permissionService.list();
    model.addAttribute("ps", ps);

    List<Permission> currentPermissions = permissionService.list(role);
    model.addAttribute("currentPermissions", currentPermissions);

    return "editRole";
  }

  @RequestMapping("updateRole")
  public String update(Role role, long[] permissionIds) {
    rolePermissionService.setPermissions(role, permissionIds);
    roleService.update(role);
    return "redirect:listRole";
  }

  @RequestMapping("addRole")
  public String list(Model model, Role role) {
    System.out.println(role.getName());
    System.out.println(role.getDesc_());
    roleService.add(role);
    return "redirect:listRole";
  }

  @RequestMapping("deleteRole")
  public String delete(Model model, long id) {
    roleService.delete(id);
    return "redirect:listRole";
  }
}
