package com.easy.cloud.core.search.core.query.builder;

import com.easy.cloud.core.search.constant.EcElasticSearchOperator;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Collection;

/**
 * @Title: EcElasticSearchSimpleExpression
 * @Description:
 * @Author tudou
 * @Date 2018/6/21 15:17
 * @Version 1.0
 */
public class EcElasticSearchSimpleExpression {
    /**
     * 属性名
     */
    private String fieldName;
    /**
     * 对应值
     */
    private Object value;
    /**
     * 对应值
     */
    private Collection<Object> values;
    /**
     * 计算符
     */
    private EcElasticSearchOperator operator;
    private String format;
    private Object from;
    private Object to;

    protected EcElasticSearchSimpleExpression(String fieldName, Object value, EcElasticSearchOperator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    protected EcElasticSearchSimpleExpression(Object value, EcElasticSearchOperator operator) {
        this.value = value;
        this.operator = operator;
    }

    protected EcElasticSearchSimpleExpression(String fieldName, Collection<Object> values) {
        this.fieldName = fieldName;
        this.values = values;
        this.operator = EcElasticSearchOperator.TERMS;
    }

    protected EcElasticSearchSimpleExpression(String fieldName, Object from, Object to) {
        this.fieldName = fieldName;
        this.from = from;
        this.to = to;
        this.operator = EcElasticSearchOperator.RANGE;
    }
    protected EcElasticSearchSimpleExpression(String fieldName, String format, Object from, Object to) {
        this.fieldName = fieldName;
        this.format = format;
        this.from = from;
        this.to = to;
        this.operator = EcElasticSearchOperator.RANGEDATE;
    }

    public QueryBuilder toBuilder() {
        QueryBuilder qb = null;
//        QueryBuilders.boolQuery().must()
        switch (operator) {
            case TERM:
                qb = QueryBuilders.termQuery(fieldName, value);
                break;
            case TERMS:
                qb = QueryBuilders.termsQuery(fieldName, values);
                break;
            case RANGE:
                qb = QueryBuilders.rangeQuery(fieldName).from(from).to(to).includeLower(true).includeUpper(true);
                break;
            case RANGEDATE:
                qb = QueryBuilders.rangeQuery(fieldName).format(format).from(from).to(to).includeLower(true).includeUpper(true);
                break;
            case FUZZY:
                qb = QueryBuilders.fuzzyQuery(fieldName, value);
//                QueryBuilders.
                break;
            case CONSTRUCTOR:
                if(value instanceof ESQueryBuilderConstructor){
                    ESQueryBuilderConstructor constructor = (ESQueryBuilderConstructor)value;
                    qb = constructor.listBuilders();
                }
                break;
            case QUERY_STRING:
                if(fieldName != null && !"".equals(fieldName)){
                    qb = QueryBuilders.queryStringQuery(value.toString()).field(fieldName).defaultOperator(Operator.AND);
                }else{
                    qb = QueryBuilders.queryStringQuery(value.toString()).defaultOperator(Operator.AND);
                }
        }
        return qb;
    }
}
