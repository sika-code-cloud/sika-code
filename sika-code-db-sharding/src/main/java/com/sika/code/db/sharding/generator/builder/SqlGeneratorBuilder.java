package com.sika.code.db.sharding.generator.builder;

import cn.hutool.core.util.StrUtil;
import com.sika.code.db.sharding.generator.dto.SqlGeneratorDTO;

import java.util.Locale;

/**
 * <p>
 *  sql脚本生构建器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/7/10 19:41
 */
public class SqlGeneratorBuilder {
    private final static String CREATE_DATABASE = "CREATE DATABASE";
    private final static String ALTER_DATABASE = "ALTER DATABASE";
    private final static String CREATE_TABLE = "CREATE TABLE";
    private final static String ALTER_TABLE = "ALTER TABLE";
    public void build(SqlGeneratorDTO dto) {
        String toUpperCase = dto.getSourceLine().toUpperCase(Locale.ROOT);
        String resultLine = dto.getSourceLine();
        if (StrUtil.startWith(toUpperCase, CREATE_DATABASE)) {

        } else if (StrUtil.startWith(toUpperCase, ALTER_DATABASE)) {

        } else if (StrUtil.startWith(toUpperCase, CREATE_TABLE)) {

        } else if (StrUtil.startWith(toUpperCase, ALTER_TABLE)) {

        }
        dto.setResultLine(resultLine);
    }

    protected String buildResultLine(SqlGeneratorDTO dto, String toUpperCase) {
        Integer shardingType = dto.getShardingType();
        String resultLine = dto.getSourceLine();
        if (shardingType.equals(1)) {
            // 年库天表
        } else if (shardingType.equals(2)) {
            // 年库月表
        } else if (shardingType.equals(3)) {
            // 年表
        } else if (shardingType.equals(4)) {
            // 数据递增表-如百库十表-十库百表
        }
        return resultLine;
    }
}
