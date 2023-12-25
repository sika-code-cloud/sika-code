package com.sika.code.demo.domain.common.base.repository.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;
import com.sika.code.demo.infrastructure.db.common.mapper.BaseBizMapper;
import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;

import java.util.List;

/**
 * 基础示例仓储实现
 */
public class BaseBizRepositoryImpl<PO extends BaseBizPO, Mapper extends BaseBizMapper<PO>> extends BaseRepositoryMyBatisPlusImpl<PO, Long, Mapper> implements BaseBizRepository<PO, Mapper> {

    @Override
    public int insertBatch(List<PO> pos) {
        buildDefaultIds(pos);
        return super.insertBatch(pos);
    }

    @Override
    public int insertBatch(List<PO> pos, int batchSize) {
        buildDefaultIds(pos);
        return super.insertBatch(pos, batchSize);
    }

    @Override
    public int insert(PO po) {
        buildDefaultId(po);
        return super.insert(po);
    }

    @Override
    public Long insertRetId(PO po) {
        buildDefaultId(po);
        return super.insertRetId(po);
    }

    protected void buildDefaultIds(List<PO> pos) {
        if (CollUtil.isEmpty(pos)) {
            return;
        }
        for (PO po : pos) {
            buildDefaultId(po);
        }
    }

    protected void buildDefaultId(PO po) {
        buildId(po);
    }


    protected void buildId(PO po) {
        if (po.getId() == null) {
            po.setId(IdUtil.getSnowflake().nextId());
        }
    }

}