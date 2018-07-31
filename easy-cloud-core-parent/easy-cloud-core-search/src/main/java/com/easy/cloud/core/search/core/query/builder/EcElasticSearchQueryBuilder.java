package com.easy.cloud.core.search.core.query.builder;

import com.easy.cloud.core.search.constant.EcElasticSearchOperator;
import com.easy.cloud.core.search.utils.CharUtil;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Title: EcElasticSearchQueryBuilder
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
 */
public class EcElasticSearchQueryBuilder implements EcElasticSearchCriterion {

    private List<QueryBuilder> list = new ArrayList<>();

    public EcElasticSearchQueryBuilder addQueryBuilder(QueryBuilder queryBuilder) {
        list.add(queryBuilder);
        return this;
    }

    /**
     * 功能描述：Term 查询
     * @param field 字段名
     * @param value 值
     */
    public EcElasticSearchQueryBuilder term(String field, Object value) {
        if(value == null || "".equals(value)){
            return this;
        }
        list.add(new EcElasticSearchSimpleExpression(field, value, EcElasticSearchOperator.TERM).toBuilder());
        return this;
    }

    /**
     * 功能描述：Terms 查询
     * @param field 字段名
     * @param values 集合值
     */
    public EcElasticSearchQueryBuilder terms(String field, Collection<Object> values) {
        if(values == null || values.size()==0){
            return this;
        }
        list.add(new EcElasticSearchSimpleExpression(field, values).toBuilder());
        return this;
    }

    /**
     * 功能描述：fuzzy 查询
     * @param field 字段名
     * @param value 值
     */
    public EcElasticSearchQueryBuilder fuzzy(String field, Object value) {
        list.add(new EcElasticSearchSimpleExpression(field, value, EcElasticSearchOperator.FUZZY).toBuilder());
        return this;
    }

    /**
     * 功能描述：Range 查询
     * @param from 起始值
     * @param to 末尾值
     */
    public EcElasticSearchQueryBuilder range(String field, Object from, Object to) {
        if("".equals(from) || "".equals(to)){
            return this;
        }
        list.add(new EcElasticSearchSimpleExpression(field, from, to).toBuilder());
        return this;
    }

    /**
     *功能描述：对日期进行过滤查询
     * @param field
     * @param format
     * @param from
     * @param to
     * @return
     */
    public EcElasticSearchQueryBuilder range(String field, String format, Object from, Object to) {
        if("".equals(from) || "".equals(to)){
            return this;
        }
        list.add(new EcElasticSearchSimpleExpression(field, format, from, to).toBuilder());
        return this;
    }

    /**
     * 功能描述：queryString 查询
     * 关键词查询
     * @param queryString 查询语句
     */
    public EcElasticSearchQueryBuilder queryString(String queryString) {
        if(queryString == null || "".equals(queryString)){
            return this;
        }
        queryString = CharUtil.escapeExprSpecialWord(queryString);
        list.add(new EcElasticSearchSimpleExpression(queryString, EcElasticSearchOperator.QUERY_STRING).toBuilder());
        return this;
    }
    /**
     * 功能描述：queryString 查询
     * 关键词查询
     * @param queryString 查询语句
     */
    public EcElasticSearchQueryBuilder queryString(String field, String queryString) {
        if(queryString == null || "".equals(queryString)){
            return this;
        }
        queryString = CharUtil.escapeExprSpecialWord(queryString);
        list.add(new EcElasticSearchSimpleExpression(field, queryString, EcElasticSearchOperator.QUERY_STRING).toBuilder());
        return this;
    }

    /**
     * 功能描述：queryString 查询
     * 关键词查询
     * @param constructor 查询语句
     */
    public EcElasticSearchQueryBuilder constructor(ESQueryBuilderConstructor constructor) {
        if(constructor == null){
            return this;
        }
        list.add(new EcElasticSearchSimpleExpression(constructor, EcElasticSearchOperator.CONSTRUCTOR).toBuilder());
        return this;
    }

    @Override
    public List<QueryBuilder> listBuilders() {
        return list;
    }
}