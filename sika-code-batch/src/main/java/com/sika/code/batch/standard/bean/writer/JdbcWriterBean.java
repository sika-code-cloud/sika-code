package com.sika.code.batch.standard.bean.writer;

import lombok.Data;

/**
 * <p>
 *  JDBC写Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:38
 */
@Data
public class JdbcWriterBean extends BaseWriterBean{
    private String tableName;

}
