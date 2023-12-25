package com.sika.code.demo.infrastructure.business.node.pojo.query;

import com.sika.code.core.base.pojo.query.PageQuery;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * <p>
 * 节点表 查询类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:16
 */
@Getter
@Setter
public class NodeQuery extends PageQuery<Long> {
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
    private List<Long> ids;
}
