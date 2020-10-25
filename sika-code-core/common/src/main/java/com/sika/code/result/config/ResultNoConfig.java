package com.sika.code.result.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultNoConfig {
    /**
     * 编号前缀
     */
    private String prefix;
    /**
     * 编号后缀数量
     */
    private int suffixCount;
}