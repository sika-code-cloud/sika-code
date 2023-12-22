package com.sika.code.demo.sharding.controller;

import com.sika.code.demo.sharding.pojo.po.HintPlusPO;
import com.sika.code.demo.sharding.pojo.query.HintPlusQuery;
import com.sika.code.demo.sharding.service.HintPlusService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * HintPlusController
 *
 * @author : daiqi
 * @date : 2023-12-11
 */
@RestController
@RequestMapping(value = "hintPlus")
public class HintPlusController {
    @Resource
    private HintPlusService hintPlusService;

    @RequestMapping(value = "find")
    public HintPlusPO find(@RequestBody HintPlusQuery query) {
        return hintPlusService.find(query);
    }

    @RequestMapping(value = "list")
    public List<HintPlusPO> list(@RequestBody HintPlusQuery query) {
        return hintPlusService.list(query);
    }

    @RequestMapping(value = "listHint")
    public List<HintPlusPO> listHint(@RequestBody HintPlusQuery query) {
        return hintPlusService.listHint(query);
    }

    @RequestMapping(value = "insert")
    public int insert(@RequestBody HintPlusPO po) {
        return hintPlusService.insert(po);
    }

    @RequestMapping(value = "update")
    public int update(@RequestBody HintPlusPO po) {
        return hintPlusService.update(po);
    }
}
