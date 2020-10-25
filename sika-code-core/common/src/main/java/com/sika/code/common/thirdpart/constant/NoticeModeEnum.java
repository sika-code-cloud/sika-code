package com.sika.code.common.thirdpart.constant;

/**
 * 通知方式枚举类
 */
public enum NoticeModeEnum {
    /** 通知方式 */
    REST(1, "使用rest接口方式接口进行通知"),
    MQ(2, "使用消息队列方式接口进行通知"),
    ;

    NoticeModeEnum(Integer mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    private Integer mode;
    private String desc;

    public Integer getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isRest(NoticeModeEnum modeEnum) {
        if (REST.equals(modeEnum)) {
            return true;
        }
        return false;
    }

    public static boolean isMq(NoticeModeEnum modeEnum) {
        if (MQ.equals(modeEnum)) {
            return true;
        }
        return false;
    }
}