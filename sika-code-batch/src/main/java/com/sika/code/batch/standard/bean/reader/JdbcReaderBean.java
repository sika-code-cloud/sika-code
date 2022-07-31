package com.sika.code.batch.standard.bean.reader;

import lombok.Data;

import java.util.LinkedHashMap;

/**
 * <p>
 * JDBC的读取器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:23
 */
@Data
public class JdbcReaderBean extends BaseReaderBean {
    // 查询的sql
    private String selectSql;
    // from的sql
    private String fromSql;
    // where条件sql
    private String whereSql;
    // 排序key和类型的映射
    private LinkedHashMap<String, String> sortKeyType;
    // 结果集的映射
    private LinkedHashMap<String, String> resultSetKey;
    // 每次分页读取的数量
    private Integer pageSize = 1000;
}
