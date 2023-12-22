package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.TwiceHashPO;
import com.sika.code.demo.sharding.pojo.query.TwiceHashQuery;
import com.sika.code.demo.sharding.service.TwiceHashService;
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
@RequestMapping(value = "twiceHash")
public class TwiceHashController {
    @Resource
    private TwiceHashService twiceHashService;

    @RequestMapping(value = "find")
    public TwiceHashPO find(@RequestBody TwiceHashQuery query) {
        return twiceHashService.find(query);
    }

    @RequestMapping(value = "list")
    public List<TwiceHashPO> list(@RequestBody TwiceHashQuery query) {
        return twiceHashService.list(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody TwiceHashPO po) {
        return twiceHashService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody TwiceHashPO po) {
        return twiceHashService.update(po);
    }
}
