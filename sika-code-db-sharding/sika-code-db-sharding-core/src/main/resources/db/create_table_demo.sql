CREATE TABLE temp_db_{}.temp_table_{} (
   `id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'ID-雪花算法生成-不参与任务业务关联',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表';