DROP DATABASE IF EXISTS shiro;
CREATE DATABASE shiro DEFAULT CHARACTER SET utf8;
USE shiro;

drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;

create table user (
  id bigint auto_increment,
  name varchar(100),
  password varchar(100),
  salt varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table role (
  id bigint auto_increment,
  name varchar(100),
  desc_ varchar(100),
  constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table permission (
  id bigint auto_increment,
  name varchar(100),
  desc_ varchar(100),
  url varchar(100),
  constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table user_role (
  id bigint auto_increment,
  uid bigint,
  rid bigint,
  constraint pk_users_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table role_permission (
  id bigint auto_increment,
  rid bigint,
  pid bigint,
  constraint pk_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;

INSERT INTO `permission` VALUES (1,'addProduct','增加产品','/addProduct');
INSERT INTO `permission` VALUES (2,'deleteProduct','删除产品','/deleteProduct');
INSERT INTO `permission` VALUES (3,'editeProduct','编辑产品','/editeProduct');
INSERT INTO `permission` VALUES (4,'updateProduct','修改产品','/updateProduct');
INSERT INTO `permission` VALUES (5,'listProduct','查看产品','/listProduct');
INSERT INTO `permission` VALUES (6,'addOrder','增加订单','/addOrder');
INSERT INTO `permission` VALUES (7,'deleteOrder','删除订单','/deleteOrder');
INSERT INTO `permission` VALUES (8,'editeOrder','编辑订单','/editeOrder');
INSERT INTO `permission` VALUES (9,'updateOrder','修改订单','/updateOrder');
INSERT INTO `permission` VALUES (10,'listOrder','查看订单','/listOrder');
INSERT INTO `role` VALUES (1,'admin','超级管理员');
INSERT INTO `role` VALUES (2,'productManager','产品管理员');
INSERT INTO `role` VALUES (3,'orderManager','订单管理员');
INSERT INTO `role_permission` VALUES (1,1,1);
INSERT INTO `role_permission` VALUES (2,1,2);
INSERT INTO `role_permission` VALUES (3,1,3);
INSERT INTO `role_permission` VALUES (4,1,4);
INSERT INTO `role_permission` VALUES (5,1,5);
INSERT INTO `role_permission` VALUES (6,1,6);
INSERT INTO `role_permission` VALUES (7,1,7);
INSERT INTO `role_permission` VALUES (8,1,8);
INSERT INTO `role_permission` VALUES (9,1,9);
INSERT INTO `role_permission` VALUES (10,1,10);
INSERT INTO `role_permission` VALUES (11,2,1);
INSERT INTO `role_permission` VALUES (12,2,2);
INSERT INTO `role_permission` VALUES (13,2,3);
INSERT INTO `role_permission` VALUES (14,2,4);
INSERT INTO `role_permission` VALUES (15,2,5);
INSERT INTO `role_permission` VALUES (50,3,10);
INSERT INTO `role_permission` VALUES (51,3,9);
INSERT INTO `role_permission` VALUES (52,3,8);
INSERT INTO `role_permission` VALUES (53,3,7);
INSERT INTO `role_permission` VALUES (54,3,6);
INSERT INTO `role_permission` VALUES (55,3,1);
INSERT INTO `role_permission` VALUES (56,5,11);
INSERT INTO `user` VALUES (1,'zhang3','a7d59dfc5332749cb801f86a24f5f590','e5ykFiNwShfCXvBRPr3wXg==');
INSERT INTO `user` VALUES (2,'li4','43e28304197b9216e45ab1ce8dac831b','jPz19y7arvYIGhuUjsb6sQ==');
INSERT INTO `user_role` VALUES (43,2,2);
INSERT INTO `user_role` VALUES (45,1,1);
