package com.zyd.blog.business.log.pojo.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.zyd.blog.business.log.pojo.entity.LogStatisticsItem;
import com.zyd.blog.business.log.pojo.query.LogStatisticsQuery;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文章统计信息
 *
 * @author daiqi
 * @create 2020-09-18 10:42
 */
@Data
@Accessors(chain = true)
public class LogStatisticsVisitVo {
    private LogStatisticsItem totalItem;
    private LogStatisticsItem recentItem;
    private LogStatisticsItem maxItem;
    private List<LogStatisticsItem> items;

    /**
     * 查询对象
     */
    private LogStatisticsQuery query;

    public LogStatisticsVisitVo build() {
        if (CollUtil.isNotEmpty(items)) {
            this.recentItem = items.get(items.size() - 1);
        }
        if (ObjectUtil.isEmpty(totalItem)) {
            totalItem = LogStatisticsItem.init();
        }
        if (ObjectUtil.isEmpty(recentItem)) {
            recentItem = LogStatisticsItem.init();
        }
        // 求最大访问量的item
        this.maxItem = items.stream()
                .max(Comparator.comparing(LogStatisticsItem::getNumber))
                .get();
        return this;
    }

    public LogStatisticsVisitVo buildItemsForMonth() {
        items = CollUtil.emptyIfNull(items);
        Map<Date, LogStatisticsItem> itemMap = Maps.newLinkedHashMap();
        Date beginDateOfYear = DateUtil.beginOfYear(query.getBeginDate());
        Date endDateOfYear = DateUtil.endOfYear(query.getEndDate());
        long diffMonth = DateUtil.betweenDay(beginDateOfYear, endDateOfYear, true);
        for (int i = 0; i < diffMonth; ++i) {
            Date currentDay = DateUtil.offsetMonth(query.getBeginDate(), i);
            itemMap.put(currentDay, LogStatisticsItem.init().setDate(currentDay));
        }
        for (LogStatisticsItem item : items) {
            itemMap.put(item.getDate(), item);
        }
        items = itemMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return this;
    }

    public LogStatisticsVisitVo buildItemsForDay() {
        items = CollUtil.emptyIfNull(items);
        Map<Date, LogStatisticsItem> itemMap = Maps.newLinkedHashMap();
        long diffDay = DateUtil.betweenDay(query.getBeginDate(), query.getEndDate(), true);
        for (int i = 0; i < diffDay; ++i) {
            Date currentDay = DateUtil.offsetDay(query.getBeginDate(), i);
            itemMap.put(currentDay, LogStatisticsItem.init().setDate(currentDay));
        }
        for (LogStatisticsItem item : items) {
            itemMap.put(item.getDate(), item);
        }
        items = itemMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return this;
    }

    public LogStatisticsVisitVo buildItemsForHour() {
        items = CollUtil.emptyIfNull(items);
        Map<Date, LogStatisticsItem> itemMap = Maps.newLinkedHashMap();
        Date beginOfDay = DateUtil.beginOfDay(query.getBeginDate());
        Date endOfDay = DateUtil.endOfDay(query.getEndDate());
        long diffHour = DateUtil.betweenDay(beginOfDay, endOfDay, true);
        for (int i = 0; i < diffHour; ++i) {
            Date currentDay = DateUtil.offsetHour(query.getBeginDate(), i);
            itemMap.put(currentDay, LogStatisticsItem.init().setDate(currentDay));
        }
        for (LogStatisticsItem item : items) {
            itemMap.put(item.getDate(), item);
        }
        items = itemMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return this;
    }
}
