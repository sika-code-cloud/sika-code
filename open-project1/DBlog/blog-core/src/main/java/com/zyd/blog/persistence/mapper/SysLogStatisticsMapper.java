package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.log.pojo.entity.LogStatisticsItem;
import com.zyd.blog.business.log.pojo.query.LogStatisticsQuery;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface SysLogStatisticsMapper extends BaseMapper<BizArticle> {

    /**
     * 按天统计
     */
    List<LogStatisticsItem> statisticsVisitDataForDay(@Param(value = "query") LogStatisticsQuery query);

    /**
     * 按小时统计
     */
    List<LogStatisticsItem> statisticsVisitDataForHour(@Param(value = "query") LogStatisticsQuery query);

    /**
     * 按月统计
     */
    List<LogStatisticsItem> statisticsVisitDataForMonth(@Param(value = "query") LogStatisticsQuery query);

    /**
     * 统计总数
     */
    LogStatisticsItem statisticsTotalVisitData(@Param(value = "query") LogStatisticsQuery query);

}
