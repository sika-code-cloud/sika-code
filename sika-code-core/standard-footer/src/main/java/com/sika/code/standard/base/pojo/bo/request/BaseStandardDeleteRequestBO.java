package com.sika.code.standard.base.pojo.bo.request;

import com.sika.code.basic.pojo.response.BaseResponseBO;
import lombok.Data;

/**
 * 删除的request
 *
 * @author daiqi
 * @create 2018-12-07 10:33
 */
@Data
public abstract class BaseStandardDeleteRequestBO<ResponseBO extends BaseResponseBO> extends BaseStandardRequestBO<ResponseBO> {

}
