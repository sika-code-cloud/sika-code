package com.sika.code.batch.standard.item.writer;

import cn.hutool.core.util.ReflectUtil;
import com.sika.code.batch.standard.bean.writer.MethodWriterBean;
import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import com.sika.code.core.util.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  方法写入支持器
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/25 13:37
 */
@Data
@Accessors(chain = true)
public class MethodWriterItemSupport implements ItemWriter<Map<String, Object>> {
    private BaseWriterDataBuilder<Map<String, Object>> dataBuilder;
    private MethodWriterBean writerBean;

    @Override
    public void write(List<? extends Map<String, Object>> list) throws Exception {
        List<? extends Map<String, Object>> buildMap = dataBuilder.build(list);
        // 先转化
        List<?> waitWriterObjs = BeanUtil.toBeans(buildMap, Class.forName(writerBean.getParamsClassName()));
        // 获取实例对象
        Object obj = BeanUtil.getBean(writerBean.getClassName());
        // 通过反射调用指定方法
        ReflectUtil.invoke(obj, writerBean.getMethodName(), waitWriterObjs);
    }
}
