package com.sika.code.standard.tree.pojo.dto;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 树形关系数据传输对象
 *
 * @author daiqi
 * @create 2019-02-28 11:30
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TreeRelationDTO extends BaseStandardDTO {
    /**
     * 祖节点id
     */
    protected Long ancestorId;
    /**
     * 子孙节点id
     */
    protected Long descendantId;

}
