package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.article.pojo.entity.ArticleStatisticsItem;
import com.zyd.blog.business.article.pojo.query.ArticleStatisticsQuery;
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
public interface BizArticleStatisticsMapper extends BaseMapper<BizArticle> {

    List<ArticleStatisticsItem> statisticsVisitData(@Param(value = "query") ArticleStatisticsQuery query);

    ArticleStatisticsItem statisticsTotalVisitData(@Param(value = "query") ArticleStatisticsQuery query);

}
