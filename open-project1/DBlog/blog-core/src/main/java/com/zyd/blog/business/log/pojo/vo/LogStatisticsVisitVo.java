package com.zyd.blog.business.log.pojo.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zyd.blog.business.log.pojo.entity.LogStatisticsItem;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;

import java.util.Comparator;
import java.util.List;

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

    public LogStatisticsVisitVo build() {
        if (CollUtil.isEmpty(items)) {
            this.items = Lists.emptyList();
        }
        if (CollUtil.isNotEmpty(items)) {
            this.recentItem = items.get(items.size() - 1);
        }
        if (ObjectUtil.isEmpty(totalItem)) {
            totalItem = LogStatisticsItem.init();
        }
        if (ObjectUtil.isEmpty(recentItem)){
            recentItem = LogStatisticsItem.init();
        }
        // 求最大访问量的item
        this.maxItem = items.stream()
                .max(Comparator.comparing(LogStatisticsItem::getNumber))
                .get();

        return this;
    }
}
