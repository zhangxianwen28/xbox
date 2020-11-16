/*
 * Created by JFormDesigner on Fri Nov 13 17:43:45 CST 2020
 */

package com.xw.swing.elastic.panel;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import com.xw.swing.elastic.domain.vo.IndexDefVO;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author Brainrain
 */
public class IndexDefinitionFrom extends JPanel {
    private IndexDefVO indexDef;

    public IndexDefinitionFrom() {
        initComponents();
    }


    public IndexDefVO getIndexDef() {
        return indexDef;
    }

    public void setIndexDef(IndexDefVO indexDef) {
        IndexDefVO old = this.indexDef;
        this.indexDef = indexDef;
        firePropertyChange("indexDef", old, indexDef);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel3 = new JPanel();
        separator1 = compFactory.createSeparator("  \u6620\u5c04");
        label10 = new JLabel();
        label11 = new JLabel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
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

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(textArea1);
            }
            panel3.add(scrollPane2, new GridBagConstraints(3, 2, 1, 2, 0.0, 0.0,
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
        add(panel3, BorderLayout.CENTER);

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.name"),
            textField1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("indexDef.pid"),
            label11, BeanProperty.create("text")));
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
            this, BeanProperty.create("indexDef.copyto"),
            textField4, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JComponent separator1;
    private JLabel label10;
    private JLabel label11;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label6;
    private JScrollPane scrollPane2;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
