package com.sika.code.demo.infrastructure.db.business.node.po;

import com.sika.code.core.base.pojo.po.BasePO;
import com.sika.code.db.po.BasePoAuto;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * <p>
 * 节点表 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:18
 */
@Getter
@Setter
@TableName("sk_node")
public class NodePO extends BasePoAuto<Long> {
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
     * 节点编号
     */
    private String nodeNo;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 节点的Class全量限定名
     */
    private String nodeClazz;
    /**
     * 节点类型【10:普通节点，11:普通脚本节点，20:条件节点，21:条件脚本节点】
     */
    private Integer nodeType;
    /**
     * 节点脚本
     */
    private String nodeScript;
    /**
     * 租户主键ID
     */
    private Long tenantId;
    /**
     * 应用主键ID
     */
    private Long applicationId;
}
