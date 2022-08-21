package com.sika.code.migrate.pojo;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * <pre>
 *  迁移规则响应结果
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 8:43
 */
@Data
@Accessors(chain = true)
public class MigrateRuleResponse {
    /**
     * 规则匹配结果 - true代表匹配-其他不匹配
     */
    private Boolean match;
    /**
     * 需要重定向的url
     */
    private String redirectUrl;
    /**
     * 其他额外参数 - 如果该参数不为空-将会将其添加到重定向的请求头中进行重定向
     */
    private Map<String, String> extraParam;
    /**
     * 迁移类型
     */
    private String migrateType;
    /** 当前请求的ID */
    private String requestId;

    public void putExtraParam(String key, String value) {
        if (StrUtil.isBlank(key)) {
            return;
        }
        if (value == null) {
            return;
        }
        if (extraParam == null) {
            extraParam = Maps.newLinkedHashMap();
        }
        extraParam.put(key, value);
    }

}
