package com.sika.code.batch.standard.builder.item.writer;

import com.sika.code.core.base.pojo.domain.factory.MetaSpringUtil;
import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.RedisWriterBean;
import com.sika.code.batch.standard.item.writer.RedisBatchItemWriterSupport;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 19:05
 */
@Data
public class StandardRedisBatchItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        RedisWriterBean redisWriterBean = (RedisWriterBean) itemWriterBean.buildBeanObj();
        // 获取标准的处理类，通过Redis写入到数据库中
        RedisBatchItemWriterSupport writer = new RedisBatchItemWriterSupport()
                .setDataBuilder(redisWriterBean.getBaseWriterDataBuilder())
                .setStringRedisTemplate(MetaSpringUtil.getBean(StringRedisTemplate.class))
                .setRedisWriterBean(redisWriterBean);
        return writer;
    }

}
