package com.sika.code.demo.domain.business.node.repository;

import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import com.sika.code.core.base.repository.BaseRepository;

/**
 * <p>
 * 节点表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:19
 */
public interface NodeRepository extends BaseRepository<NodePO, Long> {
    /**
     * 校验ID对应的协作器是否不存在-不存在抛出异常
     * @param id : 主键ID
     */
     void verifyNodeUnExistById(Long id);
}
