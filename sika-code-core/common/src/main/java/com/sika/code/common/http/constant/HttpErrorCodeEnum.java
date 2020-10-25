package com.sika.code.common.http.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * http错误码
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 上午11:21:15
 */
@Getter
@AllArgsConstructor
public enum HttpErrorCodeEnum implements BaseErrorCode {
    /**
     * 内容格式有误---HTTP_CONTENT_FORMAT_WRONG---HTTP_000001
     */
    HTTP_CONTENT_FORMAT_WRONG("HTTP_000001", "内容格式有误");
    private String code;
    private String message;
}
