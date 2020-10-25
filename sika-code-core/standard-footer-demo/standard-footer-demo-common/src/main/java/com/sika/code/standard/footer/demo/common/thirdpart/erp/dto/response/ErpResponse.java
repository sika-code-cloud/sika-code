package com.sika.code.standard.footer.demo.common.thirdpart.erp.dto.response;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.common.json.util.JSONUtil;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * ERP响应的类
 * </p>
 *
 * @author daiqi
 * @date 2019/4/4 17:46
 */
@Data
public class ErpResponse {
    private String code;
    private String description;
    private String message;
    private Boolean success;
    private ErpResponseData resultMap;

    public static ErpResponse buildErrorResponse(BaseErrorCode errorCode) {
        ErpResponse erpResponseDTO = new ErpResponse();
        erpResponseDTO.setSuccess(false);
        erpResponseDTO.setCode(errorCode.getCode());
        erpResponseDTO.setDescription(errorCode.getMessage());
        return erpResponseDTO;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public <T> T getTObj(Class<T> clazz) {
        if (resultMap != null && resultMap.getData() != null) {
            return JSONUtil.parseObject(resultMap.getData(), clazz);
        }
        return null;
    }

    public <T> List<T> getTList(Class<T> clazz) {
        if (resultMap != null && resultMap.getData() != null) {
            return JSONUtil.parseArray(resultMap.getData(), clazz);
        }
        return null;
    }


}
