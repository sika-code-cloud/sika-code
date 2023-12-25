package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.TwiceHashCmpxPO;
import com.sika.code.demo.sharding.pojo.query.TwiceHashCmpxQuery;

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
public interface TwiceHashCmpxService {
    TwiceHashCmpxPO find(TwiceHashCmpxQuery query);
    List<TwiceHashCmpxPO> list(TwiceHashCmpxQuery query);
    int insert(TwiceHashCmpxPO po);
    int update(TwiceHashCmpxPO po);

}
