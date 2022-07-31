package com.sika.code.batch.standard.builder.item.writer;

import com.sika.code.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.batch.core.factory.BatchFactory;
import com.sika.code.batch.core.item.writer.MultiResourceItemWriterSupport;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemWriterBean;
import com.sika.code.batch.standard.bean.writer.FlatWriterBean;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.Map;

/**
 * <p>
 * 多资源文件构建器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 9:13
 */
@Data
public class StandardMultiResourceItemWriterBuilder implements BaseItemWriterBuilder<Map<String, Object>> {

    @Override
    public ItemWriter<Map<String, Object>> build(BatchBean batchBean) {
        // 拆分写入多个文件
        MultiResourceItemWriter<Map<String, Object>> writer = new MultiResourceItemWriterSupport<>();
        ItemWriterBean<?> itemWriterBean = batchBean.getCurrentItemWriterBean();
        FlatWriterBean flatWriterBean = (FlatWriterBean) itemWriterBean.buildBeanObj();
        try {
            // 获取子写item
            ResourceAwareItemWriterItemStream<Map<String, Object>> writerItem = subWriterItem(flatWriterBean, batchBean);
            Resource source = new FileSystemResource(flatWriterBean.getSource());
            writer.setResource(source);
            writer.setItemCountLimitPerResource(flatWriterBean.getPerSourceNum());
            writer.setDelegate(writerItem);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return writer;
    }

    private ResourceAwareItemWriterItemStream<Map<String, Object>> subWriterItem(FlatWriterBean writerBean, BatchBean batchBean) {
        return (ResourceAwareItemWriterItemStream<Map<String, Object>>) BatchFactory.getItemWriter(batchBean, writerBean.getSubBuilderClassName());
    }
}
