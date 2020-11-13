/*
 * Created by JFormDesigner on Mon Oct 19 11:25:59 CST 2020
 */

package com.xw.swing.elastic.panel;

import java.awt.event.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import com.xw.controller.IndexController;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.elastic.domain.bo.IndexTableBO;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.util.other.IDGenerator;
import lombok.Data;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * @author Brainrain
 */
public class IndexTablePanel extends JPanel {
    private IndexTableBO indexTableBO = IndexTableBO.create();;
    private final IndexController indexController = new IndexController();

    public IndexTablePanel() {
        initComponents();
    }

    private void pageStateChanged(ChangeEvent e) {

    }

    public void initModel(){
        EsIndexVO esIndexQuery = indexTableBO.getIndexQuery();
        PageWrapper<EsIndexVO> esIndexPage = indexController.getEsIndexPage(esIndexQuery, indexTableBO.getPage(), indexTableBO.getSize());
        indexTableBO.getIndexList().clear();
        indexTableBO.getIndexList().addAll(esIndexPage.getData());
        indexTableBO.setTotalNum(esIndexPage.getTotalNum());
    }

    private void addIndex(ActionEvent e) {
        IndexDefinitionPanel indexPanel = new IndexDefinitionPanel();
        indexPanel.setEsIndexFrom(new EsIndexVO());
        JOptionPane optionPane = new JOptionPane(indexPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, "新增");
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return ;
        }

        EsIndexVO esIndexVO = indexPanel.getEsIndexFrom();
        esIndexVO.setId(String.valueOf(IDGenerator.getId()));
        esIndexVO.setStatus("未发布");
        indexController.save(esIndexVO);

        indexTableBO.getIndexList().add(esIndexVO);
        int row = indexTableBO.getIndexSize()- 1;
        this.table1.setRowSelectionInterval(row, row);
        this.table1.scrollRectToVisible(this.table1.getCellRect(row, 0, true));
    }




    // **************************************GET SET**********************************************
    public IndexTableBO getIndexTableBO() {
        return indexTableBO;
    }

    public void setIndexTableBO(IndexTableBO indexTableBO) {
        this.indexTableBO = indexTableBO;
    }



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
                    scrollPane2.setViewportView(table1);
                }
                panel3.add(scrollPane2, BorderLayout.WEST);

                //======== panel2 ========
                {
                    panel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                    ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- button1 ----
                    button1.setText("\u67e5\u8be2");
                    panel2.add(button1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- button9 ----
                    button9.setText("\u65b0\u589e");
                    button9.addActionListener(e -> addIndex(e));
                    panel2.add(button9, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                panel3.add(panel2, BorderLayout.EAST);

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
            tabbedPane1.addTab("\u64cd\u4f5c", panel4);
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
            popupMenu1.add(menuItem1);

            //---- menuItem2 ----
            menuItem2.setText("\u5220\u9664");
            popupMenu1.add(menuItem2);
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
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class IndexPanel extends JPanel {
        private EsIndexVO indexFrom ;
        private IndexPanel() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        public EsIndexVO getIndexFrom() {
            return indexFrom;
        }

        public void setIndexFrom(EsIndexVO indexFrom) {
            this.indexFrom = indexFrom;
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
}
