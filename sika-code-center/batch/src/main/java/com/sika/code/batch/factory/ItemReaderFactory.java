package com.sika.code.batch.factory;

import com.sika.code.common.spring.SpringUtil;
import org.springframework.batch.item.ItemReader;

/**
 * 创建ItemReader对象的工厂类
 *
 * @author daiqi
 * @create 2019-10-03 22:52
 */
public class ItemReaderFactory {
    public static <T extends ItemReader> T newItemReader(Class<T> itemReaderClass) {
        return SpringUtil.getBean(itemReaderClass);
    }

    public static <T extends ItemReader> T newItemReader(String name, Class<T> itemReaderClass) {
        return SpringUtil.getBean(name, itemReaderClass);
    }

    public static <T extends ItemReader> T newItemReader(String name) {
        return (T) SpringUtil.getBean(name);
    }

}
