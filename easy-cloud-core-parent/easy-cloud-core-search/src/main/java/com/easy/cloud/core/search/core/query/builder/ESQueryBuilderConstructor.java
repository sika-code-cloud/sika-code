package com.easy.cloud.core.search.core.query.builder;

import com.easy.cloud.core.search.constant.EcElasticSearchQueryConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ESQueryBuilderConstructor
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
 */
public class ESQueryBuilderConstructor {

    /**
     * 设置默认为最大值
     */
    private int size = EcElasticSearchQueryConstant.QUERY_MAX;

    private int from = 0;

    private String asc;

    private String desc;

    private String scrollId;

    /**
     * 查询条件容器
     */
    private List<EcElasticSearchCriterion> mustCriterions = new ArrayList<>();
    private List<EcElasticSearchCriterion> shouldCriterions = new ArrayList<>();
    private List<EcElasticSearchCriterion> mustNotCriterions = new ArrayList<>();

    //构造builder
    public QueryBuilder listBuilders() {
        int count = mustCriterions.size() + shouldCriterions.size() + mustNotCriterions.size();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = null;

        if (count >= 1) {
            //must容器
            if (!CollectionUtils.isEmpty(mustCriterions)) {
                for (EcElasticSearchCriterion criterion : mustCriterions) {
                    for (QueryBuilder builder : criterion.listBuilders()) {
                        queryBuilder = boolQueryBuilder.must(builder);
                    }
                }
            }
            //should容器
            if (!CollectionUtils.isEmpty(shouldCriterions)) {
                for (EcElasticSearchCriterion criterion : shouldCriterions) {
                    for (QueryBuilder builder : criterion.listBuilders()) {
                        queryBuilder = boolQueryBuilder.should(builder);
                    }

                }
            }
            //must not 容器
            if (!CollectionUtils.isEmpty(mustNotCriterions)) {
                for (EcElasticSearchCriterion criterion : mustNotCriterions) {
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
     * 构造searchRequestBuilder
     * @param index
     * @param type
     * @return
     */
    public SearchRequestBuilder getSearchRequestBuilder(Client client, String index, String type) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
        //排序
        addSort(searchRequestBuilder);
        //设置查询体
        searchRequestBuilder.setQuery(this.listBuilders());
        searchRequestBuilder.setSize(this.getSize());
        searchRequestBuilder.setScroll(new TimeValue(600000));
        return searchRequestBuilder;
    }
    /**
     * 添加排序
     *
     * @param searchRequestBuilder
     */
    private void addSort(SearchRequestBuilder searchRequestBuilder) {
        if (StringUtils.isNotEmpty(this.getAsc())) {
            searchRequestBuilder.addSort(this.getAsc(), SortOrder.ASC);
//            searchRequestBuilder.addSor
        }
        if (StringUtils.isNotEmpty(this.getDesc())) {
            searchRequestBuilder.addSort(this.getDesc(), SortOrder.DESC);
        }
    }

    /**
     * 增加简单条件表达式
     */
    public ESQueryBuilderConstructor must(EcElasticSearchCriterion criterion){
        if(criterion!=null){
            mustCriterions.add(criterion);
        }
        return this;
    }
    /**
     * 增加简单条件表达式
     */
    public ESQueryBuilderConstructor should(EcElasticSearchCriterion criterion){
        if(criterion!=null){
            shouldCriterions.add(criterion);
        }
        return this;
    }
    /**
     * 增加简单条件表达式
     */
    public ESQueryBuilderConstructor mustNot(EcElasticSearchCriterion criterion){
        if(criterion!=null){
            mustNotCriterions.add(criterion);
        }
        return this;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 0) {
            size = 0;
        }else if (size > EcElasticSearchQueryConstant.QUERY_MAX) {
            size = EcElasticSearchQueryConstant.QUERY_MAX;
        }
        this.size = size;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getScrollId() {
        return scrollId;
    }
    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }
}

