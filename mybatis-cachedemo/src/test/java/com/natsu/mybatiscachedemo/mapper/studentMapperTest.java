package com.natsu.mybatiscachedemo.mapper;

import static org.junit.Assert.*;

import com.natsu.mybatiscachedemo.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class studentMapperTest {

  private SqlSessionFactory sqlSessionFactory;

  @Before
  public void setUp() throws Exception {
    sqlSessionFactory = new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsReader("mybatis-config.xml"));
  }

  @Test
  public void showCacheConfig() {
    System.out
        .println("local cache : " + sqlSessionFactory.getConfiguration().getLocalCacheScope());
    System.out.println(
        "secondary cache enabled: " + sqlSessionFactory.getConfiguration().isCacheEnabled());
  }

  /**
   * 开启一级缓存,范围为会话级别 执行结果可以看到只有第一次真正查询了数据库
   */
  @Test
  public void checkLocalCache() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    studentMapper studentMapper = sqlSession.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);

    System.out.println(studentMapper.getStudentById(1));
    System.out.println(studentMapper.getStudentById(1));
    System.out.println(studentMapper.getStudentById(1));

    sqlSession.close();
  }

  /**
   * 在修改操作后执行相同的查询,一级缓存失效
   */
  @Test
  public void addOperation() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    studentMapper studentMapper = sqlSession.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);
    System.out.println(studentMapper.getStudentById(1));
    // 增加对数据库的修改操作,在一次数据库会话中,如果对数据库发生了修改操作,一级缓存是否会失效
    System.out.println("add " + studentMapper.addStudent(buildStudent()));
    System.out.println(studentMapper.getStudentById(1));
  }

  private Student buildStudent() {
    Student studentEntity = new Student();
    studentEntity.setName("nana");
    studentEntity.setAge(20);
    return studentEntity;
  }

  /**
   * 开启两个SqlSession，在sqlSession1中查询数据，使一级缓存生效，在sqlSession2中更新数据库，验证一级缓存只在数据库会话内部共享。
   */
  @Test
  public void checkLocalCache2() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper = sqlSession1.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);

    // 在数据库执行了查询
    System.out.println("studentMapper读取数据: " + studentMapper.getStudentById(1));
    // 通过缓存进行查询
    System.out.println("studentMapper读取数据: " + studentMapper.getStudentById(1));
    // sqlSession2更新数据
    System.out.println("studentMapper2更新了" + studentMapper2.updateStudentName("喵喵", 1) + "个学生的数据");
    // sqlSession1读到脏数据,也就是一级缓存只在数据库会话内部共享
    System.out.println("studentMapper读取数据: " + studentMapper.getStudentById(1));
    System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));
  }
}