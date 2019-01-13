package com.natsu.springbootshirodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.natsu.springbootshirodemo.entity"})
@ComponentScan(basePackages = {"com.natsu.springbootshirodemo.entity"})
public class SpringbootShirodemoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootShirodemoApplication.class, args);
    }

}

