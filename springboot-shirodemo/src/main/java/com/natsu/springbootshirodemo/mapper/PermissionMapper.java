package com.natsu.springbootshirodemo.mapper;

import com.natsu.springbootshirodemo.pojo.Permission;
import com.natsu.springbootshirodemo.pojo.PermissionExample;
import java.util.List;

public interface PermissionMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	int insertSelective(Permission record);

	List<Permission> selectByExample(PermissionExample example);

	Permission selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);
}