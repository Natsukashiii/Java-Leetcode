package com.natsu.springbootshirodemo.mapper;

import com.natsu.springbootshirodemo.pojo.User;
import com.natsu.springbootshirodemo.pojo.UserExample;
import java.util.List;
import javax.annotation.Resource;

@Resource
public interface UserMapper {
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}