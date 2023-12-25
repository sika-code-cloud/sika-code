package com.sika.code.demo.domain.common.base.service.impl;

import com.sika.code.core.base.repository.BaseRepository;
import com.sika.code.core.base.service.BaseServiceImpl;
import com.sika.code.demo.domain.common.base.service.BaseBizService;
import com.sika.code.demo.infrastructure.common.pojo.dto.BaseBizDTO;
import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;

/**
 * <p>
 * 基础服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022/8/18 23:51
 */
public class BaseBizServiceImpl<PO extends BaseBizPO, DTO extends BaseBizDTO, Repository extends BaseRepository<PO, Long>> extends BaseServiceImpl<Long, PO, DTO, Repository> implements BaseBizService<DTO> {
}