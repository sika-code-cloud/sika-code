package com.sika.code.monitor.core.common.constant;

/**
 * MonitorEnableConstant
 *
 * @author : daiqi
 * @date : 2023-09-16
 */
public interface MonitorEnableConstant {

    String METRICS_COMMON_PREFIX = "management.metrics.sika.";

    String DB_DRUID_CONNECTOR_POOL = METRICS_COMMON_PREFIX + "connectorPool.db.druid.enabled";
    String DB_HIKARI_CONNECTOR_POOL = METRICS_COMMON_PREFIX + "connectorPool.db.hikari.enabled";
    String DB_SHARDING_SPHERE_CONNECTOR_POOL = METRICS_COMMON_PREFIX + "connectorPool.db.shardingSphere.enabled";

    String DB_MYBATIS_INVOKE = METRICS_COMMON_PREFIX + "invoke.db.mybatis.enabled";
    String RPC_DUBBO_INVOKE = METRICS_COMMON_PREFIX + "invoke.rpc.dubbo.enabled";

}
