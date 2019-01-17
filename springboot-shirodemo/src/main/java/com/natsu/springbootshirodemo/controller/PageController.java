package com.natsu.springbootshirodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class PageController {

  @RequestMapping("index")
  public String index() {
    return "index";
  }

  @RequestMapping("deleteOrder")
  public String deleteOrder() {
    return "deleteOrder";
  }

  @RequestMapping("deleteProduct")
  public String deleteProduct() {
    return "deleteProduct";
  }

  @RequestMapping("listProduct")
  public String listProduct() {
    return "listProduct";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping("unauthorized")
  public String noPerms() {
    return "unauthorized";
  }
}
