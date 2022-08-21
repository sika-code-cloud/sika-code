package com.sika.code.migrate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <pre>
 *  迁移结果数据传输对象
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/21 11:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrateResultDTO {
    private Object result;
    private Map<String, String> header;
    private String requestId;
}
