use test_db_{};
CREATE TABLE `lf_test_{}`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version`     int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available`   int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted`  int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `test_name`   varchar(64) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '流程名称',
    `record_date` date                                             NOT NULL COMMENT '记录日期',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试表';
