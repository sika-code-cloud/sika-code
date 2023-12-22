package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.YearMonthPO;
import com.sika.code.demo.sharding.pojo.query.YearMonthQuery;
import com.sika.code.demo.sharding.service.YearMonthService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * YearMonthController
 *
 * @author : daiqi
 * @date : 2023-12-11
 */
@RestController
@RequestMapping(value = "yearMonth")
public class YearMonthController {
    @Resource
    private YearMonthService yearMonthService;

    @RequestMapping(value = "find")
    public YearMonthPO find(@RequestBody YearMonthQuery query) {
        return yearMonthService.find(query);
    }

    @RequestMapping(value = "list")
    public List<YearMonthPO> list(@RequestBody YearMonthQuery query) {
        return yearMonthService.list(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody YearMonthPO po) {
        return yearMonthService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody YearMonthPO po) {
        return yearMonthService.update(po);
    }
}
