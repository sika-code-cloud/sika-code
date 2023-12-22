package com.sika.code.demo.domain.business.testtemp.repository;

import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.infrastructure.db.business.testtemp.mapper.TestTempMapper;
import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;

/**
 * <p>
 * 测试tem表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:40
 */
public interface TestTempRepository extends BaseBizRepository<TestTempPO, TestTempMapper> {

}
