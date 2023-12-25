package com.sika.code.demo.domain.business.testtemp.repository.impl;

import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.infrastructure.db.business.testtemp.mapper.TestTempMapper;
import com.sika.code.demo.domain.business.testtemp.repository.TestTempRepository;
import com.sika.code.demo.domain.common.base.repository.impl.BaseBizRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 测试tem表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:41
 */
@Repository
public class TestTempRepositoryImpl extends BaseBizRepositoryImpl<TestTempPO, TestTempMapper> implements TestTempRepository {

}

