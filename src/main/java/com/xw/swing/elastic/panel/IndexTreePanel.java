/*
 * Created by JFormDesigner on Mon Nov 09 10:52:20 CST 2020
 */

package com.xw.swing.elastic.panel;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * @author Brainrain
 */
public class IndexTreePanel extends JPanel {

    public IndexTreePanel() {
        initComponents();
        initModel();
    }

    public static void main(String[] args) {
        IndexTreePanel indexTreePanel = new IndexTreePanel();
        indexTreePanel.setVisible(true);
    }

    public void initModel(){
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("软件部");
        node1.add(new DefaultMutableTreeNode("小花") {
        });
        node1.add(new DefaultMutableTreeNode("小虎"));
        node1.add(new DefaultMutableTreeNode("小龙"));

        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("销售部");
        node2.add(new DefaultMutableTreeNode("小叶"));
        node2.add(new DefaultMutableTreeNode("小雯"));
        node2.add(new DefaultMutableTreeNode("小夏"));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("mapping");

        top.add(new DefaultMutableTreeNode("总经理"));
        top.add(node1);
        top.add(node2);
        DefaultTreeModel treeModel = new DefaultTreeModel(top);
        this.tree1.setModel(treeModel);
        //this.tree1.updateUI();
    }

    /**
     * 选中树节点
     * @param e
     */
    private void tree1MouseClicked(MouseEvent e) {
        DefaultMutableTreeNode note = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
        Object userObject = note.getUserObject();
        JOptionPane.showMessageDialog(null, "note : "+note.toString());
        // 展示
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();

        //======== this ========
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- tree1 ----
            tree1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tree1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tree1);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTree tree1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
