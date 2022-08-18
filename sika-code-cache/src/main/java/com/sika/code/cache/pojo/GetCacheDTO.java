package com.sika.code.cache.pojo;

import com.sika.code.cache.constant.CacheConstant;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 16:39
 */
@Data
public class GetCacheDTO<T> extends CacheDTO {

    /**
     * 为空的时候的默认值
     */
    private T defaultValue;
    /**
     * 缓存有效时间-单位秒
     */
    private Long expire;

    @Override
    public void customerBuild() {
        if (expire == null || expire <= 0) {
            expire = CacheConstant.EXPIRE_DEFAULT;
        }
        super.customerBuild();
    }
}
