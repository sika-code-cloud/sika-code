package com.sika.code.demo.interfaces.business.testtemp.controller;


import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.query.TestTempQuery;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;

import com.sika.code.demo.application.business.testtemp.service.TestTempService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试tem表 前端控制器
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:46
 */
@RestController
@RequestMapping("testTemp")
public class TestTempController extends BaseBizController {

    @Resource
    private TestTempService testTempService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody TestTempDTO dto) {
        return success(testTempService.save(dto));
    }


    @RequestMapping(value = "saveBatch")
    public Result saveBatch(@RequestBody List<TestTempDTO> dtos) {
         return success(testTempService.saveBatch(dtos));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody TestTempQuery query) {
        return success(testTempService.page(query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody TestTempQuery query) {
        return success(testTempService.find(query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody TestTempQuery query) {
        return success(testTempService.list(query));
    }
}
