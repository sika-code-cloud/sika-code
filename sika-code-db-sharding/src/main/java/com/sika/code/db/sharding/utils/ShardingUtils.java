package com.sika.code.db.sharding.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sika.code.db.sharding.algorithm.value.BaseValueAlgorithm;
import com.sika.code.db.sharding.constant.AlgorithmPropertiesConstants;
import com.sika.code.db.sharding.algorithm.value.temp.ShardingValueAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.infra.datanode.DataNode;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.apache.shardingsphere.infra.util.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.infra.util.spi.type.typed.TypedSPIRegistry;
import org.apache.shardingsphere.sharding.api.sharding.ShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Sharding工具类
 *
 * @author daiqi
 */
public final class ShardingUtils {

    public static final String SHARDING_VALUE_CONFIG_PREFIX = "{}";
    public static final String SHARDING_VALUE_CONFIG_SEPARATOR = ",";
    public static final String SHARDING_VALUE_CONFIG_KEY_VALUE_SEPARATOR = "=";
    private static final String DELIMITER = ",";
    private static final String NOT_NULL_MESSAGE_TEMPLATE = "Properties `%s` can not be null";
    /**
     * sharding-解析实际表前缀正则表达式 源代码写法为(\d+[\-_]){0,}(\d+$) 阿里检测后的写法为(\d+[\-_])*(\d+$)
     */
    private static final Pattern DATA_NODE_SUFFIX_PATTERN = Pattern.compile("(\\d+[\\-_])*(\\d+$)");
    private static final char DEFAULT_PADDING_CHAR = '0';

    private static final Map<String, BaseValueAlgorithm> ALGORITHM_NAME_TO_VALUE_ALGORITHM_OBJ = Maps.newConcurrentMap();

    static {
        ShardingSphereServiceLoader.register(ShardingValueAlgorithm.class);
    }

    private ShardingUtils() {
    }

    /**
     * 两次hash取模(对半取模)
     *
     * @return 取模后的值
     */
    public static int twiceHashMod(Comparable<?> shardingValue, int modNumber, Collection<Integer> availableTargetSequences) {
        int remainder = Math.abs(shardingValue.hashCode()) % modNumber;
        if (availableTargetSequences.contains(remainder)) {
            return remainder;
        }
        // means modNumber%2 == 1, odd number
        if ((modNumber & 1) == 1) {
            throw new RuntimeException("模数配置异常，取模后没有可以映射的表");
        }
        // means modNumber = modNumber / 2;
        modNumber >>= 1;
        remainder = remainder % modNumber;
        if (availableTargetSequences.contains(remainder)) {
            return remainder;
        }
        throw new RuntimeException("模数配置异常，取模后没有可以映射的表");
    }

    /**
     * 获取分片的描述信息，错误信息输出时使用
     *
     * @param availableTargetNames 可用数据节点
     * @param shardingValue        分片键
     * @return 分片描述信息
     */
    public static String getShardingInfoString(Collection<String> availableTargetNames, ShardingValue shardingValue) {
        String availableTargetName = String.join(ShardingUtils.DELIMITER, availableTargetNames);
        return "分片信息：" + shardingValue.toString() + "，数据节点：" + availableTargetName;
    }

    public static int getTableModNumber(Properties props) {
        String property = props.getProperty(AlgorithmPropertiesConstants.TABLE_MOD_NUMBER);
        Preconditions.checkNotNull(property, ShardingUtils.NOT_NULL_MESSAGE_TEMPLATE,
                AlgorithmPropertiesConstants.TABLE_MOD_NUMBER);
        return strToInt(property);
    }

    public static int getDatasourceModNumber(Properties props) {
        String property = props.getProperty(AlgorithmPropertiesConstants.DATASOURCE_MOD_NUMBER);
        Preconditions.checkNotNull(property, ShardingUtils.NOT_NULL_MESSAGE_TEMPLATE,
                AlgorithmPropertiesConstants.DATASOURCE_MOD_NUMBER);
        return strToInt(property);
    }

    public static List<Integer> getAllTableSequences(Properties props) {
        String property = props.getProperty(AlgorithmPropertiesConstants.ALL_TABLE_SEQUENCES);
        Preconditions.checkNotNull(property, ShardingUtils.NOT_NULL_MESSAGE_TEMPLATE,
                AlgorithmPropertiesConstants.ALL_TABLE_SEQUENCES);
        return new InlineExpressionParser(property).splitAndEvaluate().stream().map(ShardingUtils::strToInt)
                .collect(Collectors.toList());
    }

    public static Map<Integer, String> getTableToDatasourceMapping(Properties props) {
        String property = props.getProperty(AlgorithmPropertiesConstants.TABLE_TO_DATASOURCE_MAPPING);
        Preconditions.checkNotNull(property, ShardingUtils.NOT_NULL_MESSAGE_TEMPLATE,
                AlgorithmPropertiesConstants.TABLE_TO_DATASOURCE_MAPPING);
        List<String> dataNodes = new InlineExpressionParser(property).splitAndEvaluate();
        return dataNodes.stream().map(DataNode::new)
                .collect(Collectors.toMap(node -> strToInt(node.getTableName()), DataNode::getDataSourceName));
    }

    private static int strToInt(String str) {
        if (StringUtils.isNumeric(str)) {
            return Integer.parseInt(str);
        }
        throw new RuntimeException("配置不合法,非数字类型");
    }

