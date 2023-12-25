package com.sika.code.demo.infrastructure.business.chain.pojo.query;

import com.sika.code.demo.infrastructure.common.pojo.query.BaseBizQuery;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * <p>
 * 流程表 查询类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:12
 */
@Getter
@Setter
public class ChainQuery extends BaseBizQuery {
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
    private List<Long> ids;
}
