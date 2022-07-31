package com.sika.code.batch.standard.builder.item.reader;

import cn.hutool.core.util.ArrayUtil;
import com.sika.code.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.bean.common.ItemReaderBean;
import com.sika.code.batch.standard.bean.reader.FlatReaderBean;
import com.sika.code.batch.standard.config.JSONBeanWrapperFieldSetMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

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
public class StandardFlatItemReaderBuilder implements BaseItemReaderBuilder<Map<String, Object>> {
    @Override
    public ItemReader<Map<String, Object>> build(BatchBean batchBean) {
        ItemReaderBean<?> itemBean = batchBean.getItemReaderBean();
        FlatReaderBean readerBean = (FlatReaderBean) itemBean.buildBeanObj();
        // FlatFileItemReader是一个用来加载文件的itemReader
        FlatFileItemReader<Map<String, Object>> reader = new FlatFileItemReader<>();
        // 跳过第一行的标题
        reader.setLinesToSkip(readerBean.getLinesToSkip());
        // 设置csv的位置
        reader.setResource(new ClassPathResource(readerBean.getSource()));
        // 设置每一行的数据信息
        reader.setLineMapper(new DefaultLineMapper<Map<String, Object>>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(ArrayUtil.toArray(readerBean.getNames(), String.class));
                // 配置列于列之间的间隔符,会通过间隔符对每一行进行切分
                setDelimiter(readerBean.getDelimiter());
            }});

            // 设置要映射的实体类属性
            setFieldSetMapper(new JSONBeanWrapperFieldSetMapper());
        }});
        return reader;
    }
}
