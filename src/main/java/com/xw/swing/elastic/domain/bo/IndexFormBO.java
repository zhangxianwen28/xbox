package com.xw.swing.elastic.domain.bo;

import com.xw.swing.education.util.AbstractModelObject;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.util.learn.tree.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper=false)
public class IndexFormBO extends AbstractModelObject {
    private EsIndexVO esIndexFrom;
    private DefaultTreeModel model;
    private TempIndexDefinitionEntity tempIndexDefinitionEntity;


    private void buildTreeModel() {
        List<TempIndexDefinitionEntity> tempIndexDefinitionEntities = null;//getEsIndexFrom().getTempIndexDefinitionEntities();
        if (tempIndexDefinitionEntities == null) {
            return;
        }
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("mapping");
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(top);
        List<Tree<TempIndexDefinitionEntity>> trees = Tree.buildTree(tempIndexDefinitionEntities.stream().map(x -> {
            Tree<TempIndexDefinitionEntity> t = new Tree<>();
            t.setId(t.getId());
            t.setPid(t.getPid());
            return t;
        }).collect(Collectors.toList()));

        for (Tree<TempIndexDefinitionEntity> tree : trees) {
            if (tree.isTopNode()) {
                buildChildNode(tree, new DefaultMutableTreeNode(tree.getData()));
            }
        }
        setModel(defaultTreeModel);
    }

    private void buildChildNode(Tree<TempIndexDefinitionEntity> tree, DefaultMutableTreeNode treeNode) {
        if (!tree.isHasChild()) {
            return;
        }
        for (Tree<TempIndexDefinitionEntity> childNode : tree.getChildNodes()) {
            treeNode.add(new DefaultMutableTreeNode(childNode.getData()));
            buildChildNode(childNode, treeNode);
        }
    }

    private void unfoldTreeModel(DefaultTreeModel model) {
        List<TempIndexDefinitionEntity> indexDefinitionEntities = new ArrayList<>();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getRoot();
        Enumeration children = node.children();
        if (children.hasMoreElements()) {
            children.nextElement();
        }
        //getEsIndexFrom().setTempIndexDefinitionEntities(indexDefinitionEntities);
    }

    public EsIndexVO unfoldAndGetEsIndexVO() {
        unfoldTreeModel(this.model);
        return esIndexFrom;
    }
}
