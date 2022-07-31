package com.sika.code.batch.standard.item.writer;

import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 支持类
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/18 20:33
 */
@Data
@Accessors(chain = true)
public class FlatFileItemWriterSupport extends FlatFileItemWriter<Map<String, Object>> {
    private BaseWriterDataBuilder<Map<String, Object>> dataBuilder;

    @Override
    public String doWrite(List<? extends Map<String, Object>> items) {
        return super.doWrite(dataBuilder.build(items));
    }

}
