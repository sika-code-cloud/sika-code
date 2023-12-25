package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.YearMonthPO;
import com.sika.code.demo.sharding.pojo.query.YearMonthQuery;

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
public interface YearMonthService {
    YearMonthPO find(YearMonthQuery query);
    List<YearMonthPO> list(YearMonthQuery query);
    int insert(YearMonthPO po);
    int update(YearMonthPO po);

}
