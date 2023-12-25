package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.TwiceHashPO;
import com.sika.code.demo.sharding.pojo.query.TwiceHashQuery;

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
public interface TwiceHashService {
    TwiceHashPO find(TwiceHashQuery query);
    List<TwiceHashPO> list(TwiceHashQuery query);
    int insert(TwiceHashPO po);
    int update(TwiceHashPO po);

}
