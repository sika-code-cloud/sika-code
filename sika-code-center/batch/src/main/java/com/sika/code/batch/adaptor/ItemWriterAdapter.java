package com.sika.code.batch.adaptor;

import com.google.common.collect.Lists;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.factory.BeanFactory;
import com.sika.code.common.spring.SpringUtil;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.AbstractMethodInvokingDelegator;

import java.util.List;

/**
 * 自定义Writer适配器
 *
 * @author daiqi
 * @create 2020-01-12 23:16
 */
@Data
public class ItemWriterAdapter<T> extends AbstractMethodInvokingDelegator implements ItemWriter<T> {

    private Class<?> targetObjectClass;

    @Override
    public void write(List<? extends T> items) throws Exception {
        List<Object> args = Lists.newArrayList();
        args.add(items);
        super.invokeDelegateMethodWithArguments(args.toArray());
    }

    public ItemWriterAdapter build() {
        if (BaseUtil.isNotNull(this.targetObjectClass)) {
            setTargetObject(getTargetObj(this.targetObjectClass));
        }
        return this;
    }

    private Object getTargetObj(Class<?> targetObjectClass) {
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

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
