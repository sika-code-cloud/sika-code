package com.zyd.blog.business.article.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 统计条目对象
 *
 * @author daiqi
 * @create 2020-09-18 10:47
 */
@Data
@Accessors(chain = true)
public class ArticleStatisticsItem {
    private Integer number;
    private Date day;

    public static ArticleStatisticsItem init() {
        return new ArticleStatisticsItem().setNumber(0);
    }
}
