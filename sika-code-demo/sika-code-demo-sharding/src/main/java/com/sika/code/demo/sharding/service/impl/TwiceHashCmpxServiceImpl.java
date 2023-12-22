package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sika.code.demo.sharding.mapper.TwiceHashCmpxMapper;
import com.sika.code.demo.sharding.service.TwiceHashCmpxService;
import com.sika.code.demo.sharding.pojo.po.TwiceHashCmpxPO;
import com.sika.code.demo.sharding.pojo.query.TwiceHashCmpxQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/10 11:39
 */
@Service
public class TwiceHashCmpxServiceImpl implements TwiceHashCmpxService {
    @Resource
    private TwiceHashCmpxMapper twiceHashCmpxMapper;

    @Override
    public TwiceHashCmpxPO find(TwiceHashCmpxQuery query) {
        LambdaQueryWrapper<TwiceHashCmpxPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TwiceHashCmpxPO::getId, query.getId());
        wrapper.eq(TwiceHashCmpxPO::getTwiceHashCmpxNo, query.getTwiceHashCmpxNo());
        if (query.getRemark() != null) {
            wrapper.like(TwiceHashCmpxPO::getRemark, query.getRemark());
        }
        return twiceHashCmpxMapper.selectOne(wrapper);
    }

    @Override
    public List<TwiceHashCmpxPO> list(TwiceHashCmpxQuery query) {
        LambdaQueryWrapper<TwiceHashCmpxPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TwiceHashCmpxPO::getTwiceHashCmpxNo, query.getTwiceHashCmpxNo());
        if (query.getRemark() != null) {
            wrapper.like(TwiceHashCmpxPO::getRemark, query.getRemark());
        }
        return twiceHashCmpxMapper.selectList(wrapper);

    }

    @Override
    public int insert(TwiceHashCmpxPO po) {
        return twiceHashCmpxMapper.insert(po);
    }

    @Override
    public int update(TwiceHashCmpxPO po) {
        LambdaUpdateWrapper<TwiceHashCmpxPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(TwiceHashCmpxPO::getTwiceHashCmpxNo, po.getTwiceHashCmpxNo());
        wrapper.eq(TwiceHashCmpxPO::getId, po.getId());
        return twiceHashCmpxMapper.update(po, wrapper);
    }
}
