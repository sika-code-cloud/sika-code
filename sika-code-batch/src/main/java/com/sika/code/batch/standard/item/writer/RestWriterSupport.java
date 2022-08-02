package com.sika.code.batch.standard.item.writer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dtflys.forest.Forest;
import com.sika.code.batch.standard.bean.writer.RestWriterBean;
import com.sika.code.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.LinkedHashMap;
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
public class RestWriterSupport implements ItemWriter<Map<String, Object>> {
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
        verifyResult(retMap);
    }

    protected void verifyResult(Map<String, Object> objectMap) {
        Assert.notNull(objectMap, "响应对象不能为空");
        if (CharSequenceUtil.isBlank(writerBean.getCodeName())) {
            return;
        }
        if (CollUtil.isEmpty(writerBean.getSuccessCodes())) {
            log.info("没有配置成功响应编码集合");
            return;
        }
        String code = MapUtil.getStr(objectMap, writerBean.getCodeName());
        String msg = MapUtil.getStr(objectMap, writerBean.getMsgName());
        if (CharSequenceUtil.isBlank(code)) {
            throw new RuntimeException(CharSequenceUtil.format("codeName【{}】对应的响应码为空", writerBean.getCodeName()));
        }
        if (!writerBean.getSuccessCodes().contains(code)) {
            throw new RuntimeException(CharSequenceUtil.format("codeName【{}】对应的响应码【{}】与配置的成功编码列表【{}】不匹配，异常消息为【{}】"
                    , writerBean.getCodeName(), code, JSON.toJSONString(writerBean.getSuccessCodes()), msg));
        }
    }
}
