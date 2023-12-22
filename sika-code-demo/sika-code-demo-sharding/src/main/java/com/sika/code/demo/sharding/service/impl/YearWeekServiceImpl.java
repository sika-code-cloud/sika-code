package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sika.code.demo.sharding.pojo.po.YearWeekPO;
import com.sika.code.demo.sharding.mapper.YearWeekMapper;
import com.sika.code.demo.sharding.pojo.query.YearWeekQuery;
import com.sika.code.demo.sharding.service.YearWeekService;
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
public class YearWeekServiceImpl implements YearWeekService {
    @Resource
    private YearWeekMapper yearWeekMapper;

    @Override
    public YearWeekPO find(YearWeekQuery query) {
        LambdaQueryWrapper<YearWeekPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearWeekPO::getYearWeekDate, query.getYearWeekDate());
        if (query.getRemark() != null) {
            wrapper.like(YearWeekPO :: getRemark, query.getRemark());
        }
        return yearWeekMapper.selectOne(wrapper);
    }

    @Override
    public List<YearWeekPO> list(YearWeekQuery query) {
        LambdaQueryWrapper<YearWeekPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearWeekPO::getYearWeekDate, query.getYearWeekDate());
        if (query.getRemark() != null) {
            wrapper.like(YearWeekPO :: getRemark, query.getRemark());
        }
        return yearWeekMapper.selectList(wrapper);

    }

    @Override
    public int insert(YearWeekPO po) {
        return yearWeekMapper.insert(po);
    }

    @Override
    public int update(YearWeekPO po) {
        LambdaUpdateWrapper<YearWeekPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(YearWeekPO::getYearWeekDate, po.getYearWeekDate());
        wrapper.eq(YearWeekPO::getId, po.getId());
        return yearWeekMapper.update(po, wrapper);
    }
}
