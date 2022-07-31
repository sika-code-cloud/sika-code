package com.sika.code.batch.core.item.writer;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.batch.item.file.MultiResourceItemWriter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/5/29 20:28
 */
@Data
public class MultiResourceItemWriterSupport<T> extends MultiResourceItemWriter<T> {

    private int currentResourceItemCountTemp = 0;

    private int itemCountLimitPerResource = Integer.MAX_VALUE;

    @Override
    public void write(List<? extends T> items) throws Exception {
        List<T> list = Lists.newArrayList();
        for (T t : items) {
            currentResourceItemCountTemp++;
            list.add(t);
            if (currentResourceItemCountTemp == itemCountLimitPerResource) {
                super.write(list);
                currentResourceItemCountTemp = 0;
                list.clear();
            }
        }
        if (CollUtil.isNotEmpty(list)) {
            super.write(list);
        }
    }

    @Override
    public void setItemCountLimitPerResource(int itemCountLimitPerResource) {
        this.itemCountLimitPerResource = itemCountLimitPerResource;
        super.setItemCountLimitPerResource(itemCountLimitPerResource);
    }
}
