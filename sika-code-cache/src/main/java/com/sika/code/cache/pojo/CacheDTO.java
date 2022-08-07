package com.sika.code.cache.pojo;

import cn.hutool.core.lang.Assert;
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

    public void build() {
        verifyData();
    }

    protected void verifyData() {
        Assert.notEmpty(key, "缓存的key不能为空");
    }

}
