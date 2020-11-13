package com.xw.util.learn.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {
    private final static String ROOT_NODE = "0";

    private String id;
    private String pid;
    private T data;
    // 子节点
    private List<Tree<T>> childNodes = new ArrayList<>();
    private boolean hasChild;
    private boolean topNode;

    public Tree() {
    }

    public Tree(String id, String pid, T data) {
        this.id = id;
        this.pid = pid;
        this.data = data;
    }

    public static void main(String[] args) {
        List<Tree<String>> tree = new ArrayList<>();
        tree.add(new Tree<String>("1", "0", "fu"));
        tree.add(new Tree<String>("2", "1", "er"));
        tree.add(new Tree<String>("3", "2", "sun"));
        tree.add(new Tree<String>("4", "3", "sun"));
        List<Tree<String>> trees = buildTree(tree);
        trees.forEach(System.out::println);
    }



    public static <T> List<Tree<T>> buildTree(List<Tree<T>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        List<Tree<T>> tree = new ArrayList<>();
        for (Tree<T> t : nodes) {
            String pid = t.getPid();
            if (pid == null || ROOT_NODE.equals(pid)) {
                t.setTopNode(true);
                tree.add(t);
                continue;
            }
            for (Tree<T> t1 : nodes) {
                String id = t1.getId();
                if (id != null && id.equals(pid)) {
                    t1.setHasChild(true);
                    t1.getChildNodes().add(t);
                }
            }
        }
        return tree;
    }

}
