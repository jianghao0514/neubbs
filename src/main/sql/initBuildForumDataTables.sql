/*
Navicat MySQL Data Transfer

Source Server         : neubbs
Source Server Version : 50711
Source Host           : 云服务器:3306
Source Database       : neubbs

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-12-07 17:26:30
*/


-- MySQL中取消外键约束
SET FOREIGN_KEY_CHECKS=0;


-- 删除原有表
DROP TABLE IF EXISTS `forum_user`;
DROP TABLE IF EXISTS `forum_topic`;
DROP TABLE IF EXISTS `forum_topic_content`;
DROP TABLE IF EXISTS `forum_topic_reply`;


-- 论坛用户
CREATE TABLE `forum_user` (
  `fu_id` INT(11) NOT NULL AUTO_INCREMENT comment '用户 id',
  `fu_name` VARCHAR(15) DEFAULT NULL comment '用户名',
  `fu_password` VARCHAR(200) NOT NULL comment '用户密码（储存 MD5 加密结果）',
  `fu_email` VARCHAR(255) NOT NULL comment '用户邮箱',
  `fu_sex` INT(1) DEFAULT NULL comment '用户性别(0-女，1-男)',
  `fu_birthday` VARCHAR(20) DEFAULT NULL comment '出生年月日',
  `fu_position` VARCHAR(255) DEFAULT NULL comment '所在位置',
  `fu_description` VARCHAR(255) DEFAULT NULL comment '个人描述',
  `fu_avator` VARCHAR(100) comment '用户头像图片名',
  `fu_rank` VARCHAR(10) DEFAULT 'user' comment '用户级别',
  `fu_state` INT(1) NOT NULL DEFAULT 0 comment '激活状态（0-未激活，1-已激活）',
  `fu_createtime` datetime DEFAULT CURRENT_TIMESTAMP comment '用户创建时间',
   PRIMARY KEY (`fu_id`),
   UNIQUE KEY `fu_name` (`fu_name`),
   UNIQUE KEY `fu_email` (`fu_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


-- 论坛话题
CREATE TABLE `forum_topic` (
  `ft_id` INT(11) NOT NULL AUTO_INCREMENT comment '话题 id',
  `fu_id` INT(11) NOT NULL comment '发表人 id （对应 forum_user）',
  `ftcg_id` INT(11) NOT NULL comment '话题分类 id （对应 forum_topic_category）',
  `ft_title` VARCHAR(50) NOT NULL comment '话题标题',
  `ft_replies` INT DEFAULT 0 comment '话题回复总数',
  `ft_lastreplyuserid` INT(11) DEFAULT NULL comment '最后回复人 id',
  `ft_lastreplytime` DATETIME DEFAULT NULL comment '最后回复时间',
  `ft_createtime` DATETIME DEFAULT CURRENT_TIMESTAMP comment '话题创建时间',
  PRIMARY KEY (`ft_id`),
  CONSTRAINT `FT_FU_ID` FOREIGN KEY (`fu_id`) REFERENCES `forum_user` (`fu_id`),
  CONSTRAINT `FT_FTCG_ID` FOREIGN KEY (`ftcg_id`) REFERENCES `forum_topic_category` (`ftcg_id`),
  CONSTRAINT `FT_L_FU_ID` FOREIGN KEY (`ft_lastreplyuserid`) REFERENCES `forum_user` (`fu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


-- 论坛话题内容
CREATE TABLE `forum_topic_content` (
  `ftc_id` INT(11) NOT NULL AUTO_INCREMENT comment '话题内容 id',
  `ft_id` INT(11) NOT NULL UNIQUE comment '对应话题 id （对应 forum_topic）',
  `ftc_content` LONGTEXT NOT NULL comment '话题内容（longtext 类型，最大长度4294967295个字元 (2^32-1)）',
  `ftc_read` INT DEFAULT 0 comment '话题阅读数',
  `ftc_like` INT DEFAULT 0 comment '喜欢话题人数',
  PRIMARY KEY (`ftc_id`),
  CONSTRAINT `FTC_FT_ID` FOREIGN KEY (`ft_id`) REFERENCES `forum_topic` (`ft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


-- 论坛话题回复
CREATE TABLE `forum_topic_reply` (
  `ftr_id` INT(11) NOT NULL AUTO_INCREMENT comment '话题回复id',
  `fu_id` INT(11) DEFAULT NULL comment '回复人 id',
  `ft_id` INT(11) DEFAULT NULL comment '对应话题 id',
  `ftr_content` VARCHAR(150) NOT NULL comment '话题内容',
  `ftr_agree` INT DEFAULT 0 comment '回复点赞数',
  `ftr_oppose` INT DEFAULT 0 comment '回复反对数',
  `ftr_createtime` DATETIME DEFAULT CURRENT_TIMESTAMP comment '回复创建时间',
  PRIMARY KEY (`ftr_id`),
  CONSTRAINT `FTR_FU_ID` FOREIGN KEY (`fu_id`) REFERENCES `forum_user` (`fu_id`),
  CONSTRAINT `FTR_FT_ID` FOREIGN KEY (`ft_id`) REFERENCES `forum_topic` (`ft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


-- 论坛话题分类
CREATE TABLE `forum_topic_category` (
  `ftcg_id` INT(11) NOT NULL AUTO_INCREMENT comment '话题分类id',
  `ftcg_nick` VARCHAR(100) DEFAULT NULL comment '分类昵称（全英文）',
  `ftcg_name` VARCHAR(100) DEFAULT NULL comment '分类名字',
  PRIMARY KEY (`ftcg_id`),
  UNIQUE KEY `ftcg_nick` (`ftcg_nick`),
  UNIQUE KEY `ftcg_name` (`ftcg_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;