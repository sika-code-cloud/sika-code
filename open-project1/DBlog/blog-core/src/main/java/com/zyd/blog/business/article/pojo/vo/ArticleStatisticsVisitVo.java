package com.zyd.blog.business.article.pojo.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zyd.blog.business.article.pojo.entity.ArticleStatisticsItem;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * 文章统计信息
 *
 * @author daiqi
 * @create 2020-09-18 10:42
 */
@Data
@Accessors(chain = true)
public class ArticleStatisticsVisitVo {
    private ArticleStatisticsItem totalItem;
    private ArticleStatisticsItem recentItem;
    private List<ArticleStatisticsItem> items;

    public ArticleStatisticsVisitVo build() {
        if (CollUtil.isEmpty(items)) {
            this.items = Lists.emptyList();
        }
        if (CollUtil.isNotEmpty(items)) {
            this.recentItem = items.get(items.size() - 1);
        }
        if (ObjectUtil.isEmpty(totalItem)) {
            totalItem = ArticleStatisticsItem.init();
        }
        if (ObjectUtil.isEmpty(recentItem)){
            recentItem = ArticleStatisticsItem.init();
        }

        return this;
    }
}
