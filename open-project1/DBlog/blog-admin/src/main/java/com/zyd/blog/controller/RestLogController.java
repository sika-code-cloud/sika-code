package com.zyd.blog.controller;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.log.pojo.query.LogStatisticsQuery;
import com.zyd.blog.business.service.SysLogService;
import com.zyd.blog.business.vo.LogConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志rest接口
 *
 * @author daiqi
 * @create 2020-09-09 0:40
 */
@RestController
@RequestMapping("/log")
public class RestLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequiresPermissions("logs")
    @PostMapping("/list")
    public PageResult list(LogConditionVO vo) {
        PageInfo<Log> pageInfo = sysLogService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("log:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取日志详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.sysLogService.getByPrimaryKey(id));
    }
}
