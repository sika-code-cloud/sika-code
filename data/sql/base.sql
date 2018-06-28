drop table if exists table_name;
CREATE TABLE `table_name` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
`create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
`update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
`create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
`update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
`version` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本管理标志',
`available` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '可用标志[1:可用 0:不可用]',
`is_deleted` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除 0:未删)',
`remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`),
UNIQUE KEY `unique_column` (`column`),
KEY `index_column` (`column`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表注释';
