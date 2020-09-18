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

    List<LogStatisticsItem> statisticsVisitData(@Param(value = "query") LogStatisticsQuery query);

    LogStatisticsItem statisticsTotalVisitData(@Param(value = "query") LogStatisticsQuery query);

}
