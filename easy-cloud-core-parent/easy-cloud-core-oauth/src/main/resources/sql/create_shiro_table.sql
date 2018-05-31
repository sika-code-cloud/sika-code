
drop table if exists sys_user;
create table sys_user (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',

	`phone` varchar(31) NOT NULL DEFAULT '' COMMENT '手机号',
	`username` varchar(63) NOT NULL DEFAULT '' COMMENT '用户名',
	`password` CHAR(32) NOT NULL DEFAULT 0 COMMENT "用户密码，经过MD5加密后的密码 32位",
	`salt` CHAR(32) NOT NULL COMMENT "用作加密的盐",
	`locked` INT(11) NOT NULL COMMENT "锁定标志 1 锁定 0 未锁",
  PRIMARY KEY (`id`),
	UNIQUE KEY `unique_phone` (`phone`),
	UNIQUE KEY `unique_username` (`username`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户表';


drop table if exists sys_user_role;
create table sys_user_role (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',

	`user_id` varchar(63) NOT NULL DEFAULT '' COMMENT '用户id',
	`role_no` CHAR(32) NOT NULL DEFAULT 0 COMMENT "角色编号",
  PRIMARY KEY (`id`),
	KEY `index_user_id` (`user_id`),
	KEY `index_role_no` (`role_no`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户角色关系表';


drop table if exists sys_organization;
create table sys_organization (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',
	
	`organization_no` int(11) DEFAULT NULL COMMENT '组织编号',
	`name` varchar(63) NOT NULL DEFAULT '' COMMENT '组织结构名称',
	`parent_no` int(11) NOT NULL DEFAULT 0 COMMENT "直接父编号",
	`parent_nos` VARCHAR(127) NOT NULL DEFAULT '' COMMENT "父编号列表 json数组 从左到右 辈分依次减小",
  PRIMARY KEY (`id`),
	KEY `index_organization_no` (`organization_no`),
	KEY `index_parent_no` (`parent_no`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织架构表';

drop table if exists sys_resource;
create table sys_resource (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',
	
	`resource_no` int(11) DEFAULT NULL COMMENT '资源编号',
	`name` varchar(63) NOT NULL DEFAULT '' COMMENT '资源名称',
  `type` VARCHAR(63) NOT NULL DEFAULT '' COMMENT "资源类型",
	`url` VARCHAR(63) NOT NULL DEFAULT '' COMMENT "资源url",
	`parent_no` int(11) NOT NULL DEFAULT 0 COMMENT "直接父编号",
	`parent_nos` VARCHAR(127) NOT NULL DEFAULT '' COMMENT "父编号列表 json数组 从左到右 辈分依次减小",
	`permission` VARCHAR(127) NOT NULL DEFAULT '' COMMENT "权限字符串",
  PRIMARY KEY (`id`),
	UNIQUE KEY `unique_resource_no` (`resource_no`),
	KEY `index_parent_no` (`parent_no`),
	KEY `index_resource_no` (`resource_no`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源表';

drop table if exists sys_role;
create table sys_role (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',

	`role_no` int(11) DEFAULT NULL COMMENT '角色编号',
  `description` VARCHAR(31) NOT NULL COMMENT "角色描述",
  PRIMARY KEY (`id`),
	UNIQUE KEY `unique_role_no` (`role_no`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

drop table if exists sys_role_resource;
create table sys_role_resource (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
  `update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL default 1 COMMENT "可用标志",
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',

  `role_no` BIGINT(20) NOT NULL COMMENT "角色编号 关联角色表的角色编号",
	`resource_no` BIGINT(20) NOT NULL COMMENT "资源编号 关联资源表的资源编号",
  PRIMARY KEY (`id`),
  KEY `index_role_no` (`role_no`),
	KEY `index_resource_no` (`resource_no`)
) charset=utf8 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色资源关系表';


