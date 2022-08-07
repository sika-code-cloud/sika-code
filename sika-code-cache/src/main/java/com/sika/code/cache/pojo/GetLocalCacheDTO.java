package com.sika.code.cache.pojo;

import cn.hutool.core.lang.Assert;
import com.sika.code.cache.constant.CacheConstant;
import lombok.Data;

/**
 * <p>
 * 本地缓存数据传输对象
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 9:02
 */
@Data
public class GetLocalCacheDTO<T> extends GetCacheDTO<T> {
    /** 初始缓存大小 */
    private Integer initCacheSize;
    /** 最大的缓存大小 */
    private Integer maxCacheSize;
    /**
     * 缓存的类型
     */
    private String type;

    @Override
    public void build() {
        Assert.notEmpty(type, "缓存的类型不能为空");
        if (initCacheSize == null || initCacheSize <= 0) {
            initCacheSize = CacheConstant.INIT_CACHE_SIZE_DEFAULT;
        }
        if (maxCacheSize == null || maxCacheSize <= 0) {
            maxCacheSize =  CacheConstant.MAX_CACHE_SIZE_DEFAULT;
        }
        super.build();
    }
}
