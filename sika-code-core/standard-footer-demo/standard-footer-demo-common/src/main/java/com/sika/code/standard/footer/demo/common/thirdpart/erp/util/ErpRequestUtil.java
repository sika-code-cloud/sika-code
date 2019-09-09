package com.sika.code.standard.footer.demo.common.thirdpart.erp.util;

import com.sika.code.common.thirdpart.constant.BaseThirdPartyInterface;
import com.sika.code.common.thirdpart.request.ThirdPartyRequestDTO;
import com.sika.code.common.thirdpart.util.ThirdPartyRequestUtil;
import com.sika.code.standard.footer.demo.common.thirdpart.erp.dto.request.ErpRequest;
import com.sika.code.standard.footer.demo.common.thirdpart.erp.dto.response.ErpResponse;
import com.sika.code.standard.footer.demo.common.thirdpart.erp.properties.ErpProperties;
import com.sika.code.standard.footer.demo.common.thirdpart.properties.ThirdPartyDomainProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <p>
 * erp请求工具类
 * </p>
 *
 * @author daiqi
 * @date 2019/4/4 17:55
 */
@Component
public class ErpRequestUtil extends ThirdPartyRequestUtil {
    private static ErpProperties erpProperties;
    private static ThirdPartyDomainProperties thirdPartyDomainProperties;

    @Autowired
    public void setThirdPartyDomainProperties(ThirdPartyDomainProperties thirdPartyDomainProperties) {
        ErpRequestUtil.thirdPartyDomainProperties = thirdPartyDomainProperties;
    }

    @Autowired
    public void setErpProperties(ErpProperties erpProperties) {
        ErpRequestUtil.erpProperties = erpProperties;
    }

    /**
     * 请求凌雄租赁Erp(通用模板)
     *
     * @param request             : 请求数据对象
     * @param thirdPartyInterface : 第三方接口对象
     * @param responseClass       : 响应的class
     * @return
     */
    private static <T> T postErp(ErpRequest request, BaseThirdPartyInterface thirdPartyInterface, Class<T> responseClass) {
        ThirdPartyRequestDTO thirdPartyRequestDTO = new ThirdPartyRequestDTO();
        thirdPartyRequestDTO.setRequestMethod(RequestMethod.POST);
        thirdPartyRequestDTO.setRequestDomain(thirdPartyDomainProperties.getErpRequestDomain());
        thirdPartyRequestDTO.setThirdPartyInterface(thirdPartyInterface);
        request.setErpAppId(erpProperties.getAppId());
        request.setErpAppSecret(erpProperties.getAppSecret());
        thirdPartyRequestDTO.setData(request);
        return requestForJson(thirdPartyRequestDTO, responseClass);
    }

    /**
     * 请求凌雄租赁Erp(标准模板，返回ErpResponse)
     * result不是成功状态自动抛出异常
     *
     * @param request             : 请求数据对象
     * @param thirdPartyInterface : 第三方接口对象
     * @return
     */
    public static ErpResponse postErp(ErpRequest request, BaseThirdPartyInterface thirdPartyInterface) {
        return postErp(request, thirdPartyInterface, ErpResponse.class);
    }


}

