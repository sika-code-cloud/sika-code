package com.sika.code.demo.domain.business.chain.repository;

import com.sika.code.demo.infrastructure.db.business.chain.mapper.ChainMapper;
import com.sika.code.demo.infrastructure.db.business.chain.po.ChainPO;
import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;

/**
 * <p>
 * 流程表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:14
 */
public interface ChainRepository extends BaseBizRepository<ChainPO, ChainMapper> {

}
