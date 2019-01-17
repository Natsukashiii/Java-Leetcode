package com.natsu.springbootshirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

  @PostMapping("/login")
  public String login(Model model, String name, String password) {
    // 调用之前已经设置了Security Manager
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(name, password);
    try {
      // 调用login进行登录, 自动委托给Security Manager
      subject.login(token);
      // shiro自定义的session, 是controller中的request在shiro过滤器中的doFilterInternal方法,包装的
      // 两者操作session等价
      Session session = subject.getSession();
      session.setAttribute("subject", subject);
      return "redirect:index";
    } catch (AuthenticationException e) {
      model.addAttribute("error", "验证失败");
      // 返回login页面时, 会自动将上一行的验证错误信息返回
      return "login";
    }
  }

}
