package com.sika.code.batch.util;

import com.sika.code.basic.util.Assert;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import java.util.List;

/**
 * 批量工具类
 *
 * @author daiqi
 * @create 2019-09-12 22:36
 */
public class BatchUtil {

    public static String buildStatementId(Class clazz, String methodName) {
        Assert.verifyObjNull(clazz, "MapperClass不能为空");
        return buildStatementId(clazz.getName(), methodName);
    }

    public static String buildStatementId(String clazzName, String methodName) {
        Assert.verifyStrEmpty(clazzName, "MapperClass不能为空");
        return clazzName + "." + methodName;
    }

    /**
     * <p>
     * 创建lineMapper
     * </p>
     *
     * @param tClass
     * @param delimiter
     * @param names
     * @return org.springframework.batch.item.file.LineMapper<T>
     * @author sikadai
     * @date 2019/9/12 22:36
     */
    public static <T> LineMapper<T> buildLineMapper(Class<T> tClass, String delimiter, List<String> names) {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(delimiter);
        delimitedLineTokenizer.setNames(names.toArray(new String[names.size()]));
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<T> wrapperFieldSetMapper = new BeanWrapperFieldSetMapper();
        wrapperFieldSetMapper.setTargetType(tClass);
        lineMapper.setFieldSetMapper(wrapperFieldSetMapper);
        return lineMapper;
    }

}
