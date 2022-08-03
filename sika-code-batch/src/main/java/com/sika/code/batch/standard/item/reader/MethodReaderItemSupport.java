package com.sika.code.batch.standard.item.reader;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.sika.code.batch.standard.bean.reader.MethodReaderBean;
import com.sika.code.core.base.pojo.query.Page;
import com.sika.code.core.util.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.io.Serializable;
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
public class MethodReaderItemSupport extends AbstractPagingItemReader<Map<String, Object>> {
    private MethodReaderBean readerBean;
    private static final String INDEX_NAME = "id";

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }
        results.addAll(getRecords());
        // 设置索引值
        buildIndexValue(results);
    }

    protected Collection<? extends Map<String, Object>> getRecords() {
        Object obj = BeanUtil.getBean(readerBean.getClassName());
        Object returnValue = ReflectUtil.invoke(obj, readerBean.getMethodName(), readerBean.buildQuery());
        Collection<?> objects;
        if (returnValue instanceof List) {
            objects = BeanUtil.toBeans((List) returnValue, LinkedHashMap.class);
        } else if (returnValue instanceof Page) {
            objects = BeanUtil.toBeans(((Page) returnValue).getRecords(), LinkedHashMap.class);
        } else {
            throw new RuntimeException("暂不支持数据类型");
        }
        return (Collection<? extends Map<String, Object>>) objects;
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
