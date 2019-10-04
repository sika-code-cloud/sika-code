package com.sika.code.batch.factory;

import com.sika.code.common.spring.SpringUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;

/**
 * 创建ItemReader对象的工厂类
 *
 * @author daiqi
 * @create 2019-10-03 22:52
 */
public class ItemProcessFactory {
    public static <T extends ItemProcessor> T newItemProcess(Class<T> itemReaderClass) {
        return SpringUtil.getBean(itemReaderClass);
    }

    public static <T extends ItemProcessor> T newItemProcess(String name, Class<T> itemReaderClass) {
        return SpringUtil.getBean(name, itemReaderClass);
    }

    public static <T extends ItemProcessor> T newItemProcess(String name) {
        return (T) SpringUtil.getBean(name);
    }

}
