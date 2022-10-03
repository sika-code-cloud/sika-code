package com.sika.code.batch.standard.item.reader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.dtflys.forest.Forest;
import com.sika.code.batch.standard.bean.reader.RestReaderBean;
import com.sika.code.batch.standard.util.BatchUtil;
import com.sika.code.core.base.pojo.query.Page;
import com.sika.code.core.util.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *  方法读取支持器
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/25 13:37
 */
@Data
@Accessors(chain = true)
@Slf4j
public class RestReaderItemSupport extends AbstractPagingItemReader<Map<String, Object>> {
    private RestReaderBean readerBean;
    private static final String INDEX_NAME = "id";

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = Lists.newArrayList();
        } else {
            results.clear();
        }
        results.addAll(getRecords());
        // 设置索引值
        buildIndexValue(results);
    }

    protected Collection<? extends Map<String, Object>> getRecords() {
        // 使用post请求
        Map<String, Object> retObj = Forest.post(readerBean.buildFullUrl())
                .contentTypeJson()
                .addBody(JSON.toJSONString(readerBean.buildQuery()))
                .executeAsMap();
        // 校验状态码
        BatchUtil.verifyResult(retObj, readerBean.getCodeName(), readerBean.getMsgName(), readerBean.getSuccessCodes());
        return (Collection<? extends Map<String, Object>>) retObj.get(readerBean.getDataName());
    }

    protected void buildIndexValue(List<? extends Map<String, Object>> list) {
        String indexName = readerBean.getIndexName();
        if (CharSequenceUtil.isBlank(indexName)) {
            indexName = INDEX_NAME;
        }
        if (CollUtil.isEmpty(list)) {
            return;
        }
        readerBean.setStartIndex(MapUtil.getLong(CollUtil.getLast(list), indexName));
    }

    @Override
    protected void doJumpToPage(int i) {

    }
}
