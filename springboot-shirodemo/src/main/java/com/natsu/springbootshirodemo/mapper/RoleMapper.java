package com.natsu.springbootshirodemo.mapper;

import com.natsu.springbootshirodemo.pojo.Role;
import com.natsu.springbootshirodemo.pojo.RoleExample;
import java.util.List;
import javax.annotation.Resource;

public interface RoleMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	int insertSelective(Role record);

	List<Role> selectByExample(RoleExample example);

	Role selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);
}