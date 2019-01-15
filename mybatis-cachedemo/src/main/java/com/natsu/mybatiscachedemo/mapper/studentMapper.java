package com.natsu.mybatiscachedemo.mapper;

import com.natsu.mybatiscachedemo.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface studentMapper {

  public Student getStudentById(int id);

  public List<Student> getStudentByIdSQL(@Param("id") Integer id);

  public int addStudent(Student student);

  public int updateStudentName(@Param("name") String name, @Param("id") int id);

  public Student getStudentByIdWithClassInfo(int id);

}
