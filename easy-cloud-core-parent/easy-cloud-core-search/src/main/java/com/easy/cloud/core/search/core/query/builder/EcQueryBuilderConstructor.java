package com.easy.cloud.core.search.core.query.builder;

import com.easy.cloud.core.search.constant.EcElasticSearchQueryConstant;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ESQueryBuilderConstructor
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
 */
public class EcQueryBuilderConstructor extends SearchRequestBuilder {

    private String scrollId;

    /**
     * 查询条件容器
     */
    private List<EcQueryBuilderCriterion> mustCriterions = new ArrayList<>();
    private List<EcQueryBuilderCriterion> shouldCriterions = new ArrayList<>();
    private List<EcQueryBuilderCriterion> mustNotCriterions = new ArrayList<>();

    public EcQueryBuilderConstructor(ElasticsearchClient client, SearchAction action) {
        super(client, action);
    }

    //构造builder
    public QueryBuilder listBuilders() {
        int count = mustCriterions.size() + shouldCriterions.size() + mustNotCriterions.size();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = null;

        if (count >= 1) {
            //must容器
            if (!CollectionUtils.isEmpty(mustCriterions)) {
                for (EcQueryBuilderCriterion criterion : mustCriterions) {
                    for (QueryBuilder builder : criterion.listBuilders()) {
                        queryBuilder = boolQueryBuilder.must(builder);
                    }
                }
            }
            //should容器
            if (!CollectionUtils.isEmpty(shouldCriterions)) {
                for (EcQueryBuilderCriterion criterion : shouldCriterions) {
                    for (QueryBuilder builder : criterion.listBuilders()) {
                        queryBuilder = boolQueryBuilder.should(builder);
                    }
                }
            }
            //must not 容器
            if (!CollectionUtils.isEmpty(mustNotCriterions)) {
                for (EcQueryBuilderCriterion criterion : mustNotCriterions) {
                    for (QueryBuilder builder : criterion.listBuilders()) {
                        queryBuilder = boolQueryBuilder.mustNot(builder);
                    }
                }
            }
            return queryBuilder;
        } else {
            return null;
        }
    }

    /**
     * 增加简单条件表达式
     */
    public EcQueryBuilderConstructor must(EcQueryBuilderCriterion criterion){
        if(criterion!=null){
            mustCriterions.add(criterion);
        }
        return this;
    }
    /**
     * 增加简单条件表达式
     */
    public EcQueryBuilderConstructor should(EcQueryBuilderCriterion criterion){
        if(criterion!=null){
            shouldCriterions.add(criterion);
        }
        return this;
    }
    /**
     * 增加简单条件表达式
     */
    public EcQueryBuilderConstructor mustNot(EcQueryBuilderCriterion criterion){
        if(criterion!=null){
            mustNotCriterions.add(criterion);
        }
        return this;
    }

    public String getScrollId() {
        return scrollId;
    }
    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }
}

