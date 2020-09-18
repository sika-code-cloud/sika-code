package com.zyd.blog.business.article.pojo.query;

import cn.hutool.core.date.DateUtil;
import com.zyd.blog.business.entity.Article;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文章统计信息
 *
 * @author daiqi
 * @create 2020-09-18 10:42
 */
@Data
@Accessors(chain = true)
public class ArticleStatisticsQuery extends Article {
    private Date beginDate;
    private Date endDate;

    public ArticleStatisticsQuery build() {
        if (this.beginDate == null) {
            this.beginDate = new Date();
        }
        if (this.endDate == null) {
            this.beginDate = new Date();
        }
        this.beginDate = DateUtil.beginOfDay(beginDate);
        this.endDate = DateUtil.endOfDay(endDate);
        return this;
    }

}
