package com.zyd.blog.controller;

import com.zyd.blog.business.log.pojo.query.LogStatisticsQuery;
import com.zyd.blog.business.service.BizStatisticsService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.service.SysLogService;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/5/22 16:47
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
public class RestStatisticsController {

    @Autowired
    private SysConfigService configService;
    @Autowired
    private BizStatisticsService statisticsService;
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/siteInfo")
    public ResponseVO getSiteInfo() {
        return ResultUtil.success(configService.getSiteInfo());
    }

    @RequestMapping("/listSpider")
    public ResponseVO listSpider() {
        return ResultUtil.success(statisticsService.listSpider(10));
    }

    @RequestMapping("/listType")
    public ResponseVO listType() {
        return ResultUtil.success(statisticsService.listType(10));
    }

    @RequestMapping(value = "/recentWeekLog")
    public ResponseVO statisticsRecentWeek(@RequestBody LogStatisticsQuery query) {
        return ResultUtil.success(sysLogService.statisticsRecentWeek(query));
    }

    @RequestMapping(value = "/sameYearLog")
    public ResponseVO statisticsSameYear(@RequestBody LogStatisticsQuery query) {
        return ResultUtil.success(sysLogService.statisticsSameYear(query));
    }

    @RequestMapping(value = "/sameMonthLog")
    public ResponseVO statisticsSameMonth(@RequestBody LogStatisticsQuery query) {
        return ResultUtil.success(sysLogService.statisticsSameMonth(query));
    }

    @RequestMapping(value = "/sameDayLog")
    public ResponseVO statisticsSameDay(@RequestBody LogStatisticsQuery query) {
        return ResultUtil.success(sysLogService.statisticsSameDay(query));
    }
}
