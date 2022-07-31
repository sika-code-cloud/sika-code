package com.sika.code.batch.standard.config;

import com.google.common.collect.Maps;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 16:23
 */
public class JSONBeanWrapperFieldSetMapper implements FieldSetMapper<Map<String, Object>> {
    @Override
    public Map<String, Object> mapFieldSet(FieldSet fs) throws BindException {
        Map<String, Object> retMap = Maps.newLinkedHashMap();
        for (Map.Entry<Object, Object> entity : fs.getProperties().entrySet()) {
            retMap.put(entity.getKey().toString(), entity.getValue());
        }
        return retMap;
    }
}
