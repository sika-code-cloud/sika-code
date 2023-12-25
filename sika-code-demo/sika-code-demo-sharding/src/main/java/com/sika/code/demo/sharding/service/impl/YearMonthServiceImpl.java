package com.sika.code.demo.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sika.code.demo.sharding.mapper.YearMonthMapper;
import com.sika.code.demo.sharding.pojo.po.YearMonthPO;
import com.sika.code.demo.sharding.pojo.query.YearMonthQuery;
import com.sika.code.demo.sharding.service.YearMonthService;
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
public class YearMonthServiceImpl implements YearMonthService {
    @Resource
    private YearMonthMapper yearMonthMapper;

    @Override
    public YearMonthPO find(YearMonthQuery query) {
        LambdaQueryWrapper<YearMonthPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearMonthPO::getYearMonthDate, query.getYearMonthDate());
        wrapper.eq(YearMonthPO::getId, query.getId());
        if (query.getRemark() != null) {
            wrapper.like(YearMonthPO :: getRemark, query.getRemark());
        }
        return yearMonthMapper.selectOne(wrapper);
    }

    @Override
    public List<YearMonthPO> list(YearMonthQuery query) {
        LambdaQueryWrapper<YearMonthPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YearMonthPO::getYearMonthDate, query.getYearMonthDate());
        if (query.getRemark() != null) {
            wrapper.like(YearMonthPO :: getRemark, query.getRemark());
        }
        return yearMonthMapper.selectList(wrapper);

    }
    
    @Override
    public int insert(YearMonthPO po) {
        return yearMonthMapper.insert(po);
    }

    @Override
    public int update(YearMonthPO po) {
        LambdaUpdateWrapper<YearMonthPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(YearMonthPO::getYearMonthDate, po.getYearMonthDate());
        wrapper.eq(YearMonthPO::getId, po.getId());
        return yearMonthMapper.update(po, wrapper);
    }
}
