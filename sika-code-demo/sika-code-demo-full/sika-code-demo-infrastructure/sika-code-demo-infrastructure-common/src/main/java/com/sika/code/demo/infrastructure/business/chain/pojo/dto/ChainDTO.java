package com.sika.code.demo.infrastructure.business.chain.pojo.dto;

import com.sika.code.demo.infrastructure.common.pojo.dto.BaseBizDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 流程表 数据传输类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:11
 */
@Getter
@Setter
public class ChainDTO extends BaseBizDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人标识
     */
    private String createBy;
    /**
     * 最后更新人标识
     */
    private String updateBy;
    /**
     * 流程名称
     */
    private String chainName;
    /**
     * 租户主键ID
     */
    private Long tenantId;
    /**
     * 应用主键ID
     */
    private Long applicationId;
    private Integer deleted;
}
