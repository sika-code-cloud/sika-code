package com.easy.cloud.core.search.core.query.builder;

import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

/**
 * @Title: ScanDemo
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
 */
public interface EcQueryBuilderCriterion {
    /**
     * 构造List<QueryBuilder> 并返回
     * @return List<QueryBuilder>
     */
    List<QueryBuilder> listBuilders();
}
