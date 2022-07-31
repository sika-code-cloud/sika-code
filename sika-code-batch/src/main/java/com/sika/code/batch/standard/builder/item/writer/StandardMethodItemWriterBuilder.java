package com.sika.code.batch.standard.builder.item.writer;

import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.MethodWriterBean;
import com.sika.code.batch.standard.item.writer.MethodWriterSupport;
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
public class StandardMethodItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        MethodWriterBean methodWriterBean = (MethodWriterBean) itemWriterBean.buildBeanObj();
        // 获取标准的处理类，通过调用目标写入到数据库中
        return new MethodWriterSupport()
                .setWriterBean(methodWriterBean)
                .setDataBuilder(methodWriterBean.getBaseWriterDataBuilder());
    }

}
