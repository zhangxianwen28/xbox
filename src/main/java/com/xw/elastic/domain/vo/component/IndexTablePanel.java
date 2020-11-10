/*
 * Created by JFormDesigner on Mon Oct 19 11:25:59 CST 2020
 */

package com.xw.elastic.domain.vo.component;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.xw.education.util.SwingUtil;
import com.xw.elastic.domain.bo.IndexTableBO;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

/**
 * @author Brainrain
 */
public class IndexTablePanel extends JPanel {
    private IndexTableBO indexTableBO = IndexTableBO.create();;

    public IndexTablePanel() {
        initComponents();
        SwingUtil.fitTableColumns(this.table1, 0);
    }

    private void pageStateChanged(ChangeEvent e) {
       /* JSpinner source = (JSpinner) e.getSource();
        //JOptionPane.showMessageDialog(null, "page changed");
        esIndexVOS.clear();
        esIndexQuery.setPage(Integer.parseInt(String.valueOf(source.getValue())));

        Page<EsIndex> page = indexService.page(new EsIndexQuery());
        List<EsIndexVO> collect = page.map(x -> {
            EsIndexVO esIndexVO = new EsIndexVO();
            BeanUtils.copyProperties(x, esIndexVO);
            return esIndexVO;

        }).stream().collect(Collectors.toList());

        esIndexVOS.addAll(collect);
        totalNum = page.getTotalPages();*/
    }


    public IndexTableBO getIndexTableBO() {
        return indexTableBO;
    }

    public void setIndexTableBO(IndexTableBO indexTableBO) {
        this.indexTableBO = indexTableBO;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel1 = new JPanel();
        label1 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        spinner1 = new JSpinner();
        label6 = new JLabel();
        button10 = new JButton();
        panel6 = new JPanel();
        panel4 = new JPanel();
        panel3 = new JPanel();
        separator1 = compFactory.createSeparator("  \u68c0\u7d22\u6761\u4ef6\uff1a");
        label4 = new JLabel();
        textField1 = new JTextField();
        label7 = new JLabel();
        textField2 = new JTextField();
        label8 = new JLabel();
        textField3 = new JTextField();
        label9 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        button9 = new JButton();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        popupMenu1 = new JPopupMenu();
        menuItem3 = new JMenuItem();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();

        //======== this ========
        setLayout(new BorderLayout());

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

            //---- button10 ----
            button10.setText("\u5bfc\u51fa");
            panel1.add(button10);
        }
        add(panel1, BorderLayout.SOUTH);

        //======== panel6 ========
        {
            panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        add(panel6, BorderLayout.NORTH);

        //======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());

            //======== panel3 ========
            {
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 131, 27, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                panel3.add(separator1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label4 ----
                label4.setText("  \u59d3\u540d\uff1a");
                panel3.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField1 ----
                textField1.setPreferredSize(new Dimension(100, 30));
                panel3.add(textField1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label7 ----
                label7.setText("  \u4f4f\u5740\uff1a");
                panel3.add(label7, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField2 ----
                textField2.setPreferredSize(new Dimension(100, 30));
                panel3.add(textField2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label8 ----
                label8.setText("  \u7535\u8bdd\uff1a");
                panel3.add(label8, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField3 ----
                textField3.setPreferredSize(new Dimension(100, 30));
                panel3.add(textField3, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label9 ----
                label9.setText("  \u6027\u522b\uff1a");
                panel3.add(label9, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                panel3.add(comboBox1, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button1 ----
                button1.setText("\u67e5\u8be2");
                panel3.add(button1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button9 ----
                button9.setText("\u65b0\u589e");
                panel3.add(button9, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            panel4.add(panel3, BorderLayout.CENTER);
        }
        add(panel4, BorderLayout.EAST);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table1);
        }
        add(scrollPane2, BorderLayout.WEST);

        //======== popupMenu1 ========
        {

            //---- menuItem3 ----
            menuItem3.setText("\u65b0\u589e");
            menuItem3.setFont(menuItem3.getFont().deriveFont(menuItem3.getFont().getStyle() | Font.BOLD));
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
    private JPanel panel1;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    private JLabel label5;
    private JSpinner spinner1;
    private JLabel label6;
    private JButton button10;
    private JPanel panel6;
    private JPanel panel4;
    private JPanel panel3;
    private JComponent separator1;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label7;
    private JTextField textField2;
    private JLabel label8;
    private JTextField textField3;
    private JLabel label9;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button9;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
