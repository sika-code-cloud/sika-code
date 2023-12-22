package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sika.code.demo.sharding.pojo.po.TwiceHashPO;
import com.sika.code.demo.sharding.mapper.TwiceHashMapper;
import com.sika.code.demo.sharding.pojo.query.TwiceHashQuery;
import com.sika.code.demo.sharding.service.TwiceHashService;
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
public class TwiceHashServiceImpl implements TwiceHashService {
    @Resource
    private TwiceHashMapper twiceHashMapper;

    @Override
    public TwiceHashPO find(TwiceHashQuery query) {
        LambdaQueryWrapper<TwiceHashPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TwiceHashPO::getId, query.getId());
        wrapper.eq(TwiceHashPO::getTwiceHashNo, query.getTwiceHashNo());
        if (query.getRemark() != null) {
            wrapper.like(TwiceHashPO :: getRemark, query.getRemark());
        }
        return twiceHashMapper.selectOne(wrapper);
    }

    @Override
    public List<TwiceHashPO> list(TwiceHashQuery query) {
        LambdaQueryWrapper<TwiceHashPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TwiceHashPO::getTwiceHashNo, query.getTwiceHashNo());
        if (query.getRemark() != null) {
            wrapper.like(TwiceHashPO :: getRemark, query.getRemark());
        }
        return twiceHashMapper.selectList(wrapper);

    }

    @Override
    public int insert(TwiceHashPO po) {
        return twiceHashMapper.insert(po);
    }

    @Override
    public int update(TwiceHashPO po) {
        LambdaUpdateWrapper<TwiceHashPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(TwiceHashPO ::getTwiceHashNo, po.getTwiceHashNo());
        wrapper.eq(TwiceHashPO ::getId, po.getId());
        return twiceHashMapper.update(po, wrapper);
    }
}
