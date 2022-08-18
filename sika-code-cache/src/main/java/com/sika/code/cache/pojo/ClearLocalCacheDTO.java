package com.sika.code.cache.pojo;

import cn.hutool.core.lang.Assert;
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
public class ClearLocalCacheDTO extends ClearCacheDTO {
    /**
     * 缓存的类型
     */
    private String type;

    @Override
    protected void customerBuild() {
        Assert.notEmpty(type, "缓存的类型不能为空");
        super.customerBuild();
    }

}
