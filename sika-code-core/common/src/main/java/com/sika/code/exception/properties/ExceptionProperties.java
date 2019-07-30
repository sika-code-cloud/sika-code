package com.sika.code.exception.properties;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.basic.constant.PropertiesConstant;
import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daiqi
 * @create 2019-03-22 17:30
 */
@Data
@ConfigurationProperties(prefix = PropertiesConstant.EXCEPTION)
public class ExceptionProperties {
    /**
     * 是否是排除模式 若exclude为true则为排除模式 needExcludeInformCodes生效否则为包含模式needInformCodes生效
     * 默认为包含模式 即不设置exclude将采取包含模式
     */
    private Boolean exclude;

    /**
     * 需要通知的错误码
     */
    private Map<String, BaseErrorCode> needInformCodesMap = new LinkedHashMap<>();
    /**
     * 需要排除通知的错误码
     */
    private Map<String, BaseErrorCode> needExcludeInformCodesMap = new LinkedHashMap<>();

    public ExceptionProperties buildNeedInformCodes(List<BaseErrorCode> needInformErrorCodes) {
        if (CollUtil.isEmpty(needInformErrorCodes)) {
            return this;
        }
        buildInformCodesMap(needInformCodesMap, needInformErrorCodes);
        return this;
    }

    public ExceptionProperties buildNeedExcludeInformCodes(List<BaseErrorCode> needExcludeInformErrorCodes) {
        if (CollUtil.isEmpty(needExcludeInformErrorCodes)) {
            return this;
        }
        buildInformCodesMap(this.needExcludeInformCodesMap, needExcludeInformErrorCodes);
        return this;
    }

    private void buildInformCodesMap(Map<String, BaseErrorCode> informCodesMap, List<BaseErrorCode> informCodes) {
        if (CollUtil.isEmpty(informCodes)) {
            return;
        }
        for (BaseErrorCode needInformCode : informCodes) {
            informCodesMap.put(needInformCode.getCode(), needInformCode);
        }
    }
}
