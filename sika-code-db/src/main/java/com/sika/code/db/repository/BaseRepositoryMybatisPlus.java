package com.sika.code.db.repository;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.core.base.repository.BaseRepository;
import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.core.base.pojo.query.BaseQuery;
import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.db.mapper.BaseMapper;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * mybatis基础持久化接口
 *
 * @author daiqi
 * @create 2021-10-15 22:39
 */
public interface BaseRepositoryMybatisPlus<PO extends BasePO<PRIMARY>, PRIMARY extends Serializable, Mapper extends BaseMapper<PO, PRIMARY>> extends BaseRepository<PO, PRIMARY> {


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

    int updateBatchById(List<PO> pos);

    int updateBatchById(List<PO> poList, int batchSize);

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
