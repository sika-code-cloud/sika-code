package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.YearWeekPO;
import com.sika.code.demo.sharding.pojo.query.YearWeekQuery;
import com.sika.code.demo.sharding.service.YearWeekService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * YearWeekController
 *
 * @author : daiqi
 * @date : 2023-12-11
 */
@RestController
@RequestMapping(value = "yearWeek")
public class YearWeekController {
    @Resource
    private YearWeekService yearWeekService;

    @RequestMapping(value = "find")
    public YearWeekPO find(@RequestBody YearWeekQuery query) {
        return yearWeekService.find(query);
    }

    @RequestMapping(value = "list")
    public List<YearWeekPO> list(@RequestBody YearWeekQuery query) {
        return yearWeekService.list(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody YearWeekPO po) {
        return yearWeekService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody YearWeekPO po) {
        return yearWeekService.update(po);
    }
}
