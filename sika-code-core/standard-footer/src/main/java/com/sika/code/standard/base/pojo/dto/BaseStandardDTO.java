package com.sika.code.standard.base.pojo.dto;

import com.sika.code.basic.pojo.dto.BaseDTO;
import lombok.Data;

/**
 * 标准框架基础数据传输类---所有数据传输类的父类
 *
 * @author daiqi
 * @create 2018-08-03 14:59
 */
@Data
public abstract class BaseStandardDTO extends BaseDTO<String> {
    private static final long serialVersionUID = 1L;
}
