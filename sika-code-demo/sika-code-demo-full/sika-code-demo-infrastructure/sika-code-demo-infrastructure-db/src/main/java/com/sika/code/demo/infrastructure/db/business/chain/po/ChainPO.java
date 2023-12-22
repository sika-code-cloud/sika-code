package com.sika.code.demo.infrastructure.db.business.chain.po;

import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * <p>
 * 流程表 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:13
 */
@Getter
@Setter
@TableName("lf_chain")
public class ChainPO extends BaseBizPO {
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
