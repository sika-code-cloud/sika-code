package com.sika.code.demo.sharding.service;

import com.sika.code.demo.sharding.pojo.po.HintPlusPO;
import com.sika.code.demo.sharding.pojo.query.HintPlusQuery;

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
public interface HintPlusService {
    HintPlusPO find(HintPlusQuery query);
    List<HintPlusPO> list(HintPlusQuery query);
    List<HintPlusPO> listHint(HintPlusQuery query);
    int insert(HintPlusPO po);
    int update(HintPlusPO po);

}
