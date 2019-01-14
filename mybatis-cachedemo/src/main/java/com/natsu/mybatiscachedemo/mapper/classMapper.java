package com.natsu.mybatiscachedemo.mapper;

import org.apache.ibatis.annotations.Param;

public interface classMapper {
  public int updateClassName(@Param("name") String className, @Param("id") int id);
}
