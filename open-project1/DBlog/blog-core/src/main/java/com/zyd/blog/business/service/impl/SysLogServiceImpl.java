package com.zyd.blog.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.LogLevelEnum;
import com.zyd.blog.business.enums.LogTypeEnum;
import com.zyd.blog.business.enums.PlatformEnum;
import com.zyd.blog.business.log.pojo.entity.LogStatisticsItem;
import com.zyd.blog.business.log.pojo.query.LogStatisticsQuery;
import com.zyd.blog.business.log.pojo.vo.LogStatisticsVisitVo;
import com.zyd.blog.business.service.SysLogService;
import com.zyd.blog.business.util.WebSpiderUtils;
import com.zyd.blog.business.vo.LogConditionVO;
import com.zyd.blog.persistence.beans.SysLog;
import com.zyd.blog.persistence.mapper.SysLogMapper;
import com.zyd.blog.persistence.mapper.SysLogStatisticsMapper;
import com.zyd.blog.util.RequestUtil;
import com.zyd.blog.util.SessionUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:50
 * @since 1.0
 */
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private SysLogStatisticsMapper sysLogStatisticsMapper;

    @Override
    public PageInfo<Log> findPageBreakByCondition(LogConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysLog> list = sysLogMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Log> boList = new ArrayList<>();
        for (SysLog sysLog : list) {
            boList.add(new Log(sysLog));
        }
        PageInfo bean = new PageInfo<SysLog>(list);
        bean.setList(boList);
        return bean;
    }

    @Async
    @Override
    public void asyncSaveSystemLog(PlatformEnum platform, String bussinessName) {
        String ua = RequestUtil.getUa();
        Log sysLog = new Log();
        sysLog.setLogLevel(LogLevelEnum.INFO);
        sysLog.setType(platform.equals(PlatformEnum.WEB) ? LogTypeEnum.VISIT : LogTypeEnum.SYSTEM);
        sysLog.setIp(RequestUtil.getIp());
        sysLog.setReferer(RequestUtil.getReferer());
        sysLog.setRequestUrl(RequestUtil.getRequestUrl());
        sysLog.setUa(ua);
        sysLog.setSpiderType(WebSpiderUtils.parseUa(ua));
        sysLog.setParams(JSONObject.toJSONString(RequestUtil.getParametersMap()));
        User user = SessionUtil.getUser();
        if (user != null) {
            sysLog.setUserId(user.getId());
            sysLog.setContent(String.format("用户: [%s] | 操作: %s", user.getUsername(), bussinessName));
        } else {
            sysLog.setContent(String.format("访客: [%s] | 操作: %s", sysLog.getIp(), bussinessName));
        }

        try {
            UserAgent agent = UserAgent.parseUserAgentString(ua);
            sysLog.setBrowser(agent.getBrowser().getName());
            sysLog.setOs(agent.getOperatingSystem().getName());
            this.insert(sysLog);
        } catch (Exception e) {
            log.error("保存日志失败", log);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Log insert(Log entity) {
        Assert.notNull(entity, "Log不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysLogMapper.insertSelective(entity.getSysLog());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysLogMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Log entity) {
        Assert.notNull(entity, "Log不可为空！");
        entity.setUpdateTime(new Date());
        return sysLogMapper.updateByPrimaryKeySelective(entity.getSysLog()) > 0;
    }

    @Override
    public Log getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysLog entity = sysLogMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Log(entity);
    }

    @Override
    public LogStatisticsVisitVo statisticsRecentWeek() {
        LogStatisticsQuery query = new LogStatisticsQuery()
                .setBeginDate(DateUtil.lastWeek())
                .setEndDate(DateUtil.date());
        return statisticsVisitDataForDay(query);
    }

    public LogStatisticsVisitVo statisticsRecentYear() {
        LogStatisticsQuery query = new LogStatisticsQuery()
                .setBeginDate(DateUtil.beginOfYear(new Date()))
                .setEndDate(DateUtil.beginOfYear(new Date()));
        return statisticsVisitDataForMonth(query);
    }

    public LogStatisticsVisitVo statisticsRecentMonth() {
        LogStatisticsQuery query = new LogStatisticsQuery()
                .setBeginDate(DateUtil.lastMonth())
                .setEndDate(DateUtil.date());
        return statisticsVisitDataForDay(query);
    }

    public LogStatisticsVisitVo statisticsRecentDay() {
        LogStatisticsQuery query = new LogStatisticsQuery()
                .setBeginDate(DateUtil.beginOfDay(new Date()))
                .setEndDate(DateUtil.beginOfDay(new Date()));
        return statisticsVisitDataForHour(query);
    }

    public LogStatisticsVisitVo statisticsVisitDataForMonth(LogStatisticsQuery query) {
        // 构建
        query.build();
        // 查询
        List<LogStatisticsItem> items = sysLogStatisticsMapper.statisticsVisitDataForMonth(query);
        LogStatisticsItem totalItem = sysLogStatisticsMapper.statisticsTotalVisitData(query);
        LogStatisticsVisitVo statisticsVisitVo = new LogStatisticsVisitVo();
        return statisticsVisitVo.setItems(items)
                .setTotalItem(totalItem)
                .setQuery(query)
                .buildItemsForMonth()
                .build();
    }


    @Override
    public LogStatisticsVisitVo statisticsVisitDataForDay(LogStatisticsQuery query) {
        // 构建
        query.build();
        // 查询
        List<LogStatisticsItem> items = sysLogStatisticsMapper.statisticsVisitDataForDay(query);
        LogStatisticsItem totalItem = sysLogStatisticsMapper.statisticsTotalVisitData(query);
        LogStatisticsVisitVo statisticsVisitVo = new LogStatisticsVisitVo();
        return statisticsVisitVo.setItems(items)
                .setTotalItem(totalItem)
                .setQuery(query)
                .buildItemsForDay()
                .build();
    }

    public LogStatisticsVisitVo statisticsVisitDataForHour(LogStatisticsQuery query) {
        // 构建
        query.build();
        // 查询
        List<LogStatisticsItem> items = sysLogStatisticsMapper.statisticsVisitDataForHour(query);
        LogStatisticsItem totalItem = sysLogStatisticsMapper.statisticsTotalVisitData(query);
        LogStatisticsVisitVo statisticsVisitVo = new LogStatisticsVisitVo();
        return statisticsVisitVo.setItems(items)
                .setTotalItem(totalItem)
                .setQuery(query)
                .buildItemsForHour()
                .build();
    }
}
