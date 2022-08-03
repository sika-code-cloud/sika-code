package com.sika.code.batch.standard.builder.item.reader;

import com.sika.code.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemReaderBean;
import com.sika.code.batch.standard.bean.reader.RestReaderBean;
import com.sika.code.batch.standard.item.reader.RestReaderItemSupport;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 18:00
 */
@Setter
public class StandardRestItemReaderBuilder implements BaseItemReaderBuilder<Map<String, Object>> {
    @Override
    public ItemReader<Map<String, Object>> build(BatchBean batchBean) {
        ItemReaderBean<?> itemBean = batchBean.getItemReaderBean();
        RestReaderBean readerBean = (RestReaderBean) itemBean.buildBeanObj();
        // RestReaderSupport是方法读取器
        RestReaderItemSupport itemSupport = new RestReaderItemSupport().setReaderBean(readerBean);
        itemSupport.setPageSize(readerBean.getPageSize());
        return itemSupport;
    }
}
