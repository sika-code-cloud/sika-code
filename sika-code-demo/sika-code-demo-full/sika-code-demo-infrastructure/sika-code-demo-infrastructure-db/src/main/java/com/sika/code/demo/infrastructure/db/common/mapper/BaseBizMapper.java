package com.sika.code.demo.infrastructure.db.common.mapper;

import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.db.mapper.BaseMapper;

/**
 * <pre>
 *  基础用户Mapper
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/11 11:25
 */
public interface BaseBizMapper<PO extends BasePO<Long>> extends BaseMapper<PO, Long> {
}
