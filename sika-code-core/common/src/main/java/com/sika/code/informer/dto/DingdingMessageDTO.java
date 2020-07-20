package com.sika.code.informer.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2019-03-25 11:41
 */
@Data
public class DingdingMessageDTO implements BaseThirdMessageDTO{
    private static final String CONTENT_KEY = "content";
    private static final String MSG_TYPE_TEXT = "text";
    private String msgtype;
    private Map<String, Object> text;

    public DingdingMessageDTO() {
        this.msgtype = MSG_TYPE_TEXT;
        this.text = new HashMap<>();
    }

    public void setContent(String content) {
        text.put(CONTENT_KEY, content);
    }
}
