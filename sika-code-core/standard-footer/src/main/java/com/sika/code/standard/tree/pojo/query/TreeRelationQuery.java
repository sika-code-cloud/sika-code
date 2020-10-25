package com.sika.code.standard.tree.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * 描述：树关系查询类
 *
 * @author daiqi
 * @date 2018-12-22 13:03:40
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TreeRelationQuery extends BaseStandardQuery {
    /**
     * 祖节点id
     */
    private Long ancestorId;
    /**
     * 子孙节点id
     */
    private Long descendantId;

    private Set<Long> ancestorIds;
    private Set<Long> descendantIds;
}
