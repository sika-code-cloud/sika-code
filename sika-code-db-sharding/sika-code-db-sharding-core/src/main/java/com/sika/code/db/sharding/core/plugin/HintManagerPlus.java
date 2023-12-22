package com.sika.code.db.sharding.core.plugin;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.shardingsphere.infra.hint.HintManager;

import java.io.Closeable;
import java.util.Collection;

/**
 * @author daiqi
 * @create 2023-12-12 22:36
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HintManagerPlus implements Closeable {

    private static final ThreadLocal<LogicNameAndShardingValue> ATTR_SHARDING_VALUE_HOLDER = new ThreadLocal<>();
    private HintManager hintManager;

    /**
     * Get a new instance for {@code HintManager}.
     *
     * @return {@code HintManager} instance
     */
    public static HintManagerPlus getInstancePlus() {
        HintManagerPlus result = new HintManagerPlus();
        ATTR_SHARDING_VALUE_HOLDER.set(new LogicNameAndShardingValue());
        return result;
    }

    public static HintManagerPlus getInstance() {
        HintManagerPlus result = getInstancePlus();
        result.hintManager = HintManager.getInstance();
        return result;
    }

    public void putShardingValue(String logicTableName, String column, Comparable<?> shardingValue) {
        Preconditions.checkState(StrUtil.isNotBlank(logicTableName), "logicTableName is blank pleas set value.");
        Preconditions.checkState(StrUtil.isNotBlank(column), "column is blank pleas set value.");
        Preconditions.checkState(ObjectUtil.isNotEmpty(shardingValue), "shardingValue is blank pleas set value.");
        ATTR_SHARDING_VALUE_HOLDER.get().setColumn(column).setShardingValue(shardingValue)
            .setLogicTableName(logicTableName);
    }

    public static String getLogicName() {
        return getColumnShardingValue() == null ? null : getColumnShardingValue().getLogicTableName();
    }

    public static LogicNameAndShardingValue getColumnShardingValue() {
        if (ATTR_SHARDING_VALUE_HOLDER.get() == null) {
            return null;
        }
        return ATTR_SHARDING_VALUE_HOLDER.get();
    }

    public static Comparable<?> getShardingValue() {
        return getColumnShardingValue() == null ? null : getColumnShardingValue().getShardingValue();
    }
    public static String getColumnName() {
        return getColumnShardingValue() == null ? null : getColumnShardingValue().getColumn();
    }

    /**
     * Set sharding value for database sharding only.
     *
     * <p>The sharding operator is {@code =}</p>
     *
     * @param value sharding value
     */
    public void setDatabaseShardingValue(final Comparable<?> value) {
        hintManager.setDatabaseShardingValue(value);
    }

    /**
     * Add sharding value for database.
     *
     * <p>The sharding operator is {@code =}</p>
     *
     * @param logicTable logic table name
     * @param value      sharding value
     */
    public void addDatabaseShardingValue(final String logicTable, final Comparable<?> value) {
        hintManager.addDatabaseShardingValue(logicTable, value);
    }

    /**
     * Add sharding value for table.
     *
     * <p>The sharding operator is {@code =}</p>
     *
     * @param logicTable logic table name
     * @param value      sharding value
     */
    public void addTableShardingValue(final String logicTable, final Comparable<?> value) {
        hintManager.addTableShardingValue(logicTable, value);
    }

    /**
     * Get database sharding values.
     *
     * @return database sharding values
     */
    public static Collection<Comparable<?>> getDatabaseShardingValues() {
        return getDatabaseShardingValues("");
    }

    /**
     * Get database sharding values.
     *
     * @param logicTable logic table
     * @return database sharding values
     */
    public static Collection<Comparable<?>> getDatabaseShardingValues(final String logicTable) {
        return HintManager.getDatabaseShardingValues(logicTable);
    }

    /**
     * Get table sharding values.
     *
     * @param logicTable logic table name
     * @return table sharding values
     */
    public static Collection<Comparable<?>> getTableShardingValues(final String logicTable) {
        return HintManager.getTableShardingValues(logicTable);
    }

    /**
     * Judge whether database sharding only.
     *
     * @return database sharding or not
     */
    public static boolean isDatabaseShardingOnly() {
        return HintManager.isDatabaseShardingOnly();
    }

    /**
     * Set database operation force route to write database only.
     */
    public void setWriteRouteOnly() {
        hintManager.setWriteRouteOnly();
    }

    /**
     * Set database routing to be automatic.
     */
    public void setReadwriteSplittingAuto() {
        hintManager.setReadwriteSplittingAuto();
    }

    /**
     * Judge whether route to write database only or not.
     *
     * @return route to write database only or not
     */
    public static boolean isWriteRouteOnly() {
        return HintManager.isWriteRouteOnly();
    }

    /**
     * Clear thread local for hint manager.
     */
    public static void clear() {
        HintManager.clear();
        ATTR_SHARDING_VALUE_HOLDER.remove();
    }

    /**
     * Clear sharding values.
     */
    public void clearShardingValues() {
        hintManager.clearShardingValues();
    }

    /**
     * Judge whether hint manager instantiated or not.
     *
     * @return whether hint manager instantiated or not
     */
    public static boolean isInstantiated() {
        return HintManager.isInstantiated();
    }

    @Override
    public void close() {
        clear();
    }

    @Data
    @Accessors(chain = true)
    public static class LogicNameAndShardingValue {
        private String logicTableName;
        private String column;
        private Comparable<?> shardingValue;
    }
}
