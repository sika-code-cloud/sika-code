package com.sika.code.batch.standard.builder.item.writer;

import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.FlatWriterBean;
import com.sika.code.batch.standard.item.writer.FlatFileItemWriterSupport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 18:30
 */
public class StandardFlatItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        FlatWriterBean flatWriterBean = (FlatWriterBean) itemWriterBean.buildBeanObj();
        // 通过Jdbc写入到数据库中
        FlatFileItemWriterSupport writer = new FlatFileItemWriterSupport()
                .setDataBuilder(flatWriterBean.getBaseWriterDataBuilder());
        try {
            DelimitedLineAggregator<Map<String, Object>> aggregator = new DelimitedLineAggregator<>();
            aggregator.setDelimiter(flatWriterBean.getDelimiter());
            
            Resource source = new FileSystemResource(flatWriterBean.getSource());
            writer.setResource(source);
            writer.setLineAggregator(aggregator);
            writer.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return writer;
    }
}
