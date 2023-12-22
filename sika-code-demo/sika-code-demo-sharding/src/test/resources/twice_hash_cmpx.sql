DROP TABLE IF EXISTS test_db_dbidx.sika_twice_hash_cmpx_tableidx;
CREATE TABLE test_db_dbidx.sika_twice_hash_cmpx_tableidx
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    create_time datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_time datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    remark      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    twice_hash_cmpx_no  varchar(64) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '测试分片key',
    twice_hash_cmpx_r_no  varchar(64) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '测试分片key',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='twice_hash_cmpx';