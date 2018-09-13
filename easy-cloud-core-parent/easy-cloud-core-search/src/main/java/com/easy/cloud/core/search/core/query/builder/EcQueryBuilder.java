package com.easy.cloud.core.search.core.query.builder;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.search.utils.CharUtil;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tudou
 * @version 1.0
 * @title: EcElasticSearchQueryBuilder
 * @description: ---
 * @date 2018/6/21 15:17
 */
class EcQueryBuilder implements EcQueryBuilderCriterion {

    private List<QueryBuilder> list = new ArrayList<>();

    public EcQueryBuilder addQueryBuilder(QueryBuilder queryBuilder) {
        if (queryBuilder != null) {
            list.add(queryBuilder);
        }
        return this;
    }

    /**
    * <p>
    * Term 查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:39
    * @param fieldName  字段名
    * @param value      值
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder term(String fieldName, Object value) {
        Assert.isTrue(EcStringUtils.isEmpty(fieldName), "fieldName is null or \"\"");
        if (value != null && !"".equals(value)) {
            list.add(QueryBuilders.termQuery(fieldName, value));
        }
        return this;
    }

    /**
    * <p>
    * Terms 查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:39
    * @param fieldName  字段名
    * @param values     集合值
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder terms(String fieldName, Collection<Object> values) {
        Assert.isTrue(EcStringUtils.isEmpty(fieldName), "fieldName is null or \"\"");
        if (values != null && values.size() != 0) {
            list.add(QueryBuilders.termsQuery(fieldName, values));
        }
        return this;
    }

    /**
    * <p>
    * fuzzy 查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:37
    * @param fieldName  字段名
    * @param value      值
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder fuzzy(String fieldName, Object value) {
        Assert.isTrue(EcStringUtils.isEmpty(fieldName), "fieldName is null or \"\"");
        if (value != null && !"".equals(value)) {
            list.add(QueryBuilders.fuzzyQuery(fieldName, value));
        }
        return this;
    }
    /**
    * <p>
    * 范围查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:29
    * @param fieldName  字段名
    * @param format     日期格式，如果不是日期过滤可以为null
    * @param from       开始时间
    * @param to         结束时间
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder range(String fieldName, String format, Object from, Object to) {
        return range(fieldName, format, from, to, true, false);
    }

    /**
     * <p>
     * 功能描述：进行范围查询（可以指定日期的格式）
     * </p >
     *
     * @param fieldName    字段名
     * @param format       日期格式，如果不是日期过滤可以为null
     * @param from         开始字符
     * @param to           结束字符
     * @param includeLower 是否包含上限
     * @param includeUpper 是否包含下限
     * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
     * @author tudou
     * @date 2018/8/14 10:24
     */
    public EcQueryBuilder range(String fieldName, String format, Object from, Object to, boolean includeLower, boolean includeUpper) {
        Assert.isTrue(EcStringUtils.isEmpty(fieldName), "fieldName is null or \"\"");
        if (from == null && to == null) {
            return this;
        }
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery(fieldName);
        if (EcStringUtils.isNotEmpty(format)) {
            queryBuilder.format(format);
        }
        if (from != null) {
            queryBuilder.from(from);
        }
        if (from != null) {
            queryBuilder.to(to);
        }
        list.add(queryBuilder.includeLower(includeLower).includeUpper(includeUpper));
        return this;
    }

    /**
    * <p>
    * 对全局进行queryString关键词查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:34
    * @param queryString    查询语句
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder queryString(String queryString) {
        if (queryString != null && !"".equals(queryString)) {
            list.add(QueryBuilders.queryStringQuery(queryString).defaultOperator(Operator.AND));
        }
        return this;
    }

    /**
    * <p>
    * 对fieldName字段进行queryString关键词查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:34
    * @param fieldName    查询字段
    * @param queryString  查询语句
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder queryString(String fieldName, String queryString) {
        Assert.isTrue(EcStringUtils.isEmpty(fieldName), "fieldName is null or \"\"");
        if (queryString != null && !"".equals(queryString)) {
            queryString = CharUtil.escapeExprSpecialWord(queryString);
            list.add(QueryBuilders.queryStringQuery(queryString).field(fieldName).defaultOperator(Operator.AND));
        }
        return this;
    }

    /**
    * <p>
    * 添加子级boolean查询
    * </p >
    * @author tudou
    * @date 2018/8/14 10:35
    * @param constructor  传入构造器
    * @return com.easy.cloud.core.search.core.query.builder.EcQueryBuilder
    */
    public EcQueryBuilder constructor(EcQueryBuilderConstructor constructor) {
        if (constructor != null) {
            list.add(constructor.listBuilders());
        }
        return this;
    }

    @Override
    public List<QueryBuilder> listBuilders() {
        return list;
    }
}