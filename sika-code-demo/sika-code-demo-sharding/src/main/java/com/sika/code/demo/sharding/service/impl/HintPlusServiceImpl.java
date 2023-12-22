package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sika.code.db.sharding.core.plugin.HintManagerPlus;
import com.sika.code.demo.sharding.pojo.po.HintPlusPO;
import com.sika.code.demo.sharding.mapper.HintPlusMapper;
import com.sika.code.demo.sharding.pojo.query.HintPlusQuery;
import com.sika.code.demo.sharding.service.HintPlusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * 参数能使用::
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/10 11:39
 */
@Service
public class HintPlusServiceImpl implements HintPlusService {
    @Resource
    private HintPlusMapper hintPlusMapper;

    @Override
    public HintPlusPO find(HintPlusQuery query) {
        try (HintManagerPlus hintManager = HintManagerPlus.getInstancePlus()) {
            LambdaQueryWrapper<HintPlusPO> wrapper = new LambdaQueryWrapper<>();
            if (query.getRemark() != null) {
                wrapper.like(HintPlusPO::getRemark, query.getRemark());
            }
            wrapper.eq(HintPlusPO::getHintPlusNo, query.getHintPlusNo());
            wrapper.eq(HintPlusPO::getId, query.getId());
            hintManager.putShardingValue("sika_hint_plus", "hint_plus_no", query.getHintPlusNo());
            return hintPlusMapper.selectOne(wrapper);
        }
    }

    @Override
    public List<HintPlusPO> list(HintPlusQuery query) {
        try (HintManagerPlus hintManager = HintManagerPlus.getInstancePlus()) {
            LambdaQueryWrapper<HintPlusPO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(HintPlusPO::getHintPlusNo, query.getHintPlusNo());
            if (query.getRemark() != null) {
                wrapper.like(HintPlusPO::getRemark, query.getRemark());
            }
            hintManager.putShardingValue("sika_hint_plus", "hint_plus_no", query.getHintPlusNo());
            return hintPlusMapper.selectList(wrapper);
        }
    }

    @Override
    public List<HintPlusPO> listHint(HintPlusQuery query) {
        try (HintManagerPlus hintManager = HintManagerPlus.getInstance()) {
            hintManager.addDatabaseShardingValue("sika_hint_plus", 1);
            hintManager.addTableShardingValue("sika_hint_plus", 2);

            LambdaQueryWrapper<HintPlusPO> wrapper = new LambdaQueryWrapper<>();
            if (query.getRemark() != null) {
                wrapper.like(HintPlusPO::getRemark, query.getRemark());
            }
            return hintPlusMapper.selectList(wrapper);
        }
    }

    @Override
    public int insert(HintPlusPO po) {
        try (HintManagerPlus hintManager = HintManagerPlus.getInstancePlus()) {
            hintManager.putShardingValue("sika_hint_plus", "hint_plus_no", po.getHintPlusNo());
            return hintPlusMapper.insert(po);
        }
    }

    @Override
    public int update(HintPlusPO po) {
        try (HintManagerPlus hintManager = HintManagerPlus.getInstancePlus()) {
            hintManager.putShardingValue("sika_hint_plus", "hint_plus_no", po.getHintPlusNo());
            return hintPlusMapper.updateById(po);
        }
    }
}
