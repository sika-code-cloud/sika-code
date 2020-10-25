package com.zyd.blog.business.log.pojo.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
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
        buildItemsForCore(query.getQueryTypeEnum().getDateField());
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

    public LogStatisticsVisitVo buildItemsForCore(DateField dateField) {
        items = CollUtil.emptyIfNull(items);
        Date beginOfDay = DateUtil.beginOfDay(query.getBeginDate());
        Date endOfDay = DateUtil.endOfDay(query.getEndDate());
        long differ = 0;
        if (DateField.MONTH.equals(dateField)) {
            differ = DateUtil.betweenMonth(beginOfDay, endOfDay, true);
        } else if (DateField.DAY_OF_YEAR.equals(dateField)) {
            differ = DateUtil.between(beginOfDay, endOfDay, DateUnit.DAY, true);
        } else if (DateField.HOUR_OF_DAY.equals(dateField)) {
            differ = DateUtil.between(beginOfDay, endOfDay, DateUnit.HOUR, true);
        }

        Map<Date, LogStatisticsItem> itemMap = Maps.newLinkedHashMap();
        for (int i = 0; i <= differ; ++i) {
            Date currentDate = DateUtil.offset(beginOfDay, dateField, i);
            itemMap.put(currentDate, LogStatisticsItem.init().setDate(currentDate));
        }
        for (LogStatisticsItem item : items) {
            itemMap.put(item.getDate(), item);
        }
        items = itemMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return this;
    }
}
