package com.natsu.mybatiscachedemo.entity;

public class Class {

  private int classId;
  private String className;

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder("Class:{");
    stringBuilder.append("classId = ").append(classId);
    stringBuilder.append(", className = '").append(className).append('\'');
    stringBuilder.append("}");
    return stringBuilder.toString();
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

  public int getClassId() {
    return classId;
  }
}
