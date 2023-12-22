DROP TABLE IF EXISTS test_db_dbidx.sika_year_day_tableidx;
CREATE TABLE test_db_dbidx.sika_year_day_tableidx
(
    id            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    create_time   datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_time   datetime                                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    remark        varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    year_day_date date                                             NOT NULL COMMENT '测试分片key',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='year_day';