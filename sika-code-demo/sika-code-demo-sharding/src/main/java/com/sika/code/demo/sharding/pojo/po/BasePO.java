package com.sika.code.demo.sharding.pojo.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/12/10 9:22
 */
@Data
public class BasePO {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 更新日期
     */
    private LocalDateTime updateTime;
    private String remark;
}
