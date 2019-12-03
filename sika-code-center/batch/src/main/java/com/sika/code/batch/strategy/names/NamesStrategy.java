package com.sika.code.batch.strategy.names;

import java.util.List;

/**
 * 名称映射策略
 *
 * @author daiqi
 * @create 2019-12-03 23:26
 */
public interface NamesStrategy<T extends NamesStrategy> {
    /**
     * <p>
     * 构建名称
     * </p>
     *
     * @return NamesStrategy
     * @author sikadai
     * @date 2019/12/3 23:32
     */
    T build();

    /**
     * <p>
     * 获取key名称
     * </p>
     *
     * @param
     * @return java.util.List<java.lang.String>
     * @author sikadai
     * @date 2019/12/3 23:58
     */
    List<String> getNames();
}
