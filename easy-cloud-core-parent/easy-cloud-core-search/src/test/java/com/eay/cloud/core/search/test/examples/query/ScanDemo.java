package com.eay.cloud.core.search.test.examples.query;

import com.easy.cloud.core.search.core.query.builder.ESQueryBuilderConstructor;
import com.easy.cloud.core.search.core.query.builder.EcElasticSearchQueryBuilder;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Title: ScanDemo
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
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
        QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("", ""))
                .must();
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor()
                .must(new EcElasticSearchQueryBuilder()
                    .term("filed", "")
                    .term("filed", "")
                    .addQueryBuilder(QueryBuilders.termQuery("", ""))
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


}
