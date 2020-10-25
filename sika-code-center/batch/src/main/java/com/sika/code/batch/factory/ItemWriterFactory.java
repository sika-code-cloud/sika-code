package com.sika.code.batch.factory;

import com.sika.code.common.spring.SpringUtil;
import org.springframework.batch.item.ItemWriter;

/**
 * 创建ItemWriter对象的工厂
 *
 * @author daiqi
 * @create 2019-10-03 22:53
 */
public class ItemWriterFactory {
    public static <T extends ItemWriter> T newItemWriter(Class<T> itemReaderClass) {
        return SpringUtil.getBean(itemReaderClass);
    }

    public static <T extends ItemWriter> T newItemWriter(String name, Class<T> itemReaderClass) {
        return SpringUtil.getBean(name, itemReaderClass);
    }

    public static <T extends ItemWriter> T newItemWriter(String name) {
        return (T) SpringUtil.getBean(name);
    }
}
