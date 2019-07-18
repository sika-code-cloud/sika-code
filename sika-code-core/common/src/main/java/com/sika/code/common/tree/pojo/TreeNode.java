package com.sika.code.common.tree.pojo;

import java.util.List;

/**
 * 树的节点父接口
 *
 * @author daiqi
 * @create 2018-12-22 9:51
 */
public interface TreeNode<KEY, Node extends TreeNode<KEY, Node>> {
    /**
     * <p>
     * 获取key
     * </p>
     *
     * @return KEY
     * @author daiqi
     * @date 2019/5/21 9:14
     */
    KEY getKey();

    /**
     * <p>
     * 获取父Key
     * </p>
     *
     * @param
     * @return KEY
     * @author daiqi
     * @date 2019/5/21 9:14
     */
    KEY getParentKey();

    /**
     * <p>
     * 获取孩子列表
     * </p>
     *
     * @return java.manager.List<Node>
     * @author daiqi
     * @date 2019/5/21 9:15
     */
    List<Node> getChildren();

    /**
     * <p>
     * 设置孩子节点列表
     * </p>
     *
     * @param treeNodes : 节点列表
     * @return void
     * @author daiqi
     * @date 2019/5/21 9:15
     */
    void setChildren(List<Node> treeNodes);
}
