package com.easy.cloud.core.search.demo.query;

import com.easy.cloud.core.search.core.query.builder.ESQueryBuilderConstructor;
import com.easy.cloud.core.search.core.query.builder.EcElasticSearchQueryBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Title: ScanDemo
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 2.0
 */
@Component
public class ScanDemo {
    private static final Logger logger = Logger.getLogger(ScanDemo.class);
    @Autowired
    public Client client;

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/java-term-level-queries.html
     * @param aggFile
     * @return
     */
    public QueryBuilder queryBuilder(String aggFile) {
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor()
                .must(new EcElasticSearchQueryBuilder()
                    .term("filed", "")
                    .term("filed", "")
                    .addQueryBuilder(null)
                    .terms("filed", Collections.singleton("")));
        constructor.setAsc("startTime");
        return constructor.listBuilders();
    }

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/_metrics_aggregations.html
     * https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.3/_bucket_aggregations.html
     * @param aggFile
     * @return
     */
    public AggregationBuilder aggregation(String aggFile) {

        TermsAggregationBuilder agg = AggregationBuilders.terms(aggFile).field(aggFile).size(0).order(BucketOrder.aggregation("_count", false));
        return agg;
    }


    public SearchResponse search(String index, String type) {
        SearchResponse searchResponse = null;
        try {
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
//                    .addSort()
//                    .addAggregation()
//                    .setSize()
//                    .setScroll()
                    ;

        } catch (Exception e) {
            //do somthing
            //
         }
         return null;
    }

    /**
     * 功能描述：查询
     *
     * @param index       索引名
     * @param type        类型
     * @param constructor 查询构造
     */
    public SearchResponse scrollsearch(String index, String type, ESQueryBuilderConstructor constructor) throws Exception {
        SearchResponse searchResponse = null;
        try {
            String scrollId = constructor.getScrollId();
            if (scrollId != null && !"".equals(scrollId)) {
                searchResponse = client.prepareSearchScroll(scrollId)
                        .setScroll(new TimeValue(600000))
                        .execute()
                        .actionGet();
            } else {
                SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
                //排序
                addSort(constructor, searchRequestBuilder);
                //设置查询体
                searchRequestBuilder.setQuery(constructor.listBuilders());
                searchRequestBuilder.setSize(constructor.getSize());
                searchRequestBuilder.setScroll(new TimeValue(600000));
//                searchRequestBuilder.
                logger.debug(searchRequestBuilder.toString());
                searchResponse = searchRequestBuilder.execute().actionGet();
            }
            logger.debug(searchResponse.toString());
        } catch (Exception e) {
            logger.error("查询es出错", e);
        }
        return searchResponse;
    }

    /**
     * 添加排序
     *
     * @param constructor
     * @param searchRequestBuilder
     */
    private void addSort(ESQueryBuilderConstructor constructor, SearchRequestBuilder searchRequestBuilder) {
        if (StringUtils.isNotEmpty(constructor.getAsc())) {
            searchRequestBuilder.addSort(constructor.getAsc(), SortOrder.ASC);
//            searchRequestBuilder.addSor
        }
        if (StringUtils.isNotEmpty(constructor.getDesc())) {
            searchRequestBuilder.addSort(constructor.getDesc(), SortOrder.DESC);
        }
    }

}
