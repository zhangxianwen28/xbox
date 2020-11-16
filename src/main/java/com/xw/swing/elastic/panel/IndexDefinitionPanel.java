/*
 * Created by JFormDesigner on Mon Nov 09 13:04:00 CST 2020
 */

package com.xw.swing.elastic.panel;

import java.awt.event.*;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.swing.elastic.domain.vo.IndexDefVO;
import com.xw.util.learn.tree.Tree;
import com.xw.util.other.IDGenerator;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.*;

/**
 * @author Brainrain
 */
public class IndexDefinitionPanel extends JPanel {

    private EsIndexVO esIndexFrom;
    private DefaultTreeModel treeModel;
    private List<String> details = ObservableCollections.observableList(new ArrayList<>());

    public IndexDefinitionPanel() {

        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Mapping"));

        initComponents();
        this.tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    /**
     * 添加子节点
     *
     * @param e
     */
    public void addChildNode(ActionEvent e) {
        IndexDefinitionFrom definitionFrom = new IndexDefinitionFrom();
        IndexDefVO indexDef = new IndexDefVO();
        indexDef.setId(String.valueOf(IDGenerator.getId()));

        DefaultMutableTreeNode note = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
        Object userObject = note.getUserObject();
        if (userObject instanceof IndexDefVO) {
            IndexDefVO indexDefVO = (IndexDefVO) userObject;
            if (!("object".equals(indexDefVO.getFieldType()) || "multi-fields".equals(indexDefVO.getFieldType()))) {
                JOptionPane.showMessageDialog(null, "父节点非对象或多字段属性，不能增加子节点");
                return;
            }
            indexDef.setPid(indexDefVO.getId());
        } else {
            indexDef.setPid("0");
        }

        definitionFrom.setIndexDef(indexDef);

        JOptionPane optionPane = new JOptionPane(definitionFrom, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, "新增");
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return;
        }
        IndexDefVO indexDef1 = definitionFrom.getIndexDef();
        addObject(note, indexDef1, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
        if (shouldBeVisible) {
            tree1.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    private void tree1MouseClicked(MouseEvent e) {
        DefaultMutableTreeNode note = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
        if (note == null) {
            return;
        }
        Object userObject = note.getUserObject();
        if (userObject instanceof IndexDefVO) {
            IndexDefVO indexDefVO = (IndexDefVO) userObject;
            // 展示
            this.details.clear();
            this.details.add("名称: " + indexDefVO.getFieldName());
            this.details.add("字段说明: " + indexDefVO.getFieldComment());
            this.details.add("字段类型: " + indexDefVO.getFieldType());
            this.details.add("父节点: " + indexDefVO.getPid());
        }
    }

    private void buildTreeModel(java.util.List<TempIndexDefinitionEntity> tempIndexDefinitionEntities) {
        if (tempIndexDefinitionEntities == null) {
            return;
        }
        IndexDefVO indexDefVO = new IndexDefVO();
        indexDefVO.setName("索引");
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(indexDefVO);
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
        setTreeModel(defaultTreeModel);
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


    public EsIndexVO getEsIndexFrom() {
        return esIndexFrom;
    }

    public void setEsIndexFrom(EsIndexVO esIndexFrom) {
        EsIndexVO old = this.esIndexFrom;
        this.esIndexFrom = esIndexFrom;
        firePropertyChange("esIndexFrom", old, esIndexFrom);
    }


    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        List<String> old = this.details;
        this.details = details;
        firePropertyChange("details", old, details);
    }

    public List<IndexDefVO> getTreeModelUserObject() {
        java.util.List<IndexDefVO> list = new ArrayList<>();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeModel.getRoot();
        getChildren(list, node.children());
        return list;
    }

    private void getChildren(java.util.List<IndexDefVO> list, Enumeration<DefaultMutableTreeNode> children) {
        if (children == null) {
            return;
        }
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode defaultMutableTreeNode = children.nextElement();
            Object userObject = defaultMutableTreeNode.getUserObject();
            if (userObject instanceof IndexDefVO) {
                list.add((IndexDefVO) userObject);
            }
            getChildren(list, defaultMutableTreeNode.children());
        }
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        DefaultTreeModel old = this.treeModel;
        this.treeModel = treeModel;
        firePropertyChange("model", old, treeModel);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel2 = new JPanel();
        separator2 = compFactory.createSeparator("  \u57fa\u672c\u4fe1\u606f");
        label7 = new JLabel();
        textField7 = new JTextField();
        label12 = new JLabel();
        textField2 = new JTextField();
        label8 = new JLabel();
        textField8 = new JTextField();
        label13 = new JLabel();
        textField6 = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        popupMenu1 = new JPopupMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {89, 138, 74, 185, 0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            panel2.add(separator2, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label7 ----
            label7.setText("\u540d\u79f0\uff1a");
            panel2.add(label7, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel2.add(textField7, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label12 ----
            label12.setText("\u522b\u540d\uff1a");
            panel2.add(label12, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel2.add(textField2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label8 ----
            label8.setText("\u72b6\u6001\uff1a");
            panel2.add(label8, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel2.add(textField8, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel2.add(label13, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textField6 ----
            textField6.setVisible(false);
            panel2.add(textField6, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        add(panel2, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //======== scrollPane1 ========
            {

                //---- tree1 ----
                tree1.setPreferredSize(new Dimension(200, 82));
                tree1.setComponentPopupMenu(popupMenu1);
                tree1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tree1MouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tree1);
            }
            panel1.add(scrollPane1);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(list1);
            }
            panel1.add(scrollPane2);
        }
        add(panel1, BorderLayout.CENTER);

        //======== popupMenu1 ========
        {

            //---- menuItem2 ----
            menuItem2.setText("\u6dfb\u52a0\u5b50\u8282\u70b9");
            menuItem2.addActionListener(e -> addChildNode(e));
            popupMenu1.add(menuItem2);

            //---- menuItem3 ----
            menuItem3.setText("\u5220\u9664\u8282\u70b9");
            popupMenu1.add(menuItem3);

            //---- menuItem4 ----
            menuItem4.setText("\u4fee\u6539\u8282\u70b9");
            popupMenu1.add(menuItem4);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("treeModel"),
            tree1, BeanProperty.create("model")));
        bindingGroup.addBinding(SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("details"), list1));
        bindingGroup.bind();
        baseInfoGroup = new BindingGroup();
        baseInfoGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.indexName"),
            textField7, BeanProperty.create("text")));
        baseInfoGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.indexAlia"),
            textField2, BeanProperty.create("text")));
        baseInfoGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.status"),
            textField8, BeanProperty.create("text")));
        baseInfoGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.id"),
            textField6, BeanProperty.create("text")));
        baseInfoGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JComponent separator2;
    private JLabel label7;
    private JTextField textField7;
    private JLabel label12;
    private JTextField textField2;
    private JLabel label8;
    private JTextField textField8;
    private JLabel label13;
    private JTextField textField6;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JScrollPane scrollPane2;
    private JList list1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private BindingGroup bindingGroup;
    private BindingGroup baseInfoGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
