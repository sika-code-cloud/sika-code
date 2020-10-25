package com.sika.code.standard.tree.entity;

import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daiqi
 * @create 2019-02-28 11:33
 */
@Data
@Slf4j
@EqualsAndHashCode
public class TreeRelationEntity extends BaseStandardEntity {
    /**
     * 祖节点id
     */
    private Long ancestorId;
    /**
     * 后台节点id
     */
    private Long descendantId;
}
