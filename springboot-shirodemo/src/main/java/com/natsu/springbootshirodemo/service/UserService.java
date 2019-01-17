package com.natsu.springbootshirodemo.service;

import com.natsu.springbootshirodemo.pojo.User;
import java.util.List;

public interface UserService {

  String getPassword(String name);

  User getByName(String name);

  List<User> list();

  void add(User user);

  void delete(Long id);

  User get(Long id);

  void update(User user);
}
