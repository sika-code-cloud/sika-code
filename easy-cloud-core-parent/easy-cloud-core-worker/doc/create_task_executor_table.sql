
drop table if exists task_executor;
CREATE TABLE `task_executor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`create_by` bigint(20) DEFAULT NULL COMMENT '创建人的id',
`update_by` bigint(20) DEFAULT NULL COMMENT '最后更新人标识，记录用户的id',
`create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
`update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
`version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
`available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志',
`is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志(1:删除)',
`job_type` int(11) NOT NULL DEFAULT '1' COMMENT '定时任务job类型1:rest、2:mq、3:redis',
`job_group` varchar(31) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '任务工作分组',
`job_name` varchar(31) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '任务工作名称',
`trigger_group` varchar(31) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '任务触发器分组',
`trigger_name` varchar(31) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '任务触发器名称',
`retry_count` int(11) NOT NULL DEFAULT '3' COMMENT '重试次数 默认3次',
`retry_interval` int(11) NOT NULL DEFAULT '8' COMMENT '重试间隔 默认8秒',
`request_url` varchar(127) COLLATE utf8_bin NOT NULL COMMENT '请求的url',
`execute_result` varchar(63) COLLATE utf8_bin DEFAULT NULL COMMENT '执行的结果',
`task_executor_no` char(32) COLLATE utf8_bin NOT NULL COMMENT '任务执行者编号 唯一',
`request_method` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT 'post' COMMENT '请求的方法统一使用post',
`request_body` text COLLATE utf8_bin COMMENT '请求的主体内容[json对象字符串]',
`job_callback_class_name` varchar(127) COLLATE utf8_bin DEFAULT NULL COMMENT '工作任务异步回调的完整类名',
`asyn` int(11) NOT NULL DEFAULT '1' COMMENT '是否为异步执行 0:同步 1:异步 默认使用异步方式执行',
`system_type` int(11) NOT NULL DEFAULT '1' COMMENT '系统类型',
`remark` varchar(63) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`),
UNIQUE KEY `unique_task_executor_no` (`task_executor_no`),
UNIQUE KEY `index_job_group-job_name-trigger_group-trigger_name-request_url` (`job_group`,`job_name`,`trigger_group`,`trigger_name`,`request_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务执行者表';



