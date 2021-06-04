package com.sika.code.informer.dto;

import java.util.Map;

/**
 * @author daiqi
 * @create 2020-05-15 15:10
 */
public interface BaseThirdMessageDTO {
    default Map<String, Object> getSendMsg(){
        return null;
    }

}
