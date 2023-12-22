package com.sika.code.demo.interfaces.business.node.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.node.pojo.query.NodeQuery;
import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;

import com.sika.code.demo.application.business.node.service.NodeService;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 节点表 前端控制器
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:31
 */
@RestController
@RequestMapping("node")
public class NodeController extends BaseBizController {

    @Resource
    private NodeService nodeService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody NodeDTO dto) {
        return success(nodeService.save(dto));
    }


    @RequestMapping(value = "saveBatch")
    public Result saveBatch(@RequestBody List<NodeDTO> dtos) {
         return success(nodeService.saveBatch(dtos));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody NodeQuery query) {
        return success(nodeService.page(query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody NodeQuery query) {
        return success(nodeService.find(query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody NodeQuery query) {
        return success(nodeService.list(query));
    }
}
