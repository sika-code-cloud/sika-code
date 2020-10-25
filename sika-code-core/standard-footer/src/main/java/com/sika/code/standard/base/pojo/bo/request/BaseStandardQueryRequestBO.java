package com.sika.code.standard.base.pojo.bo.request;

import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;

/**
 * 基础查询request
 *
 * @author daiqi
 * @create 2018-12-07 10:35
 */
@Data
public abstract class BaseStandardQueryRequestBO<ResponseBO extends BaseStandardResponseBO> extends BaseStandardRequestBO<ResponseBO> {

}
