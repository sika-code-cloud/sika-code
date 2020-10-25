package com.sika.code.no.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 编号属性
 * </p>
 *
 * @author daiqi
 * @date 2019/7/5 21:05
 */
@Accessors
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoProperties {

    /**
     * 编号前缀
     */
    private String prefix;
    /**
     * 编号后缀数量
     */
    private int suffixCount;
}