package com.sika.code.informer.constant;

import com.sika.code.basic.constant.CodeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiqi
 * @create 2020-05-15 17:13
 */
@AllArgsConstructor
@Getter
public enum MsgTypeEnum implements CodeEnumInf {
    TEXT("text", "文本消息"),
    MARKDOWN("markdown", "Markdown消息"),
    ;
    private String code;
    private String message;
}
