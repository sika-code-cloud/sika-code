package com.sika.code.batch.standard.builder.item.writer;

import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.MethodWriterBean;
import com.sika.code.batch.standard.bean.writer.RestWriterBean;
import com.sika.code.batch.standard.item.writer.MethodWriterSupport;
import com.sika.code.batch.standard.item.writer.RestWriterSupport;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;

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
public class StandardRestItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        RestWriterBean restWriterBean = (RestWriterBean) itemWriterBean.buildBeanObj();
        // 获取标准的处理类，通过调用目标写入到数据库中
        return new RestWriterSupport()
                .setWriterBean(restWriterBean)
                .setDataBuilder(restWriterBean.getBaseWriterDataBuilder());
    }

}
