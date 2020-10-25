package com.sika.code.standard.base.pojo.bo.request;

import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;

/**
 * 保存或者更新的request逻辑处理类
 *
 * @author daiqi
 * @create 2018-12-07 10:33
 */
public abstract class BaseStandardAlterRequestBO<ResponseBO extends BaseStandardResponseBO> extends BaseStandardRequestBO<ResponseBO> {

    /**
     * <p>
     * 保存或者更新的request
     * </p>
     *
     * @author daiqi
     * @date 2019/6/16 22:53
     */
    @Data
    public static class BaseStandardAlterRequest {
        /**
         * 备注
         */
        protected String remark;
    }
}
