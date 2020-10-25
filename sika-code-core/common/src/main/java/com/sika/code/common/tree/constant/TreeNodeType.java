package com.sika.code.common.tree.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiqi
 * @create 2019-02-20 10:40
 */
@Getter
@AllArgsConstructor
public enum TreeNodeType implements TypeEnumInf {
    /**
     * 书节点类型
     */
    ROOT(1, "根节点"),
    COMMON(2, "普通节点"),
    LEAF(3, "叶子节点");

    private Integer type;
    private String desc;

    public static boolean isRootNode(Integer treeNodeType) {
        return BaseUtil.equals(ROOT.getType(), treeNodeType);
    }

    public static boolean isCommonNode(Integer treeNodeType) {
        return BaseUtil.equals(COMMON.getType(), treeNodeType);
    }

    public static boolean isLeafNode(Integer treeNodeType) {
        return BaseUtil.equals(LEAF.getType(), treeNodeType);
    }
}
