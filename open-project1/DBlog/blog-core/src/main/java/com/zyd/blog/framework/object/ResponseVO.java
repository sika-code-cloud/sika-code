package com.zyd.blog.framework.object;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zyd.blog.business.enums.ResponseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;

/**
 * controller返回json
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseVO<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(ResponseStatus status, T data) {
        this(status.getCode(), status.getMessage(), data);
    }

    public String toJson() {
        T t = this.getData();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }

    public Integer getCode() {
        if (ResponseStatus.SUCCESS.getCode().equals(status)) {
            return ResponseStatus.SUCCESS_CODE.getCode();
        }
        return this.status;
    }

    public boolean isSuccess() {
        return ResponseStatus.SUCCESS.getCode().equals(status);
    }

    public static <T> ResponseVO<T> newSuccess() {
        return newSuccess(null);
    }

    public static <T> ResponseVO<T> newSuccess(T data) {
        ResponseStatus successEnum = ResponseStatus.SUCCESS;
        return new ResponseVO()
                .setData(data)
                .setStatus(successEnum.getCode())
                .setMessage(successEnum.getMessage())
                ;
    }
}
