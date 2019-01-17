package com.natsu.springbootshirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.natsu.springbootshirodemo.mapper")
public class SpringbootShirodemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootShirodemoApplication.class, args);
  }

}

