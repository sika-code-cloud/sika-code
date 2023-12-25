package com.sika.code.db.sharding.core.algorithm.key.datetime;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.sika.code.db.sharding.core.constant.LowerModeEnum;
import lombok.Getter;
import org.apache.shardingsphere.sharding.algorithm.sharding.ShardingAutoTableAlgorithmUtil;
import org.apache.shardingsphere.sharding.api.sharding.ShardingAutoTableAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.exception.data.InvalidDatetimeFormatException;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * Auto interval sharding algorithm.
 */
public final class AutoIntervalPlusShardingAlgorithm
        implements StandardShardingAlgorithm<Comparable<?>>, ShardingAutoTableAlgorithm {

    private static final String DATE_TIME_LOWER_KEY = "datetime-lower";
    private static final String DATE_TIME_LOWER_MODE_KEY = "datetime-lower-mode";
    private static final String START_INDEX_KEY = "start-index";

    private static final String DATE_TIME_UPPER_KEY = "datetime-upper";

    private static final String SHARDING_SECONDS_KEY = "sharding-seconds";

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Integer lowerMode = LowerModeEnum.ORG.getType();

    @Getter
    private Properties props;

    private LocalDateTime dateTimeLower;

    private long shardingSeconds;

    @Getter
    private int autoTablesAmount;

    private int startIndex;

    @Override
    public void init(final Properties props) {
        this.props = props;
        dateTimeLower = getDateTime(props);
        shardingSeconds = getShardingSeconds(props);
        autoTablesAmount =
                (int) (Math.ceil((double) (parseDate(props.getProperty(DATE_TIME_UPPER_KEY)) / shardingSeconds)) + 2);
        lowerMode =
                Integer.parseInt(this.props.getProperty(DATE_TIME_LOWER_MODE_KEY, LowerModeEnum.ORG.getType().toString()));
        startIndex =
                Integer.parseInt(this.props.getProperty(START_INDEX_KEY, "0"));
    }

    private LocalDateTime getDateTime(final Properties props) {
        String value = props.getProperty(DATE_TIME_LOWER_KEY);
        Preconditions.checkNotNull(value, "%s cannot be null.", DATE_TIME_LOWER_KEY);
        try {
            return LocalDateTime.parse(value, DATE_TIME_FORMAT);
        } catch (final DateTimeParseException ex) {
            throw new InvalidDatetimeFormatException(DATE_TIME_LOWER_KEY, value, "yyyy-MM-dd HH:mm:ss");
        }
    }

    private long getShardingSeconds(final Properties props) {
        Preconditions.checkArgument(props.containsKey(SHARDING_SECONDS_KEY), "%s cannot be null.",
                SHARDING_SECONDS_KEY);
        return Long.parseLong(props.getProperty(SHARDING_SECONDS_KEY));
    }

    @Override
    public String doSharding(final Collection<String> availableTargetNames,
                             final PreciseShardingValue<Comparable<?>> shardingValue) {
        String tableNameSuffix = String.valueOf(doSharding(parseDate(shardingValue.getValue())));
        return ShardingAutoTableAlgorithmUtil.findMatchedTargetName(availableTargetNames, tableNameSuffix,
                shardingValue.getDataNodeInfo()).orElse(null);
    }

    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames,
                                         final RangeShardingValue<Comparable<?>> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        int firstPartition = getFirstPartition(shardingValue.getValueRange());
        int lastPartition = getLastPartition(shardingValue.getValueRange());
        for (int i = firstPartition; i <= lastPartition; i++) {
            String suffix = String.valueOf(i);
            ShardingAutoTableAlgorithmUtil.findMatchedTargetName(availableTargetNames, suffix,
                    shardingValue.getDataNodeInfo()).ifPresent(result::add);
        }
        return result;
    }

    private int doSharding(final long shardingValue) {
        String position = new DecimalFormat("0.00").format((double) shardingValue / shardingSeconds);
        return Math.min(Math.max(0, (int) Math.ceil(Double.parseDouble(position) + startIndex)), autoTablesAmount - 1 + startIndex);
    }

    private int getFirstPartition(final Range<Comparable<?>> valueRange) {
        return valueRange.hasLowerBound() ? doSharding(parseDate(valueRange.lowerEndpoint())) : 0;
    }

    private int getLastPartition(final Range<Comparable<?>> valueRange) {
        return valueRange.hasUpperBound() ? doSharding(parseDate(valueRange.upperEndpoint())) : autoTablesAmount - 1 + startIndex;
    }

    private long parseDate(final Comparable<?> shardingValue) {
        LocalDateTime dateValue;
        if (shardingValue instanceof LocalDateTime) {
            dateValue = (LocalDateTime) shardingValue;
        } else if (shardingValue instanceof LocalDate){
            dateValue = LocalDateTimeUtil.of((LocalDate)shardingValue);
        } else if (shardingValue instanceof Date){
            dateValue = LocalDateTimeUtil.of((Date)shardingValue);
        }
        else{
            dateValue = LocalDateTime.from(DATE_TIME_FORMAT.parse(shardingValue.toString(), new ParsePosition(0)));
        }
        LocalDateTime lower = dateTimeLower;
        if (!LowerModeEnum.ORG.getType().equals(lowerMode)) {
            lower = LocalDateTime.of(dateValue.getYear(), 1, 1, 0, 0, 0);
        }
        return Duration.between(lower, dateValue).toMillis() / 1000 ;
    }

    @Override
    public String getType() {
        return "AUTO_INTERVAL_PLUS";
    }
}
