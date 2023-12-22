package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.TwiceHashCmpxPO;
import com.sika.code.demo.sharding.pojo.query.TwiceHashCmpxQuery;
import com.sika.code.demo.sharding.service.TwiceHashCmpxService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TwiceHashController
 *
 * @author : daiqi
 * @date : 2023-12-11
 */
@RestController
@RequestMapping(value = "twiceHashCmpx")
public class TwiceHashCmpxController {
    @Resource
    private TwiceHashCmpxService twiceHashCmpxService;

    @RequestMapping(value = "find")
    public TwiceHashCmpxPO find(@RequestBody TwiceHashCmpxQuery query) {
        return twiceHashCmpxService.find(query);
    }

    @RequestMapping(value = "list")
    public List<TwiceHashCmpxPO> list(@RequestBody TwiceHashCmpxQuery query) {
        return twiceHashCmpxService.list(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody TwiceHashCmpxPO po) {
        return twiceHashCmpxService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody TwiceHashCmpxPO po) {
        return twiceHashCmpxService.update(po);
    }
}
