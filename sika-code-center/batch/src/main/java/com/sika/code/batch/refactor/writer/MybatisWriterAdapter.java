package com.sika.code.batch.refactor.writer;

import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.factory.BeanFactory;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.common.util.ReflectionUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.item.ItemWriter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author daiqi
 * @create 2020-01-09 23:13
 */
@Data
@Accessors(chain = true)
public class MybatisWriterAdapter<T> implements ItemWriter<T> {
    private Class<?> targetObjectClass;
    private String targetMethod;
    private Class<?>[] paramTypes;

    @Override
    public void write(List<? extends T> items) throws Exception {
        Method method = ReflectionUtil.getMethod(targetObjectClass, targetMethod, paramTypes);
        ReflectionUtil.invoke(getTargetObj(), method, items);
    }

    private Object getTargetObj() {
        Object targetObj = null;
        try {
            targetObj = SpringUtil.getBean(targetObjectClass);
        } catch (Exception e) {
            e.getMessage();
        }
        if (BaseUtil.isNull(targetObj)) {
            targetObj = BeanFactory.newInstance(targetObjectClass);
        }
        return targetObj;
    }

    public MybatisWriterAdapter<T> build() {
        Assert.verifyObjNull(paramTypes);
        return this;
    }
}
