package com.sika.code.standard.base.pojo.entity;

import com.sika.code.database.common.entity.BaseEntity;
import lombok.Data;

/**
 * 标准框架基础实体类---所有标准框架持久化实体的父类
 *
 * @author daiqi
 * @create 2018-08-03 14:59
 */
@Data
public abstract class BaseStandardEntity extends BaseEntity<Long, String> {

}
