package com.sika.code.batch.standard.bean.writer;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Set;

/**
 * <p>
 * 方法写入的Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 18:28
 */
@Data
public class RestWriterBean extends BaseWriterBean {
    private String domain;
    private String path;
    private String requestType;
    private String codeName;
    private Set<String> successCodes;
    private String msgName;
    private String fullUrl;


    public String buildFullUrl() {
        if (this.fullUrl == null) {
            this.fullUrl = StrUtil.join(StrPool.SLASH, domain, path);
        }
        return this.fullUrl;
    }
}
