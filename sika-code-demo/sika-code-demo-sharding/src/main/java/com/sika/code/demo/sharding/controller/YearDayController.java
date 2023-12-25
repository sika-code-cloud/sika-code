package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.YearDayPO;
import com.sika.code.demo.sharding.pojo.query.YearDayQuery;
import com.sika.code.demo.sharding.service.YearDayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * YearDayController
 *
 * @author : daiqi
 * @date : 2023-12-11
 */
@RestController
@RequestMapping(value = "yearDay")
public class YearDayController {
    @Resource
    private YearDayService yearDayService;

    @RequestMapping(value = "find")
    public YearDayPO find(@RequestBody YearDayQuery query) {
        return yearDayService.find(query);
    }

    @RequestMapping(value = "list")
    public List<YearDayPO> list(@RequestBody YearDayQuery query) {
        return yearDayService.list(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody YearDayPO po) {
        return yearDayService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody YearDayPO po) {
        return yearDayService.update(po);
    }
}
