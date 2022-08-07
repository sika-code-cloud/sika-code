package com.sika.code.cache.pojo;

import cn.hutool.core.lang.Assert;
import lombok.Data;

/**
 * <p>
 * Redis缓存数据传输对象
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 9:01
 */
@Data
public class GetRedisCacheDTO<T> extends GetCacheDTO<T> {

    /**
     * 随机有效时间 -单位秒
     */
    private Long randomExpire;
    private Class<T> valueClass;

    @Override
    public void build() {
        super.build();
        Assert.notNull(valueClass, "缓存中的Class对象不能为空");
        if (randomExpire == null) {
            randomExpire = getExpire() * 2;
        }
    }
}