    public static Map<String, String> getValueAlgorithm(Properties props) {
        // TODO 待构建列名对应值的算法类名
        return Maps.newHashMap();
    }

    public static Map<String, DataNode> getDataNodeMap(Properties props) {
        return null;
    }

    public static BaseValueAlgorithm getValueAlgorithm(String classNameStr) {
        if (StrUtil.isBlank(classNameStr)) {
            return null;
        }
        BaseValueAlgorithm valueAlgorithm = ALGORITHM_NAME_TO_VALUE_ALGORITHM_OBJ.get(classNameStr);
        if (valueAlgorithm != null) {
            return valueAlgorithm;
        }
        valueAlgorithm = ReflectUtil.newInstance(classNameStr);
        ALGORITHM_NAME_TO_VALUE_ALGORITHM_OBJ.put(classNameStr, valueAlgorithm);
        return valueAlgorithm;
    }


    public static Map<Comparable<?>, String> getHotCustomerDatasource(Properties props) {
        String hotCustomers = props.getProperty(AlgorithmPropertiesConstants.HOT_CUSTOMER_DATASOURCE);
        if (Strings.isNullOrEmpty(hotCustomers)) {
            return Maps.newHashMap();
        }
        List<String> hotCustomerList = new InlineExpressionParser(hotCustomers).splitAndEvaluate();
        return hotCustomerList.stream().map(DataNode::new)
                .collect(Collectors.toMap(DataNode::getTableName, DataNode::getDataSourceName));
    }

    public static List<String> getHotCustomerTable(Properties props) {
        String hotCustomers = props.getProperty(AlgorithmPropertiesConstants.HOT_CUSTOMER_TABLE);
        if (Strings.isNullOrEmpty(hotCustomers)) {
            return Lists.newArrayList();
        }
        return new InlineExpressionParser(hotCustomers).splitAndEvaluate();
    }

    public static DataNodeInfo getTableDataNode(Collection<String> availableTargetNames,
                                                ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        String tableName = availableTargetNames.iterator().next();
        String logicTable = complexKeysShardingValue.getLogicTableName();
        String prefix = tableName.startsWith(logicTable) ? logicTable + DATA_NODE_SUFFIX_PATTERN.matcher(
                tableName.substring(logicTable.length())).replaceAll("")
                : DATA_NODE_SUFFIX_PATTERN.matcher(tableName).replaceAll("");
        int suffixMinLength = availableTargetNames.stream().map(each -> each.length() - prefix.length())
                .min(Comparator.comparing(Integer::intValue)).orElse(1);
        return new DataNodeInfo(prefix, suffixMinLength, DEFAULT_PADDING_CHAR);
    }

    public static Map<String, ShardingValueAlgorithm> getShardingValueAlgorithm(Properties props) {
        String valueAlgorithmsProp = props.getProperty(AlgorithmPropertiesConstants.SHARDING_VALUE_ALGORITHM);
        Preconditions.checkNotNull(valueAlgorithmsProp, ShardingUtils.NOT_NULL_MESSAGE_TEMPLATE,
                AlgorithmPropertiesConstants.SHARDING_VALUE_ALGORITHM);

        Map<String, String> valueAlgorithmMap = valueAlgorithmsPropToMap(valueAlgorithmsProp);

        Map<String, ShardingValueAlgorithm> algorithmMap = new HashMap<>(10);
        for (Map.Entry<String, String> entry : valueAlgorithmMap.entrySet()) {
            Optional<ShardingValueAlgorithm> shardingValueAlgorithm =
                    TypedSPIRegistry.findRegisteredService(ShardingValueAlgorithm.class, entry.getValue());
            if (!shardingValueAlgorithm.isPresent()) {
                throw new RuntimeException(
                        "复合分片,分片键值的策略处理配置错误,无法找到具体处理类,algorithmType:" + entry.getValue());
            }
            algorithmMap.put(entry.getKey(), shardingValueAlgorithm.get());
        }
        return algorithmMap;
    }

    /**
     * 分片键值策略处理类配置解析
     *
     * @param valueAlgorithmsProp 分片键值策略配置
     * @return 分片键, 分片策略类类型
     */
    private static Map<String, String> valueAlgorithmsPropToMap(String valueAlgorithmsProp) {
        return Arrays.stream(
                        StringUtils.strip(valueAlgorithmsProp, SHARDING_VALUE_CONFIG_PREFIX).split(SHARDING_VALUE_CONFIG_SEPARATOR))
                .map(item -> item.split(SHARDING_VALUE_CONFIG_KEY_VALUE_SEPARATOR))
                .collect(Collectors.toMap(i -> i[0].trim(), i -> i[1]));
    }

    public static Map.Entry<String, Collection<Comparable<?>>> getComplexColumnAndValue(
            ComplexKeysShardingValue<Comparable<?>> complexKeysShardingValue) {
        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap =
                complexKeysShardingValue.getColumnNameAndShardingValuesMap();

        // 取第一个分片列名和值
        Optional<Map.Entry<String, Collection<Comparable<?>>>> columnAndValueOption =
                columnNameAndShardingValuesMap.entrySet().stream().findFirst();

        if (!columnAndValueOption.isPresent()) {
            throw new RuntimeException("复合分片键分片键值不存在");
        }

        Map.Entry<String, Collection<Comparable<?>>> columnAndValue = columnAndValueOption.get();
        if (columnAndValue.getValue().size() > 1) {
            throw new RuntimeException("不支持in查询");
        }

        return columnAndValue;
    }

}
