package com.sika.code.common.tree.manager;

import com.sika.code.common.tree.pojo.TreeNode;
import com.sika.code.common.util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树的构建者
 *
 * @author daiqi
 * @create 2018-12-22 10:46
 */
public class TreeBuilder<Node extends TreeNode> {

    /**
     * 该节点下所有的子节点
     */
    private List<Node> children;
    /**
     * 根节点
     */
    private Node rootNode;

    public static <Node extends TreeNode> TreeBuilder builder(Node rootNode, List<Node> children) {
        return new TreeBuilder(rootNode, children);
    }

    private TreeBuilder(Node rootNode, List<Node> children) {
        this.rootNode = rootNode;
        this.children = children;
    }

    /**
     * 构建树
     */
    public Node build() {
        if (children != null && !children.isEmpty()) {
            Map<Object, Object> map = MapUtil.newHashMap();
            buildTree(this.rootNode, map);
        }
        return this.rootNode;
    }

    private void buildTree(Node beanTree, Map<Object, Object> map) {
        List<Node> childList = new ArrayList<>();
        children.stream()
                //map内不包含子节点的code
                .filter(c -> !map.containsKey(c.getKey()))
                //子节点的父id==根节点的code 继续循环
                .filter(c -> c.getParentKey().equals(beanTree.getKey()))
                .forEach(c -> {
                    //当前节点code和父节点id
                    map.put(c.getKey(), c.getParentKey());
                    //递归调用
                    buildTree(c, map);
                    childList.add(c);
                });
        beanTree.setChildren(childList);
    }

}
