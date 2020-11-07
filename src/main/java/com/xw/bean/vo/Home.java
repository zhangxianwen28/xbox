/*
 * Created by JFormDesigner on Fri Nov 06 13:09:51 CST 2020
 */

package com.xw.bean.vo;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Brainrain
 */
public class Home extends JFrame {
    public Home() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel4 = new JPanel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        panel6 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        panel3 = new JPanel();
        panel5 = new JPanel();
        comboBox1 = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== tabbedPane1 ========
        {

            //======== panel4 ========
            {
                panel4.setLayout(new BorderLayout());
            }
            tabbedPane1.addTab("\u4e00\u822c", panel4);

            //======== panel2 ========
            {
                panel2.setLayout(new BorderLayout());

                //======== scrollPane1 ========
                {

                    //---- table1 ----
                    table1.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null},
                        },
                        new String[] {
                            "\u540d\u79f0", "\u7c7b\u578b", "\u6ce8\u91ca", "\u590d\u5236\u5230", "\u5206\u6790", "\u6700\u5927\u8f93\u5165\u957f\u5ea6", "\u9886\u57df"
                        }
                    ));
                    scrollPane1.setViewportView(table1);
                }
                panel2.add(scrollPane1, BorderLayout.CENTER);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(list1);
                }
                panel2.add(scrollPane2, BorderLayout.WEST);

                //======== panel6 ========
                {
                    panel6.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                    ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                    //---- button2 ----
                    button2.setText("\u63d2\u5165\u5217");
                    panel6.add(button2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- button3 ----
                    button3.setText("\u5220\u9664\u5217");
                    panel6.add(button3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel2.add(panel6, BorderLayout.EAST);
            }
            tabbedPane1.addTab("\u6620\u5c04", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout());
            }
            tabbedPane1.addTab("\u8bbe\u7f6e", panel3);

            //======== panel5 ========
            {
                panel5.setLayout(new BorderLayout());
            }
            tabbedPane1.addTab("\u68c0\u67e5", panel5);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "text",
            "keyword",
            "mumeric",
            "date",
            "datenanoseconde",
            "boolean",
            "binary",
            "range",
            "object",
            "nested",
            "array",
            "multi-fields"
        }));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel panel4;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JList list1;
    private JPanel panel6;
    private JButton button2;
    private JButton button3;
    private JPanel panel3;
    private JPanel panel5;
    private JComboBox<String> comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
