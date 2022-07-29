package com.sika.code.db.sharding.generator.dto;

import lombok.Data;

/**
 * <p>
 *  脚本生成器数据传输对象
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/7/10 19:43
 */
@Data
public class SqlGeneratorDTO {
    private String dbName;
    private String tableName;
    private String dbNum;
    private String tableNum;
    private Integer shardingType;

    private String sourceLine;
    private String resultLine;
    private String currentDbIndex;
    private String currentTableIndex;
}
