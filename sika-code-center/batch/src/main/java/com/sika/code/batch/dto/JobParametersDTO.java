package com.sika.code.batch.dto;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.pojo.dto.BaseMsgDTO;

/**
 * @author daiqi
 * @create 2019-09-29 23:17
 */
public class JobParametersDTO extends BaseMsgDTO {
    public JobParametersDTO() {
    }

    public JobParametersDTO(Object data) {
        super(data);
    }

    public JobParametersDTO(Object data, BaseErrorCode errorCode) {
        super(data, errorCode);
    }

}
