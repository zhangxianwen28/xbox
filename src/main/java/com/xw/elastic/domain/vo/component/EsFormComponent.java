/*
 * Created by JFormDesigner on Mon Nov 09 13:04:00 CST 2020
 */

package com.xw.elastic.domain.vo.component;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;

/**
 * @author Brainrain
 */
public class EsFormComponent extends JPanel {
    public EsFormComponent() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel1 = new JPanel();
        button3 = new JButton();
        button2 = new JButton();
        panel7 = new JPanel();
        separator1 = compFactory.createSeparator("  \u6620\u5c04");
        label1 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        comboBox1 = new JComboBox<>();
        label5 = new JLabel();
        textField5 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        label7 = new JLabel();
        textField7 = new JTextField();
        label8 = new JLabel();
        label9 = new JLabel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- button3 ----
            button3.setText("\u5220\u9664");
            panel1.add(button3);

            //---- button2 ----
            button2.setText("\u4fdd\u5b58");
            panel1.add(button2);
        }
        add(panel1, BorderLayout.SOUTH);

        //======== panel7 ========
        {
            panel7.setLayout(new GridBagLayout());
            ((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {30, 51, 125, 68, 125, 25, 0};
            ((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            panel7.add(separator1, new GridBagConstraints(0, 0, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label1 ----
            label1.setText("\u540d\u79f0\uff1a");
            panel7.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel7.add(textField1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("\u6ce8\u91ca\uff1a");
            panel7.add(label3, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(textArea1);
            }
            panel7.add(scrollPane1, new GridBagConstraints(4, 1, 1, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u7c7b\u578b\uff1a");
            panel7.add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

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
            panel7.add(comboBox1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("\u5206\u6790\uff1a");
            panel7.add(label5, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel7.add(textField5, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u590d\u5236\u5230\uff1a");
            panel7.add(label4, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel7.add(textField4, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label6 ----
            label6.setText("\u6700\u5927\u957f\u5ea6\uff1a");
            panel7.add(label6, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel7.add(textField6, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label7 ----
            label7.setText("\u9886\u57df\uff1a");
            panel7.add(label7, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));
            panel7.add(textField7, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label8 ----
            label8.setText("\u7236\u8282\u70b9\uff1a");
            panel7.add(label8, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- label9 ----
            label9.setText("text");
            panel7.add(label9, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }
        add(panel7, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton button3;
    private JButton button2;
    private JPanel panel7;
    private JComponent separator1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label2;
    private JComboBox<String> comboBox1;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label6;
    private JTextField textField6;
    private JLabel label7;
    private JTextField textField7;
    private JLabel label8;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
