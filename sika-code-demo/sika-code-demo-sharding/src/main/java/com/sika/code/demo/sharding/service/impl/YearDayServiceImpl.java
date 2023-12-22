package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sika.code.demo.sharding.pojo.po.YearDayPO;
import com.sika.code.demo.sharding.mapper.YearDayMapper;
import com.sika.code.demo.sharding.pojo.query.YearDayQuery;
import com.sika.code.demo.sharding.service.YearDayService;
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
public class YearDayServiceImpl implements YearDayService {
    @Resource
    private YearDayMapper yearDayMapper;

    @Override
    public YearDayPO find(YearDayQuery query) {
        LambdaQueryWrapper<YearDayPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearDayPO::getYearDayDate, query.getYearDayDate());
        wrapper.eq(YearDayPO::getId, query.getId());
        if (query.getRemark() != null) {
            wrapper.like(YearDayPO :: getRemark, query.getRemark());
        }
        return yearDayMapper.selectOne(wrapper);
    }

    @Override
    public List<YearDayPO> list(YearDayQuery query) {
        LambdaQueryWrapper<YearDayPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearDayPO::getYearDayDate, query.getYearDayDate());
        if (query.getRemark() != null) {
            wrapper.like(YearDayPO :: getRemark, query.getRemark());
        }
        return yearDayMapper.selectList(wrapper);

    }

    @Override
    public int insert(YearDayPO po) {
        return yearDayMapper.insert(po);
    }

    @Override
    public int update(YearDayPO po) {
        LambdaUpdateWrapper<YearDayPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(YearDayPO::getYearDayDate, po.getYearDayDate());
        wrapper.eq(YearDayPO::getId, po.getId());
        return yearDayMapper.update(po, wrapper);
    }
}
