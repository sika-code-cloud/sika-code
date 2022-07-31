package com.sika.code.batch.standard.bean.writer;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.batch.standard.constant.RedisStoreTypeEnum;
import lombok.Data;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/25 14:11
 */
@Data
public class RedisWriterBean extends BaseWriterBean {
    private String redisKey;
    private Integer groupNum;
    private Long expireSecond;
    private String key;
    private Integer redisStoreType;
    private RedisStoreTypeEnum redisStoreTypeEnum;


    @Override
    public void doBuild() {
        super.doBuild();
        Assert.notBlank(redisKey, "redisKey不能为空");
        Assert.notBlank(key, "关键字的KEY不能为空");

        if (redisStoreType == null) {
            redisStoreType = RedisStoreTypeEnum.SET.getType();
        }
        redisStoreTypeEnum = BaseTypeEnum.find(redisStoreType, RedisStoreTypeEnum.class);

        this.redisKey = StrUtil.join(StrPool.COLON, this.redisKey, redisStoreTypeEnum.name());
    }
}
