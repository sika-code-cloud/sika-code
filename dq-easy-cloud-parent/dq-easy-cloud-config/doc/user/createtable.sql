/*
Navicat MySQL Data Transfer

Source Server         : easy云服务测试
Source Server Version : 50634
Source Host           : rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com:3306
Source Database       : dq_easy_cloud_user

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-02-02 14:55:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easy_user_info
-- ----------------------------
DROP TABLE IF EXISTS `easy_user_info`;
CREATE TABLE `easy_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID,自增主键',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的openid',
	`update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的openid',
  `create_date` datetime NOT NULL DEFAULT NOW() COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本管理标志',
  `is_deleted` char(1) NOT NULL DEFAULT "0" COMMENT '逻辑删除标志(1:删除)',
  `remark` varchar(63) DEFAULT NULL COMMENT '备注',

  `user_name` varchar(31) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(31) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户昵称',
  `name` varchar(15) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户名字',
  `email` varchar(31) DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(31) DEFAULT NULL COMMENT '电话',
  `wechat_number` varchar(31) DEFAULT NULL COMMENT '微信号',
  `user_type` varchar(31) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `open_id` varchar(127) DEFAULT NULL COMMENT 'openid',
  `password` char(32) DEFAULT NULL COMMENT '用户密码',
  `sex` tinyint(2) DEFAULT '1' COMMENT '1男 2 女  3未知',
  `is_frozen` int(11) DEFAULT '0' COMMENT '是否冻结 1冻结 0 未冻结 ',
  `is_manager` int(11) DEFAULT '0' COMMENT '是否管理员 1 是 0 否',
  `is_subscribe` int(11) DEFAULT '0' COMMENT '是否关注公众号 1 是 0 否',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone_number`) USING BTREE,
	UNIQUE KEY `email` (`email`) USING BTREE,
	UNIQUE KEY `user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=406871890473254913 DEFAULT CHARSET=utf8;
