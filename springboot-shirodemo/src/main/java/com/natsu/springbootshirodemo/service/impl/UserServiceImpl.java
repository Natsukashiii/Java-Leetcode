package com.natsu.springbootshirodemo.service.impl;

import com.natsu.springbootshirodemo.mapper.UserMapper;
import com.natsu.springbootshirodemo.pojo.User;
import com.natsu.springbootshirodemo.pojo.UserExample;
import com.natsu.springbootshirodemo.service.UserRoleService;
import com.natsu.springbootshirodemo.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Autowired
  UserRoleService userRoleService;

  @Override
  public String getPassword(String name) {
    User user = getByName(name);
    if (user == null) {
      return null;
    }
    return user.getPassword();
  }

  @Override
  public User getByName(String name) {
    UserExample userExample = new UserExample();
    userExample.createCriteria().andNameEqualTo(name);
    List<User> users = userMapper.selectByExample(userExample);
    if (users.isEmpty()) {
      return null;
    }
    return users.get(0);
  }

  @Override
  public List<User> list() {
    UserExample userExample = new UserExample();
    userExample.setOrderByClause("id desc");
    return userMapper.selectByExample(userExample);
  }

  @Override
  public void add(User user) {
    userMapper.insert(user);
  }

  @Override
  public void delete(Long id) {
    userMapper.deleteByPrimaryKey(id);
    userRoleService.deleteByUser(id);
  }

  @Override
  public User get(Long id) {
    return userMapper.selectByPrimaryKey(id);
  }

  @Override
  public void update(User user) {
    userMapper.updateByPrimaryKeySelective(user);
  }
}
