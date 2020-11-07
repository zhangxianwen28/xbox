package com.xw.bean.vo;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public abstract class Tree<T extends Tree<T>> {
    // ID
    private String id;
    // 父节点
    private String pid;
    // 子节点
    private List<T> childNode;
    // 是否根节点
    private Boolean root;

    public void buildTree(List<T> list) {
        if(list == null || list.isEmpty()){
            return;
        }
        Map<String, T> map = list.stream().collect(Collectors.toMap(Tree::getId, x -> x));

        List<T> tree = new ArrayList<>();
        for (T t : list) {
            // 根节点
            if(t.getRoot()){
                tree.add(t);
                T t1 = map.get(t.getPid());

            }
        }
    }

}
