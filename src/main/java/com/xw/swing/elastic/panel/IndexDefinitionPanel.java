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
    private IndexDefVO indexDef;

    public IndexDefinitionPanel() {
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("mappings"));
        initComponents();
        this.tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    /**
     * 添加子节点
     *
     * @param e
     */
    public void addChildNode(ActionEvent e) {
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

        setIndexDef(indexDef);

        JOptionPane optionPane = new JOptionPane(panel3, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, "新增");
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return;
        }
        IndexDefVO indexDef1 = getIndexDef();
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
    // ********************************GET SET**********************************
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

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        DefaultTreeModel old = this.treeModel;
        this.treeModel = treeModel;
        firePropertyChange("treeModel", old, treeModel);
    }
    public IndexDefVO getIndexDef() {
        return indexDef;
    }

    public void setIndexDef(IndexDefVO indexDef) {
        IndexDefVO old = this.indexDef;
        this.indexDef = indexDef;
        firePropertyChange("indexDef", old, indexDef);

    }
    // ********************************GET SET END **********************************
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        separator2 = compFactory.createSeparator("\u4fe1\u606f");
        label7 = new JLabel();
        textField7 = new JTextField();
        label12 = new JLabel();
        textField2 = new JTextField();
        label8 = new JLabel();
        textField8 = new JTextField();
        label13 = new JLabel();
        textField6 = new JTextField();
        separator4 = compFactory.createSeparator("\u8bbe\u7f6e");
        label9 = new JLabel();
        spinner1 = new JSpinner();
        label14 = new JLabel();
        spinner2 = new JSpinner();
        label15 = new JLabel();
        spinner3 = new JSpinner();
        panel4 = new JPanel();
        panel5 = new JPanel();
        label17 = new JLabel();
        checkBox1 = new JCheckBox();
        splitPane1 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        popupMenu1 = new JPopupMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        panelmaping = new JPanel();
        panel3 = new JPanel();
        separator1 = compFactory.createSeparator("  \u6620\u5c04");
        label10 = new JLabel();
        label11 = new JLabel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label6 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        comboBox1 = new JComboBox<>();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();

        //======== this ========
        setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel2 ========
            {
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {89, 130, 74, 130, 0, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                panel2.add(separator2, new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

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
                panel2.add(separator4, new GridBagConstraints(0, 3, 5, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label9 ----
                label9.setText("\u4e3b\u5206\u7247\u6570\uff1a");
                panel2.add(label9, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel2.add(spinner1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label14 ----
                label14.setText("\u526f\u672c\u6570\uff1a");
                panel2.add(label14, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel2.add(spinner2, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label15 ----
                label15.setText("\u6700\u5927\u7ed3\u679c\uff1a");
                panel2.add(label15, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel2.add(spinner3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            tabbedPane1.addTab("\u57fa\u672c\u4fe1\u606f", panel2);

            //======== panel4 ========
            {
                panel4.setLayout(new BorderLayout());

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- label17 ----
                    label17.setText("\u52a8\u6001\uff1a");
                    panel5.add(label17);
                    panel5.add(checkBox1);
                }
                panel4.add(panel5, BorderLayout.NORTH);

                //======== splitPane1 ========
                {

                    //======== scrollPane1 ========
                    {

                        //---- tree1 ----
                        tree1.setComponentPopupMenu(popupMenu1);
                        tree1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                tree1MouseClicked(e);
                            }
                        });
                        scrollPane1.setViewportView(tree1);
                    }
                    splitPane1.setLeftComponent(scrollPane1);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(list1);
                    }
                    splitPane1.setRightComponent(scrollPane2);
                }
                panel4.add(splitPane1, BorderLayout.CENTER);
            }
            tabbedPane1.addTab("\u6620\u5c04", panel4);
        }
        add(tabbedPane1, BorderLayout.CENTER);

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

        //======== panelmaping ========
        {
            panelmaping.setLayout(new BorderLayout());

            //======== panel3 ========
            {
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 102, 52, 162, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                panel3.add(separator1, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label10 ----
                label10.setText("\u7236\u8282\u70b9");
                panel3.add(label10, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label11 ----
                label11.setText("text");
                panel3.add(label11, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label1 ----
                label1.setText("\u540d\u79f0\uff1a");
                panel3.add(label1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel3.add(textField1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label6 ----
                label6.setText("\u6ce8\u91ca\uff1a");
                panel3.add(label6, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(textArea1);
                }
                panel3.add(scrollPane3, new GridBagConstraints(3, 2, 1, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setText("\u5b57\u6bb5\u7c7b\u578b\uff1a");
                panel3.add(label2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "text",
                    "keyword",
                    "mumeric",
                    "date",
                    "boolean",
                    "object",
                    "multi-fields"
                }));
                panel3.add(comboBox1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u5206\u6790\uff1a");
                panel3.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel3.add(textField3, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label4 ----
                label4.setText(" \u590d\u5236\u5230\uff1a");
                panel3.add(label4, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel3.add(textField4, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label5 ----
                label5.setText(" \u6700\u5927\u957f\u5ea6\uff1a");
                panel3.add(label5, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 5), 0, 0));
                panel3.add(textField5, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            panelmaping.add(panel3, BorderLayout.CENTER);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("treeModel"),
            tree1, BeanProperty.create("model")));
        bindingGroup.addBinding(SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("details"), list1));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.fieldName"),
            textField1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.fieldComment"),
            textArea1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.fieldType"),
            comboBox1, BeanProperty.create("selectedItem")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.analyzer"),
            textField3, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.copyTo"),
            textField4, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.number_of_shards"),
            spinner1, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.max_result_window"),
            spinner3, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.number_of_replicas"),
            spinner2, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("esIndexFrom.dynamic"),
            checkBox1, BeanProperty.create("selected")));
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
    private JTabbedPane tabbedPane1;
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
    private JComponent separator4;
    private JLabel label9;
    private JSpinner spinner1;
    private JLabel label14;
    private JSpinner spinner2;
    private JLabel label15;
    private JSpinner spinner3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label17;
    private JCheckBox checkBox1;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JScrollPane scrollPane2;
    private JList list1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JPanel panelmaping;
    private JPanel panel3;
    private JComponent separator1;
    private JLabel label10;
    private JLabel label11;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label6;
    private JScrollPane scrollPane3;
    private JTextArea textArea1;
    private JLabel label2;
    private JComboBox<String> comboBox1;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private BindingGroup bindingGroup;
    private BindingGroup baseInfoGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
