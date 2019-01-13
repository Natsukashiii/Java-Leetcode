package com.natsu.springbootshirodemo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户实体类
 *
 * @author natsukashii
 */
@Table(name = "auth_user")
@Entity
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   //自增长主键
    private String account;    //登录账号
    private String userName;    //注册昵称
    @Transient
    private String plainPassword;
    private String passWord;
    private String phone;
    private String email;
    private String platform;
    private String createdDate;
    private String updatedDate;
    private Integer status;

}
