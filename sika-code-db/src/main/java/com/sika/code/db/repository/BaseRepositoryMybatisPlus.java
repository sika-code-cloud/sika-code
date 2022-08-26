package com.sika.code.db.repository;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.sika.code.core.base.repository.BaseRepository;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.db.mapper.BaseMapper;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mybatis基础持久化接口
 *
 * @author daiqi
 * @create 2021-10-15 22:39
 */
public interface BaseRepositoryMybatisPlus<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable, Mapper extends BaseMapper<PO, PRIMARY>> extends BaseRepository<PO, PRIMARY> {
    String ID_KEY = "id";

    Mapper getMapper();

    @Override
    default PO findByPrimaryKey(PRIMARY primaryKey) {
        return getMapper().selectById(primaryKey);
    }

    @Override
    default PRIMARY saveRetId(PO po) {
        if (po == null) {
            throw new InvalidParameterException("持久化对象PO不能为空");
        }
        if (po.getId() == null) {
            getMapper().insert(po);
            return po.getId();
        } else {
            int count = getMapper().updateById(po);
            if (count > 0) {
                return po.getId();
            }
            throw new RuntimeException("数据更新失败");
        }
    }

    @Override
    default int save(PO po) {
        if (po == null) {
            throw new InvalidParameterException("持久化对象PO不能为空");
        }
        if (po.getId() == null) {
            return getMapper().insert(po);
        } else {
            return getMapper().updateById(po);
        }
    }

    @Override
    default int saveBatch(List<PO> pos) {
        int count = 0;
        if (CollUtil.isEmpty(pos)) {
            return count;
        }
        List<PO> waitForInsert = Lists.newArrayList();
        List<PO> waitForUpdate = Lists.newArrayList();
        for (PO po : pos) {
            if (po.getId() == null) {
                waitForInsert.add(po);
            } else {
                waitForUpdate.add(po);
            }
        }
        if (CollUtil.isNotEmpty(waitForInsert)) {
            count += insertBatch(waitForInsert);
        }
        if (CollUtil.isNotEmpty(waitForUpdate)) {
            count += updateBatchById(waitForUpdate);
        }
        return count;
    }

    int insertBatch(List<PO> pos);


    @Override
    default int insert(PO po) {
        return getMapper().insert(po);
    }

    @Override
    default PRIMARY insertRetId(PO po) {
        int count = insert(po);
        if (count > 0) {
            return po.getId();
        }
        throw new RuntimeException("数据插入失败");
    }

    @Override
    default int updateById(PO po) {
        return getMapper().updateById(po);
    }


    /**
     * <p>
     * 根据条件批量删除数据
     * </p>
     *
     * @return int
     * @author sikadai
     * @since 2022/8/25 22:54
     */
    default int logicDelete(Wrapper wrapper) {
        return getMapper().logicDelete(wrapper);
    }

    default int updateBatch(List<PO> updatePos, UpdateWrapper wrapper) {
        List<PRIMARY> primaries = updatePos.stream().map(PO::getId).collect(Collectors.toList());
        if (CollUtil.isEmpty(primaries)) {
            return 0;
        }
        if (wrapper == null) {
            wrapper = new UpdateWrapper<>();
        }
        wrapper.in(ID_KEY, primaries);
        for (PO po : updatePos) {
            if (po.getUpdateDate() == null) {
                po.setUpdateDate(new Date());
            }
        }
        return getMapper().updateBatchCaseWhen(updatePos, wrapper);
    }

    @Override
    default <QUERY extends BaseQuery<PRIMARY>> PO find(QUERY query) {
        return getMapper().find(query);
    }

    @Override
    default <QUERY extends BaseQuery<PRIMARY>> PRIMARY findId(QUERY query) {
        return getMapper().findId(query);
    }

    @Override
    default <QUERY extends BaseQuery<PRIMARY>> List<PO> list(QUERY query) {
        return getMapper().list(query);
    }

    @Override
    default <QUERY extends BaseQuery<PRIMARY>> List<PRIMARY> listId(QUERY query) {
        return getMapper().listId(query);
    }

    @Override
    default <QUERY extends PageQuery<PRIMARY>> List<PO> page(QUERY query) {
        return getMapper().page(query);
    }

    @Override
    default <Query extends BaseQuery<PRIMARY>> int count(Query query) {
        return getMapper().count(query);
    }
}
