package com.sika.code.batch.util;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.sika.code.basic.util.Assert;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
        delimitedLineTokenizer.setNames(names.toArray(new String [names.size()]));
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<T> wrapperFieldSetMapper = new BeanWrapperFieldSetMapper();
        wrapperFieldSetMapper.setTargetType(tClass);
        lineMapper.setFieldSetMapper(wrapperFieldSetMapper);
        return lineMapper;
    }

    public static void main(String[] args) {
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfExceptionOfType(Exception.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withWaitStrategy(WaitStrategies.fixedWait(2L, TimeUnit.SECONDS))
                .build();
        List<Integer> list = Lists.newArrayList();
        try {
            String name = retryer.call(() -> {
                System.out.println("运行第" + (list.size() + 1));
                if (list.size() < 2) {
                    list.add(1);
                    throw new RuntimeException("lalal-----" + list);
                }
                return "zhangsan";
            });
            System.out.println(name + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
