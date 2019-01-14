package com.natsu.mybatiscachedemo.entity;

import java.io.Serializable;

public class Student implements Serializable {

  //
  private Integer id;
  //
  private String name;
  private Integer age;
  private String className;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder("StudentEntity:{");
    stringBuilder.append("id = ").append(id);
    stringBuilder.append(", name = '").append(name).append('\'');
    stringBuilder.append(", age = ").append(age);
    stringBuilder.append(", className = '").append(className).append('\'');
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}
