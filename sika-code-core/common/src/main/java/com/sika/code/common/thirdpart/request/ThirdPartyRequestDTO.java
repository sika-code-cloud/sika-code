package com.sika.code.common.thirdpart.request;

import cn.hutool.core.util.StrUtil;
import com.sika.code.common.thirdpart.constant.BaseThirdPartyInterface;
import com.sika.code.common.thirdpart.constant.NoticeModeEnum;
import com.sika.code.common.thirdpart.util.ThirdPartyRequestUtil;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 第三方通知数据传输对象
 *
 * @author daiqi
 * @create 2018-08-15 17:40
 */
@Data
public class ThirdPartyRequestDTO {
    /**
     * 通知方式
     */
    private NoticeModeEnum noticeMode;
    /**
     * 请求的domain（完整域名/ip+端口+项目名称）、不包括接口
     */
    private String requestDomain;
    /**
     * 基础接口
     */
    private BaseThirdPartyInterface thirdPartyInterface;
    /**
     * 请求方法POST、GET等等
     */
    private RequestMethod requestMethod;

    /**
     * 需要传输的数据
     */
    private Object data;

    private String requestUrlForFull;
    private HttpHeaders httpHeaders;

    public ThirdPartyRequestDTO() {
        this.noticeMode = NoticeModeEnum.REST;
    }

    public ThirdPartyRequestDTO addHeader(String headName, String headValue) {
        if (this.httpHeaders == null) {
            this.httpHeaders = new HttpHeaders();
        }
        this.httpHeaders.add(headName, headValue);
        return this;
    }

    public ThirdPartyRequestDTO(String requestDomain, BaseThirdPartyInterface thirdPartyInterface, Object data) {
        this.requestDomain = requestDomain;
        this.thirdPartyInterface = thirdPartyInterface;
        this.data = data;
    }

    /**
     * <p>
     * 获取完整的请求url
     * </p>
     *
     * @param
     * @return java.lang.String
     * @author daiqi
     * @date 2018/8/15 18:13
     */
    public String getRequestUrlForFull() {
        if (StrUtil.isBlank(this.requestUrlForFull)) {
            this.requestUrlForFull = ThirdPartyRequestUtil.buildFullRequestUrl(this.requestDomain, thirdPartyInterface.getUri());
        }
        return this.requestUrlForFull;
    }
}
