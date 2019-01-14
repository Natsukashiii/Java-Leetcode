package com.natsu.mybatiscachedemo.mapper;

import com.natsu.mybatiscachedemo.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface studentMapper {

  public Student getStudentById(int id);

  public int addStudent(Student student);

  public int updateStudentName(@Param("name") String name, @Param("id") int id);

  public Student getStudentByIdWithClassInfo(int id);

}
