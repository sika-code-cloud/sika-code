package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.YearWeekPO;
import com.sika.code.demo.sharding.pojo.query.YearWeekQuery;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/10 11:33
 */
public interface YearWeekService {
    YearWeekPO find(YearWeekQuery query);
    List<YearWeekPO> list(YearWeekQuery query);
    int insert(YearWeekPO po);
    int update(YearWeekPO po);

}
