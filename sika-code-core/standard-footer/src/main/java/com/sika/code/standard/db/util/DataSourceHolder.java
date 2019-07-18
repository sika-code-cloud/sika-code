package com.sika.code.standard.db.util;

/**
 * <p>
 * 用于数据源切换
 * </p>
 *
 * @author daiqi
 * @date 2018/11/29 18:00
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceKey> dataSourceKey = new ThreadLocal<>();

    public static DataSourceKey getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void setDataSourceKey(DataSourceKey type) {
        dataSourceKey.set(type);
    }

    public static void clearDataSourceKey() {
        dataSourceKey.remove();
    }


}