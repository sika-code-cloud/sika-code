
package com.sika.code.db.sharding.core.algorithm.key.hint;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Preconditions;
import groovy.lang.Closure;
import groovy.util.Expando;
import lombok.Getter;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Hint inline sharding algorithm.
 */
public class HintInlinePlusShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {

    private static final String ALGORITHM_EXPRESSION_KEY = "algorithm-expression";

    private static final String DEFAULT_ALGORITHM_EXPRESSION = "${value}";

    private static final String HINT_INLINE_VALUE_PROPERTY_NAME = "value";

    @Getter
    private Properties props = new Properties();

    private String algorithmExpression;

    @Override
    public void init(final Properties props) {
        this.props = props;
        algorithmExpression = getAlgorithmExpression(props);
    }

    private String getAlgorithmExpression(final Properties props) {
        String algorithmExpression = props.getProperty(ALGORITHM_EXPRESSION_KEY, DEFAULT_ALGORITHM_EXPRESSION);
        Preconditions.checkNotNull(algorithmExpression, "Inline sharding algorithm expression can not be null.");
        return InlineExpressionParser.handlePlaceHolder(algorithmExpression.trim());
    }

    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final HintShardingValue<Comparable<?>> shardingValue) {
        if (shardingValue.getValues().isEmpty()) {
            return availableTargetNames;
        }
        // 直接解析value-已经存在直接返回
        Collection<String> targetNames = availableTargetNames.stream().filter(item -> shardingValue.getValues().contains(item)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(targetNames)) {
            return targetNames;
        }
        // 解析表达式
        return shardingValue.getValues().stream().map(this::doSharding).collect(Collectors.toList());
    }


    private String doSharding(final Comparable<?> shardingValue) {
        Closure<?> closure = createClosure();
        closure.setProperty(HINT_INLINE_VALUE_PROPERTY_NAME, shardingValue);
        return closure.call().toString();
    }

    private Closure<?> createClosure() {
        Closure<?> result = new InlineExpressionParser(algorithmExpression).evaluateClosure().rehydrate(new Expando(), null, null);
        result.setResolveStrategy(Closure.DELEGATE_ONLY);
        return result;
    }

    @Override
    public String getType() {
        return "HINT_INLINE_PLUS";
    }
}
