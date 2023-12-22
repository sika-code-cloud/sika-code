package com.sika.code.demo.interfaces.business.chain.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.chain.pojo.query.ChainQuery;
import com.sika.code.demo.infrastructure.business.chain.pojo.dto.ChainDTO;

import com.sika.code.demo.application.business.chain.service.ChainService;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 流程表 前端控制器
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:19
 */
@RestController
@RequestMapping("chain")
public class ChainController extends BaseBizController {

    @Resource
    private ChainService chainService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ChainDTO dto) {
        return success(chainService.save(dto));
    }


    @RequestMapping(value = "saveBatch")
    public Result saveBatch(@RequestBody List<ChainDTO> dtos) {
         return success(chainService.saveBatch(dtos));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ChainQuery query) {
        return success(chainService.page(query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ChainQuery query) {
        return success(chainService.find(query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ChainQuery query) {
        return success(chainService.list(query));
    }
}
