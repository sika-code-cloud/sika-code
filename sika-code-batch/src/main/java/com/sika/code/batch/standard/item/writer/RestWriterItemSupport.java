package com.sika.code.batch.standard.item.writer;

import com.alibaba.fastjson.JSON;
import com.dtflys.forest.Forest;
import com.sika.code.batch.standard.bean.writer.RestWriterBean;
import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import com.sika.code.batch.standard.util.BatchUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RestWriterItemSupport implements ItemWriter<Map<String, Object>> {
    private BaseWriterDataBuilder<Map<String, Object>> dataBuilder;
    private RestWriterBean writerBean;

    @Override
    public void write(List<? extends Map<String, Object>> list) throws Exception {
        List<? extends Map<String, Object>> buildMap = dataBuilder.build(list);
        // 使用post请求
        Map<String, Object> retMap = Forest.post(writerBean.buildFullUrl())
                .contentTypeJson()
                .addBody(JSON.toJSONString(buildMap))
                .executeAsMap();
        // 获取写入结果
        // 校验响应的code值-不一致抛出异常
        BatchUtil.verifyResult(retMap, writerBean.getCodeName(), writerBean.getMsgName(), writerBean.getSuccessCodes());
    }

}
