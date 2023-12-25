package com.sika.code.demo.application.business.chain.service.impl;

import com.sika.code.demo.infrastructure.business.chain.pojo.dto.ChainDTO;
import com.sika.code.demo.infrastructure.db.business.chain.po.ChainPO;
import com.sika.code.demo.domain.business.chain.repository.ChainRepository;
import com.sika.code.demo.application.business.chain.service.ChainService;
import com.sika.code.demo.domain.common.base.service.impl.BaseBizServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:19
 */
@Service
public class ChainServiceImpl extends BaseBizServiceImpl<ChainPO, ChainDTO, ChainRepository> implements ChainService {

}
