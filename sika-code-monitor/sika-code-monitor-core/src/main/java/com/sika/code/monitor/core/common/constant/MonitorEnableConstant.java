package com.sika.code.monitor.core.common.constant;

/**
 * MonitorEnableConstant
 *
 * @author : daiqi
 * @date : 2023-09-16
 */
public interface MonitorEnableConstant {
    String DB_DRUID_CONNECTOR_POOL = "spring.monitor.connectorPool.db.druid.enabled";
    String DB_HIKARI_CONNECTOR_POOL = "spring.monitor.connectorPool.db.hikari.enabled";
    String DB_SHARDING_SPHERE_CONNECTOR_POOL = "spring.monitor.connectorPool.db.shardingSphere.enabled";

    String DB_MYBATIS_INVOKE = "spring.monitor.invoke.db.mybatis.enabled";
    String RPC_DUBBO_INVOKE = "spring.monitor.invoke.rpc.dubbo.enabled";

    String RPC_DUBBO_THREAD_POOL = "spring.monitor.threadPool.rpc.dubbo.enabled";
    String THREAD_POOL_DYNAMIC_TP = "spring.monitor.threadPool.dynamicTp.enabled";
    String THREAD_POOL_HIPPO_4J = "spring.monitor.threadPool.hippo4j.enabled";
    String THREAD_POOL_TOMCAT = "spring.monitor.threadPool.tomcat.enabled";
}
