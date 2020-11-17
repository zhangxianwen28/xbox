/*
 * Created by JFormDesigner on Mon Oct 19 11:25:59 CST 2020
 */

package com.xw.swing.elastic.panel;

import java.awt.event.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xw.controller.IndexController;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.education.domain.dto.StudentQuery;
import com.xw.swing.education.domain.vo.table.StudentVO;
import com.xw.swing.elastic.domain.bo.*;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.swing.elastic.domain.vo.IndexDefVO;
import com.xw.swing.elastic.domain.vo.IndexTableBO;
import com.xw.util.dynaminc.ReflectUtil;
import com.xw.util.learn.tree.Tree;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brainrain
 */
public class IndexTablePanel extends JPanel {
    private IndexTableBO indexTableBO = IndexTableBO.create();
    private String jsonView;
    private final IndexController indexController = new IndexController();

    public IndexTablePanel() {
        initComponents();
    }

    /**
     * 分页
     *
     * @param e
     */
    private void pageStateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        indexTableBO.setPage(Integer.parseInt(String.valueOf(source.getValue())));
        initModel();
    }

    /**
     * 数据初始化
     */
    public void initModel() {
        EsIndexVO esIndexQuery = indexTableBO.getIndexQuery();
        PageWrapper<EsIndexVO> esIndexPage = indexController.getEsIndexPage(esIndexQuery, indexTableBO.getPage(), indexTableBO.getSize());
        indexTableBO.getIndexList().clear();
        indexTableBO.getIndexList().addAll(esIndexPage.getData());
        indexTableBO.setTotalNum(esIndexPage.getTotalNum());
    }

    /**
     * 添加索引
     *
     * @param e
     */
    private void addIndex(ActionEvent e) {
        IndexDefinitionPanel indexPanel = new IndexDefinitionPanel();
        indexPanel.setEsIndexFrom(new EsIndexVO());
        JOptionPane optionPane = new JOptionPane(indexPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, "新增");
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return;
        }

        EsIndexVO esIndexVO = indexPanel.getEsIndexFrom();

        List<IndexDefVO> indexDefVOS = indexPanel.getTreeModelUserObject();
        indexController.saveIndex(esIndexVO, indexDefVOS);

        indexTableBO.getIndexList().add(esIndexVO);
        int row = indexTableBO.getIndexSize() - 1;
        this.table1.setRowSelectionInterval(row, row);
        this.table1.scrollRectToVisible(this.table1.getCellRect(row, 0, true));
        indexTableBO.setTotalNum(indexTableBO.getTotalNum()+1);
    }

    /**
     * 更新索引
     *
     * @param e
     */
    private void updateIndex(ActionEvent e) {
        IndexDefinitionPanel indexPanel = new IndexDefinitionPanel();

        Object id = this.table1.getValueAt(this.table1.getSelectedRow(), 0);
        if (id == null) {
            JOptionPane.showMessageDialog(null, "未选中");
            return;
        }

        EsIndexVO esIndexById = indexController.getEsIndexById(String.valueOf(id));
        List<Tree<IndexDefVO>> indexDefVOTree = indexController.getIndexDefVOTree(String.valueOf(id));
        indexPanel.setEsIndexFrom(esIndexById);
        DefaultTreeModel treeModel = buildTreeModel(indexDefVOTree);
        indexPanel.setTreeModel(treeModel==null ?new DefaultTreeModel(new DefaultMutableTreeNode("mappings")):treeModel);
        JOptionPane optionPane = new JOptionPane(indexPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, "修改");
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return;
        }

        EsIndexVO esIndexVO = indexPanel.getEsIndexFrom();
        List<IndexDefVO> indexDefVOS = indexPanel.getTreeModelUserObject();
        indexController.saveIndex(esIndexVO, indexDefVOS);
        initModel();
    }

    private void deleteIndex(ActionEvent e) {
        Object id = this.table1.getValueAt(this.table1.getSelectedRow(), 0);
        Object name = this.table1.getValueAt(this.table1.getSelectedRow(), 1);
        if (id == null) {
            JOptionPane.showMessageDialog(null, "未选中");
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "确定要删除 【" + name + "】?");
        // 确定
        if (i == 0) {
            indexController.removeIndex(String.valueOf(id));
            initModel();
        }

    }

    /**
     * 显示详情
     *
     * @param e
     */
    private void showDetails(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Object id = this.table1.getValueAt(this.table1.getSelectedRow(), 0);
            if (id == null) {
                return;
            }


            EsDoc esDoc = new EsDoc();


            Map<String, Object> propertiesMap = new HashMap<>();

            List<Tree<IndexDefVO>> indexDefVOTree = indexController.getIndexDefVOTree(String.valueOf(id));
            if(indexDefVOTree==null){
                return;
            }
            for (Tree<IndexDefVO> defVOTree : indexDefVOTree) {
                if (defVOTree.isTopNode()) {
                    IndexDefVO data = defVOTree.getData();
                    String field = data.getFieldName().toLowerCase();
                    if (data.isLeaf()) {
                        propertiesMap.put(field, new EsType(data.getFieldType()));

                    } else {
                        Map<String, Object> properties = new HashMap<>();
                        buildChildNode(defVOTree, properties);
                        Object object = ReflectUtil.getObject(new Object(), properties);
                        Map<String, Object> properties2 = new HashMap<>();
                        properties2.put("properties", object);
                        propertiesMap.put(field, properties2);
                    }
                }
            }
            Object obj = ReflectUtil.getObject(new Object(), propertiesMap);
            esDoc.setMappings(obj);
            esDoc.setSettings(new Object());

            String str = JSON.toJSONString(obj, SerializerFeature.PrettyFormat);
            System.err.println(str);
            String newstr = str.replaceAll("\t", "    ");
            setJsonView(newstr);
            JOptionPane optionPane = new JOptionPane(panel5);
            JDialog dialog = optionPane.createDialog(this, "视图");
            dialog.setResizable(true);
            dialog.setVisible(true);

        }
    }

    private DefaultTreeModel buildTreeModel(List<Tree<IndexDefVO>> defVOS) {
        if (defVOS == null) {
            return null;
        }
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("mappings");
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(top);
        for (Tree<IndexDefVO> tree : defVOS) {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(tree.getData());
            buildChildNode(tree, treeNode);
            top.add(treeNode);

        }
        return defaultTreeModel;
    }

    private void buildChildNode(Tree<IndexDefVO> tree, DefaultMutableTreeNode treeNode) {
        if (!tree.isHasChild()) {
            return;
        }
        for (Tree<IndexDefVO> childNode : tree.getChildNodes()) {
            treeNode.add(new DefaultMutableTreeNode(childNode.getData()));
            buildChildNode(childNode, treeNode);
        }
    }

    private void buildChildNode(Tree<IndexDefVO> tree, Map<String, Object> properties) {
        if (!tree.isHasChild()) {
            return;
        }
        for (Tree<IndexDefVO> childNode : tree.getChildNodes()) {
            IndexDefVO data = childNode.getData();
            properties.put(data.getFieldName().toLowerCase(), new EsType(data.getFieldType()));
            buildChildNode(childNode, properties);
        }
    }

    // **************************************GET SET**********************************************
    public IndexTableBO getIndexTableBO() {
        return indexTableBO;
    }

    public void setIndexTableBO(IndexTableBO indexTableBO) {
        this.indexTableBO = indexTableBO;
    }

    public String getJsonView() {
        return jsonView;
    }

    public void setJsonView(String jsonView) {
        String old = this.jsonView;
        this.jsonView = jsonView;
        firePropertyChange("jsonView", old, jsonView);
    }


    //**************************************GET SET END**********************************************

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel6 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel3 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        button1 = new JButton();
        button9 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        spinner1 = new JSpinner();
        label6 = new JLabel();
        panel4 = new JPanel();
        popupMenu1 = new JPopupMenu();
        menuItem3 = new JMenuItem();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel6 ========
        {
            panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        add(panel6, BorderLayout.NORTH);

        //======== tabbedPane1 ========
        {

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout());

                //======== scrollPane2 ========
                {

                    //---- table1 ----
                    table1.setComponentPopupMenu(popupMenu1);
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            showDetails(e);
                        }
                    });
                    scrollPane2.setViewportView(table1);
                }
                panel3.add(scrollPane2, BorderLayout.CENTER);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- button1 ----
                    button1.setText("\u67e5\u8be2");
                    panel2.add(button1);

                    //---- button9 ----
                    button9.setText("\u65b0\u589e");
                    button9.addActionListener(e -> addIndex(e));
                    panel2.add(button9);
                }
                panel3.add(panel2, BorderLayout.NORTH);

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- label1 ----
                    label1.setText("\u5171");
                    panel1.add(label1);

                    //---- label3 ----
                    label3.setText("text");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD));
                    panel1.add(label3);

                    //---- label2 ----
                    label2.setText("\u6761\u6570\u636e");
                    panel1.add(label2);

                    //---- label5 ----
                    label5.setText("   \u5f53\u524d\u7b2c\uff1a");
                    panel1.add(label5);

                    //---- spinner1 ----
                    spinner1.setPreferredSize(new Dimension(60, 25));
                    spinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
                    spinner1.addChangeListener(e -> pageStateChanged(e));
                    panel1.add(spinner1);

                    //---- label6 ----
                    label6.setText("\u9875");
                    panel1.add(label6);
                }
                panel3.add(panel1, BorderLayout.SOUTH);
            }
            tabbedPane1.addTab("\u7d22\u5f15", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new BorderLayout());
            }
            tabbedPane1.addTab("\u540c\u6b65", panel4);
        }
        add(tabbedPane1, BorderLayout.CENTER);

        //======== popupMenu1 ========
        {

            //---- menuItem3 ----
            menuItem3.setText("\u65b0\u589e");
            menuItem3.setFont(menuItem3.getFont().deriveFont(menuItem3.getFont().getStyle() | Font.BOLD));
            menuItem3.addActionListener(e -> addIndex(e));
            popupMenu1.add(menuItem3);

            //---- menuItem1 ----
            menuItem1.setText("\u4fee\u6539");
            menuItem1.addActionListener(e -> updateIndex(e));
            popupMenu1.add(menuItem1);

            //---- menuItem2 ----
            menuItem2.setText("\u5220\u9664");
            menuItem2.addActionListener(e -> deleteIndex(e));
            popupMenu1.add(menuItem2);
        }

        //======== panel5 ========
        {
            panel5.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- textPane1 ----
                textPane1.setEditable(false);
                scrollPane1.setViewportView(textPane1);
            }
            panel5.add(scrollPane1, BorderLayout.CENTER);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                this, BeanProperty.create("pageNum"),
                spinner1, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                this, BeanProperty.create("indexTableBO.totalNum"),
                label3, BeanProperty.create("text")));
        {
            JTableBinding binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                    this, (BeanProperty) BeanProperty.create("indexTableBO.indexList"), table1);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("id"))
                    .setColumnName("Id")
                    .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("indexAlia"))
                    .setColumnName("Index Alia")
                    .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("indexName"))
                    .setColumnName("Index Name")
                    .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("status"))
                    .setColumnName("Status")
                    .setColumnClass(String.class);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
                this, BeanProperty.create("jsonView"),
                textPane1, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel6;
    private JTabbedPane tabbedPane1;
    private JPanel panel3;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel2;
    private JButton button1;
    private JButton button9;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    private JLabel label5;
    private JSpinner spinner1;
    private JLabel label6;
    private JPanel panel4;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
