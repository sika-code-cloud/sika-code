package com.easy.cloud.core.search.core.result;

import com.easy.cloud.core.common.reflection.utils.EcReflectionUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.InternalMultiBucketAggregation;
import org.elasticsearch.search.aggregations.InternalMultiBucketAggregation.InternalBucket;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.InternalTerms;
import org.elasticsearch.search.aggregations.metrics.InternalNumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 用来解析聚合结果
 * @author tudou
 * @Title: EcAggregationResultUtil
 * @Description: 解析聚合结果
 * @date 2018/8/14 11:13
 */
public class EcAggregationResultUtil {

    /**
    * <p>
    * 数据重组到bean中
    * </p >
    * @author tudou
    * @date 2018/8/14 11:31
    * @param searchResponse  聚合结果
    * @param t               包装类型
    * @param terms           Term聚合字段列表
    * @return java.util.List<T>
    */
    public static <T extends LogStat> List<T> termsResultBean(SearchResponse searchResponse, T t, String... terms) throws Exception {
        Map<String, Aggregation> map = searchResponse.getAggregations().getAsMap();
        List<Method> methods = new ArrayList<>();
        for (String name : terms) {
            Method method = t.getClass().getMethod(EcReflectionUtils.parSetName(name), String.class);
            methods.add(method);
        }
        Method method = t.getClass().getMethod(EcReflectionUtils.parSetName("count"), String.class);
        methods.add(method);
        return termsResultBean(map, methods, Arrays.asList(terms), t, true, new ArrayList<>());
    }

    /**
    * <p>
    * 简要说明
    * </p >
    * @author tudou
    * @date 2018/8/14 11:35
    * @param map      Aggregation对应的Map
    * @param methods  bean对应的methods
    * @param terms    Term聚合字段列表
    * @param t        每个
    * @param flag     判断是否是首次执行该方法，首次调用该方法flag需要标记为true
    * @param list     返回结果集合
    * @return java.util.List<T>
    */
    public static <T> List<T> termsResultBean(Map<String, Aggregation> map, List<Method> methods, List<String> terms, T t, boolean flag, List<T> list) throws Exception {
        Assert.isTrue(terms.size() == 0, "the size of terms is zero");
        MultiBucketsAggregation teamAgg = (MultiBucketsAggregation) map.get(terms.get(0));
        for (MultiBucketsAggregation.Bucket bucket : teamAgg.getBuckets()) {
            T newT;
            if (flag) {
                newT = (T) t.getClass().newInstance();
            } else {
                newT = (T) t.getClass().getConstructor(t.getClass()).newInstance(t);
            }
            methods.get(0).invoke(newT, bucket.getKeyAsString());
            //记录数
            if (terms.size() > 1) {
                //得到所有子聚合
                Map<String, Aggregation> subaggmap = bucket.getAggregations().asMap();
                termsResultBean(subaggmap, methods.subList(1, methods.size()), terms.subList(1, terms.size()), newT, false, list);
            } else if (terms.size() == 1) {
                String count = bucket.getDocCount() + "";
                methods.get(methods.size() - 1).invoke(newT, count);
                list.add(newT);
            }
        }
        return list;
    }

    /**
    * <p>
    * 根据names递归结果
    * </p >
    * @author tudou
    * @date 2018/8/14 11:33
    * @param searchResponse 聚合结果
    * @param terms          Term聚合字段列表
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    public static List<Map<String, Object>> termsResult(SearchResponse searchResponse, String... terms) {
        Map<String, Aggregation> map = searchResponse.getAggregations().getAsMap();
        Map<String, Object> resultMap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>();
        return termsResult(map, Arrays.asList(terms), resultMap, true, list);
    }

    /**
    * <p>
    * 简要说明
    * </p >
    * @author tudou
    * @date 2018/8/14 11:35
    * @param map        Aggregation对应的Map
    * @param terms      Term聚合字段列表
    * @param resultMap
    * @param flag       判断是否是首次执行该方法，首次调用该方法flag需要标记为true
    * @param list       返回结果集合
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    public static List<Map<String, Object>> termsResult(Map<String, Aggregation> map, List<String> terms, Map<String, Object> resultMap, boolean flag, List<Map<String, Object>> list) {
        Assert.isTrue(terms.size() == 0, "the size of terms is zero");
        MultiBucketsAggregation teamAgg = (MultiBucketsAggregation) map.get(terms.get(0));
        for (MultiBucketsAggregation.Bucket bucket : teamAgg.getBuckets()) {
            if (flag) {
                resultMap = new HashMap<>();
            }
            String team = bucket.getKeyAsString();
            resultMap.put(terms.get(0), team);
            if (terms.size() == 1) {
                bucket.getKeyAsString();
                long count = bucket.getDocCount();
                resultMap.put("docCount", count);
                resultMap.put("value", count);
                list.add(resultMap);
            } else if (terms.size() > 1) {
                Map<String, Aggregation> subaggmap = bucket.getAggregations().asMap();
                termsResult(subaggmap, terms.subList(1, terms.size()), resultMap, false, list);
            }
        }
        return list;
    }

    /**
    * <p>
    * 简要说明
    * </p >
    * @author tudou
    * @date 2018/8/14 11:34
    * @param searchResponse 聚合结果
    * @param terms          Term聚合字段列表
    * @param sumName        Sum聚合的字段
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    public static List<Map<String, Object>> termsSumResult(SearchResponse searchResponse, List<String> terms, String sumName) {
//        searchResponse.getAggregations().
        Map<String, Aggregation> map = searchResponse.getAggregations().getAsMap();
        HashMap resultMap = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>();
        return termsSumResult(map, terms, sumName, resultMap, true, list);
    }

    /**
     * <p>
     * 简要说明
     * </p >
     * @author tudou
     * @date 2018/8/13 11:49
     * @param map       Aggregation对应的Map
     * @param terms     Term聚合字段列表
     * @param sumName   Sum聚合的字段
     * @param resultMap 每个
     * @param flag      判断是否是首次执行该方法，首次调用该方法flag需要标记为true
     * @param list      返回结果集合
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public static List<Map<String, Object>> termsSumResult(Map<String, Aggregation> map, List<String> terms, String sumName, Map<String, Object> resultMap, boolean flag, List<Map<String, Object>> list) {
        Assert.isTrue(terms.size() == 0, "the size of terms is zero");
        InternalMultiBucketAggregation teamAgg = (InternalMultiBucketAggregation) map.get(terms.get(0));
        for (Object bucket : teamAgg.getBuckets()) {
            InternalBucket bucket2 = (InternalBucket)bucket;
            if (flag) {
                resultMap = new HashMap<>();
            }
            String team = bucket2.getKeyAsString();
            resultMap.put(terms.get(0), team);
            //记录数
            Map<String, Aggregation> subaggmap = bucket2.getAggregations().asMap();
            if (terms.size() == 1) {
                double total = ((InternalNumericMetricsAggregation.SingleValue) subaggmap.get(sumName)).value();
                resultMap.put(sumName, total);
                list.add(resultMap);

            } else if (terms.size() > 1) {
                termsSumResult(subaggmap, terms.subList(1, terms.size()), sumName, resultMap, false, list);
            }
        }
        return list;
    }
}
