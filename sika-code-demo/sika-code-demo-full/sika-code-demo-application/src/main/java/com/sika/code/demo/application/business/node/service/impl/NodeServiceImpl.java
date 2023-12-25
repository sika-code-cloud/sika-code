package com.sika.code.demo.application.business.node.service.impl;

import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;
import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import com.sika.code.demo.domain.business.node.repository.NodeRepository;
import com.sika.code.demo.application.business.node.service.NodeService;
import com.sika.code.core.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 节点表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:29
 */
@Service
public class NodeServiceImpl extends BaseServiceImpl<Long, NodePO, NodeDTO, NodeRepository> implements NodeService {

}
