package com.sika.code.core.informer.constant;

import com.sika.code.core.base.constant.BaseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiqi
 * @create 2020-05-15 17:13
 */
@AllArgsConstructor
@Getter
public enum MsgTypeEnum implements BaseCodeEnum {
    TEXT("text", "文本消息"),
    MARKDOWN("markdown", "Markdown消息"),
    ;
    private String code;
    private String desc;
}
