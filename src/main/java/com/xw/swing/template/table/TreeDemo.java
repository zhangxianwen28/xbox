/*
 * Created by JFormDesigner on Fri Nov 13 15:36:00 CST 2020
 */

package com.xw.swing.template.table;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author Brainrain
 */
public class TreeDemo extends JFrame {
    private DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Mapping"));

    public TreeDemo() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        initComponents();
        this.tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    public static void main(String[] args) {
        TreeDemo treeDemo = new TreeDemo();
        treeDemo.setVisible(true);
    }

    public void addChildNode(ActionEvent e) {
        TreePath parentPath = tree1.getSelectionPath();
        assert parentPath != null;
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        addObject(parentNode, new Demo("1","mm"), true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,Object child,boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
        if (shouldBeVisible) {
            tree1.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        DefaultTreeModel old = this.treeModel;
        this.treeModel = treeModel;
        firePropertyChange("treeModel", old, treeModel);
    }

    private void removeNode(ActionEvent e) {
        TreePath parentPath = tree1.getSelectionPath();
        assert parentPath != null;
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        treeModel.removeNodeFromParent(parentNode);
    }

    private void updateNode(ActionEvent e) {
        TreePath parentPath = tree1.getSelectionPath();
        assert parentPath != null;
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        parentNode.setUserObject("888");
    }

    private void print(ActionEvent e) {
        java.util.List<Object> list = new ArrayList<>();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeModel.getRoot();
        Enumeration<DefaultMutableTreeNode> children = node.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode defaultMutableTreeNode = children.nextElement();
            list.add(defaultMutableTreeNode.getUserObject());
        }
        list.forEach(System.out::println);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- tree1 ----
            tree1.setPreferredSize(new Dimension(20, 400));
            tree1.setComponentPopupMenu(popupMenu1);
            scrollPane1.setViewportView(tree1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== popupMenu1 ========
        {

            //---- menuItem1 ----
            menuItem1.setText("\u6dfb\u52a0");
            menuItem1.addActionListener(e -> addChildNode(e));
            popupMenu1.add(menuItem1);

            //---- menuItem2 ----
            menuItem2.setText("\u5220\u9664");
            menuItem2.addActionListener(e -> removeNode(e));
            popupMenu1.add(menuItem2);

            //---- menuItem3 ----
            menuItem3.setText("\u4fee\u6539");
            menuItem3.addActionListener(e -> updateNode(e));
            popupMenu1.add(menuItem3);

            //---- menuItem4 ----
            menuItem4.setText("\u6253\u5370");
            menuItem4.addActionListener(e -> print(e));
            popupMenu1.add(menuItem4);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("treeModel"),
            tree1, BeanProperty.create("model")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
