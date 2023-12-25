package com.sika.code.demo.application.business.testtemp.service.impl;

import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.domain.business.testtemp.repository.TestTempRepository;
import com.sika.code.demo.application.business.testtemp.service.TestTempService;
import com.sika.code.demo.domain.common.base.service.impl.BaseBizServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试tem表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:45
 */
@Service
public class TestTempServiceImpl extends BaseBizServiceImpl<TestTempPO, TestTempDTO, TestTempRepository> implements TestTempService {

}
