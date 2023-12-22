package com.sika.code.demo.domain.business.node.repository.impl;

import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import com.sika.code.demo.infrastructure.db.business.node.mapper.NodeMapper;
import com.sika.code.demo.domain.business.node.repository.NodeRepository;
import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * 节点表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:20
 */
@Repository
public class NodeRepositoryImpl extends BaseRepositoryMyBatisPlusImpl<NodePO, Long, NodeMapper> implements NodeRepository {

    @Override
    public void verifyNodeUnExistById(Long id) {
        Assert.notNull(id, "节点表主键ID不能为空");
        NodePO po = findByPrimaryKey(id);
        Assert.notNull(po, "主键【{}】对应的节点表数据不存在，请核实", id);
    }
}

