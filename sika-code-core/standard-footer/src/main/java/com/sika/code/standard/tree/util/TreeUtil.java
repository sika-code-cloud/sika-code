package com.sika.code.standard.tree.util;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.Assert;
import com.sika.code.common.tree.constant.TreeNodeType;
import com.sika.code.common.tree.manager.TreeBuilder;
import com.sika.code.common.tree.pojo.TreeNode;
import com.sika.code.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

/**
 * @author daiqi
 * @create 2019-02-19 10:03
 */
public class TreeUtil<KEY> {
    /**
     * 校验节点类型
     */
    public static void verifyTreeNodeType(Integer treeNodeType) {
        Assert.verifyObjNull(treeNodeType, "树节点类型:treeNodeType");
        if (TypeEnumInf.notExist(treeNodeType, TreeNodeType.class)) {
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, treeNodeType + ":节点类型");
        }
    }


    /**
     * 默认的根节点key --- 1L
     */
    private static final Serializable ROOT_KEY_DEFAULT = 1L;

    /**
     * <p>
     * 获取默认的根节点
     * </p>
     *
     * @return java.io.Serializable
     * @author daiqi
     * @date 2019/5/21 9:45
     */
    public static final Serializable getRootKeyDefault() {
        return ROOT_KEY_DEFAULT;
    }

    /**
     * <p>
     * 根据根节点的key和传入的treeNodes找到根节点
     * </p>
     *
     * @param rootKey   : 根节点的key
     * @param treeNodes : 树的节点列表
     * @return Node 根节点
     * @author daiqi
     * @date 2019/5/21 9:34
     */
    public static <Node extends TreeNode> Node findRootNode(Serializable rootKey, List<Node> treeNodes) {
        Assert.verifyObjNull(rootKey, "根节点key");
        if (CollUtil.isEmpty(treeNodes)) {
            return null;
        }
        Node rootNode = treeNodes.stream()
                .filter(treeNode -> rootKey.equals(treeNode.getKey()))
                .findFirst()
                .get();
        Assert.verifyDataNotExistent(rootNode, "根节点");
        return rootNode;
    }

    /**
     * <p>
     * 使用默认的根节点Key构建树
     * </p>
     *
     * @param treeNodes : 树节点列表
     * @author daiqi
     * @date 2019/5/21 9:42
     */
    public static <Node extends TreeNode> Node buildTreeUserDefaultRootKey(List<Node> treeNodes) {
        Node rootNode = findRootNode(ROOT_KEY_DEFAULT, treeNodes);
        TreeBuilder.builder(rootNode, treeNodes).build();
        return rootNode;
    }

    /**
     * <p>
     * 根据根节点的key和传入的treeNodes构建树
     * </p>
     *
     * @param rootKey   : 根节点
     * @param treeNodes : 树的节点列表
     * @author daiqi
     * @date 2019/5/21 9:36
     */
    public static <Node extends TreeNode> Node buildTreeByKey(Serializable rootKey, List<Node> treeNodes) {
        Node rootNode = findRootNode(rootKey, treeNodes);
        TreeBuilder.builder(rootNode, treeNodes).build();
        return rootNode;
    }

    /**
     * <p>
     * 根据根节点和传入的treeNodes构建树
     * </p>
     *
     * @param rootNode  : 根节点
     * @param treeNodes : 树节点列表
     * @author daiqi
     * @date 2019/5/21 9:39
     */
    public static <Node extends TreeNode> void buildTreeByNode(Node rootNode, List<Node> treeNodes) {
        TreeBuilder.builder(rootNode, treeNodes).build();
    }

}
