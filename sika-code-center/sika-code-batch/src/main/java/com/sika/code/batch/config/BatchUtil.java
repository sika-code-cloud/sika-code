package com.sika.code.batch.config;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * 批量工具类
 *
 * @author daiqi
 * @create 2019-09-12 22:36
 */
public class BatchUtil {
    /**  
     * <p>
     * 创建lineMapper
     * </p>
     *   
     * @param tClass
     * @param delimite
     * @param names
     * @return org.springframework.batch.item.file.LineMapper<T>
     * @author sikadai 
     * @date 2019/9/12 22:36
     */  
    public static <T> LineMapper<T> lineMapper(Class<T> tClass, String delimite, String[] names) {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(delimite);
        delimitedLineTokenizer.setNames(names);
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<T> wrapperFieldSetMapper = new BeanWrapperFieldSetMapper();
        wrapperFieldSetMapper.setTargetType(tClass);
        lineMapper.setFieldSetMapper(wrapperFieldSetMapper);
        return lineMapper;
    }
}
