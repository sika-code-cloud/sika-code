package com.sika.code.cache.pojo;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import lombok.Data;

/**
 * <pre>
 *  缓存数据传输对象
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/8/5 18:12
 */
@Data
public class CacheDTO {

    /**
     * 缓存的key
     */
    private String key;

    /**
     * 执行的class
     */
    private Class<?> methodClass;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法的参数
     */
    private Object[] methodArgs;
    /**
     * 子KEY
     */
    private String subKey;
    /**
     * 是否已经执行方法-未执行为空
     */
    private Boolean execute;
    /**
     * 缓存结果
     */
    private Object cacheResult;
    /**
     * 是否开启缓存日志-默认不开启
     */
    private Boolean openLog;
    /**
     * 是否开启缓存 - 为true开启缓存-默认开启
     */
    private Boolean openCache;
    /**
     * 此次缓存的请求ID
     */
    private String requestId;
    /**
     * 参数类型列表
     */
    private Class<?>[] paramTypes;

    private boolean build = false;

    public final void build() {
        if (this.build) {
            return;
        }
        buildData();
        verifyData();
        customerBuild();
        build = true;
    }

    protected void customerBuild() {

    }

    protected void buildData() {
        if (this.openLog == null) {
            this.openLog = false;
        }
        if (this.openCache == null) {
            this.openCache = true;
        }
        if (CharSequenceUtil.isEmpty(this.requestId)) {
            this.requestId = IdUtil.objectId();
        }
    }

    protected void verifyData() {
        Assert.notEmpty(key, "缓存的key不能为空");
    }

}
