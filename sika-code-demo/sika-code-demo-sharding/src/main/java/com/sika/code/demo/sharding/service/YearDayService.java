package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.YearDayPO;
import com.sika.code.demo.sharding.pojo.query.YearDayQuery;

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
public interface YearDayService {
    YearDayPO find(YearDayQuery query);
    List<YearDayPO> list(YearDayQuery query);
    int insert(YearDayPO po);
    int update(YearDayPO po);

}
