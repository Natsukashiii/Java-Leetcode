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
  public void testMybatis(){
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

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

    // 直接执行SQL语句
//    String statement = "com.natsu.mybatiscachedemo.mapper.studentMapper.getStudentById";
//    Student res =sqlSession.selectOne(statement,1);
//    System.out.println(res);

    System.out.println(studentMapper.getStudentByIdSQL(4));
//    System.out.println(studentMapper.getStudentById(1));
//    System.out.println(studentMapper.getStudentById(1));
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

  /**
   * SqlSession没有调用commit()方法时, 二级缓存不起作用
   */
  @Test
  public void testSecondaryCache() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper1 = sqlSession1.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(
        com.natsu.mybatiscachedemo.mapper.studentMapper.class);

    System.out.println("studentMapper1: " + studentMapper1.getStudentById(1));
    System.out.println("studentMapper2: " + studentMapper2.getStudentById(2));
  }

  /**
   * commit 之后, sqlSession2 查询使用了缓存, 命中率0.5
   */
  @Test
  public void testSecondaryCachewithCommit() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper = sqlSession1.getMapper(studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(studentMapper.class);

    System.out.println("studentMapper1: " + studentMapper.getStudentById(1));
//    sqlSession1.commit();
    sqlSession1.close();
    System.out.println("studentMapper2: " + studentMapper2.getStudentById(1));
  }

  /**
   * 更新之后 sqlSession2 并没有走缓存
   */
  @Test
  public void testSecondaryCachewithUpdate() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession3 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper1 = sqlSession1.getMapper(studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(studentMapper.class);
    studentMapper studentMapper3 = sqlSession3.getMapper(studentMapper.class);

    System.out.println("studentMapper1: " + studentMapper1.getStudentById(1));
    sqlSession1.close();
    System.out.println("studentMapper2: " + studentMapper2.getStudentById(1));

    studentMapper3.updateStudentName("fangfang", 1);
    sqlSession3.commit();
    System.out.println("studentMapper2: " + studentMapper2.getStudentById(1));
  }

  /**
   * MyBatis的二级缓存不适应用于映射文件中存在多表查询的情况
   */
  @Test
  public void testSecondaryCachewithDiffNameSpace() {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession3 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper1 = sqlSession1.getMapper(studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(studentMapper.class);
    classMapper classMapper = sqlSession3.getMapper(classMapper.class);

    System.out.println("studentMapper1: " + studentMapper1.getStudentByIdWithClassInfo(1));
    sqlSession1.close();

    System.out.println("studentMapper2: " + studentMapper2.getStudentByIdWithClassInfo(1));

    classMapper.updateClassName("一班lalala", 1);
    sqlSession3.commit();

    System.out.println("studentMapper2: " + studentMapper2.getStudentByIdWithClassInfo(1));
  }

  @Test
  public void testSecondaryCachewithDiffNameSpacewithCache() throws Exception {
    SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
    SqlSession sqlSession3 = sqlSessionFactory.openSession(true);

    studentMapper studentMapper1 = sqlSession1.getMapper(studentMapper.class);
    studentMapper studentMapper2 = sqlSession2.getMapper(studentMapper.class);
    classMapper classMapper = sqlSession3.getMapper(classMapper.class);

    System.out.println("studentMapper1: " + studentMapper1.getStudentByIdWithClassInfo(1));
    sqlSession1.close();

    System.out.println("studentMapper2: " + studentMapper2.getStudentByIdWithClassInfo(1));

    classMapper.updateClassName("一班lalala", 1);
    sqlSession3.commit();

    System.out.println("studentMapper2: " + studentMapper2.getStudentByIdWithClassInfo(1));
  }


}