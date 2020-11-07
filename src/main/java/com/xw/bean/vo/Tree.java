package com.xw.bean.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {
    private final static String ROOT_NODE = "0";

    // ID
    private String id;
    // 父节点
    private String pid;
    // 子节点
    private List<Tree<T>> childNode = new ArrayList<>();
    ;
    // 是否根节点
    private Boolean root;

    public static <T> List<Tree<T>> buildTree(List<Tree<T>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        List<Tree<T>> tree = new ArrayList<>();
        for (Tree<T> t : nodes) {
            // 处理子节点
            String pid = t.getPid();
            if (pid == null || ROOT_NODE.equals(pid)) {
                t.setRoot(true);
                tree.add(t);
                continue;
            }

            // 处理叶子节点
            for (Tree<T> t1 : nodes) {
                String id = t1.getId();
                if (id != null && id.equals(pid)) {
                    t1.setRoot(false);
                    t1.getChildNode().add(t);
                }
            }
        }
        return tree;
    }

}
