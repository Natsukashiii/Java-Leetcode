package com.natsu.springbootshirodemo.controller;

import com.natsu.springbootshirodemo.pojo.Permission;
import com.natsu.springbootshirodemo.service.PermissionService;
import java.util.List;
import javax.jws.WebParam.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("config")
public class PermissionController {

  @Autowired
  PermissionService permissionService;

  @RequestMapping("listPermission")
  public String list(Model model) {
    List<Permission> permissions = permissionService.list();
    model.addAttribute("ps", permissions);
    return "listPermission";
  }

  @RequestMapping("editPermission")
  public String list(Model model, long id) {
    Permission permission = permissionService.get(id);
    model.addAttribute("permission", permission);
    return "editPermission";
  }

  @RequestMapping("updatePermission")
  public String update(Permission permission) {

    permissionService.update(permission);
    return "redirect:listPermission";
  }

  @RequestMapping("addPermission")
  public String list(Model model, Permission permission) {
    System.out.println(permission.getName());
    System.out.println(permission.getDesc_());
    permissionService.add(permission);
    return "redirect:listPermission";
  }

  @RequestMapping("deletePermission")
  public String delete(Model model, long id) {
    permissionService.delete(id);
    return "redirect:listPermission";
  }
}
