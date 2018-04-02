/*
Navicat MySQL Data Transfer

Source Server         : easy云服务测试
Source Server Version : 50634
Source Host           : rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com:3306
Source Database       : dq_easy_cloud_movie

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-02-02 14:55:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easy_movie_info
-- ----------------------------
DROP TABLE IF EXISTS `easy_movie_info`;
CREATE TABLE `easy_movie_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID,自增主键',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的openid',
	`update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的openid',
  `create_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本管理标志',
  `is_deleted` char(1) NOT NULL DEFAULT "0" COMMENT '逻辑删除标志(1:删除)',
  `remark` varchar(63) DEFAULT NULL COMMENT '备注',

  `movie_name` varchar(31) NOT NULL COMMENT '电影名称',
  `movie_desc` varchar(255)  NOT NULL COMMENT '电影描述',
  `play_duration` int(11) NOT NULL COMMENT '播放时长 精确到秒',
  PRIMARY KEY (`id`),
  INDEX `index_movie_name` (`movie_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '电影表';

-- ----------------------------
-- Table structure for easy_user_attention_movie
-- ----------------------------
DROP TABLE IF EXISTS `easy_user_attention_movie`;
CREATE TABLE `easy_user_attention_movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID,自增主键',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的openid',
	`update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的openid',
  `create_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本管理标志',
  `is_deleted` char(1) NOT NULL DEFAULT "0" COMMENT '逻辑删除标志(1:删除)',
  `remark` varchar(63) DEFAULT NULL COMMENT '备注',

  `movie_id` bigint(20) NOT NULL COMMENT '电影id 关联easy_movie_info表的id',
  `user_id` bigint(20)  NOT NULL COMMENT '用户id 用户服务easy_user_id表的id',
  PRIMARY KEY (`id`),
  INDEX `index_movie_id` (`movie_id`) USING BTREE,
	INDEX `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户关注电影关系表';














