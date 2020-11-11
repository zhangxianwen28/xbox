/*
 * Created by JFormDesigner on Wed Aug 19 13:44:28 CST 2020
 */

package com.xw.swing.generator;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import com.xw.swing.generator.bean.DataSource;
import com.xw.swing.generator.bean.DataSourceInfo;
import com.xw.swing.generator.bean.DatasourceSelect;
import com.xw.swing.generator.bean.GeneratorFrom;
import com.xw.swing.generator.util.CodeGenerator;
import com.xw.swing.generator.util.XUtils;
import lombok.Getter;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author sad
 */
@Getter
public class Box extends JFrame {
    public Box() {
        undecorated();
        initComponents();
    }

    private void undecorated() {
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }


    private void applyActionPerformed(ActionEvent e) {
        DataSourceInfo dataSourceInfo = this.getDataSourceInfo1();
        dataSourceInfo.setDriverName(XUtils.getDriverName(dataSourceInfo.getSelectDriverType()));
        String name = dataSourceInfo.getName().toLowerCase().trim();
        List<String> dataSourceInfosSelect = this.datasourceSelect1.getDataSourceInfosSelect();
        if (!dataSourceInfosSelect.contains(name)) {
            dataSourceInfosSelect.add(name);
            this.comboBox2.setModel(new DefaultComboBoxModel(dataSourceInfosSelect.toArray()));
            DataSource.dataSourceInfoMap.put(name, dataSourceInfo);
        }
    }

    private void okActionPerformed(ActionEvent e) {
        applyActionPerformed(e);
        toggleButton1.setSelected(false);
        this.comboBox2.requestFocus();
    }

    private void thisComponentShown(ComponentEvent e) {
        DataSource.dataSourceInfoMap.forEach((k, v) -> {
            this.datasourceSelect1.getDataSourceInfosSelect().add(k);
            this.comboBox2.setSelectedItem(k);
        });

    }

    private void settingsWindowClosed(WindowEvent e) {
        toggleButton1.setSelected(false);
        this.comboBox2.requestFocus();
    }

    private void button2ActionPerformed(ActionEvent e) {
        button2.setEnabled(false);
        for (String table : this.getGeneratorFrom1().getTables()) {
            CodeGenerator.generator(this.getGeneratorFrom1(),table.toUpperCase().trim());
        }

        button2.setEnabled(true);
    }

    private void settingsComponentShown(ComponentEvent e) {
        textField4.setText("");
        textArea1.setText("");
        passwordField1.setText("");
        textField2.setText("");
        textField1.setText("");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        menu2 = new JMenu();
        menu3 = new JMenu();
        panel3 = new JPanel();
        separator1 = compFactory.createSeparator("\u5168\u5c40\u914d\u7f6e");
        checkBox1 = new JCheckBox();
        checkBox4 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox2 = new JCheckBox();
        label6 = new JLabel();
        textField5 = new JTextField();
        label8 = new JLabel();
        textField16 = new JTextField();
        label7 = new JLabel();
        textField3 = new JTextField();
        separator4 = compFactory.createSeparator("\u5305\u914d\u7f6e");
        label14 = new JLabel();
        textField14 = new JTextField();
        separator5 = compFactory.createSeparator("\u751f\u6210");
        label18 = new JLabel();
        comboBox2 = new JComboBox();
        toggleButton1 = new JToggleButton();
        label15 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea2 = new JTextArea();
        button2 = new JButton();
        settings = new JFrame();
        panel2 = new JPanel();
        separator6 = compFactory.createSeparator("\u4fe1\u606f");
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        separator2 = compFactory.createSeparator("\u914d\u7f6e");
        label17 = new JLabel();
        comboBox1 = new JComboBox();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        ok = new JButton();
        apply = new JButton();
        dataSourceInfo1 = new DataSourceInfo();
        datasourceSelect1 = new DatasourceSelect();
        generatorFrom1 = new GeneratorFrom();
        CellConstraints cc = new CellConstraints();

        //======== this ========
        setForeground(new Color(0, 32, 110));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 600));
        setTitle("Mybatis-Gen");
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                thisComponentShown(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u5f00\u59cb");

                //---- checkBoxMenuItem1 ----
                checkBoxMenuItem1.setText("\u6570\u636e\u5e93\u914d\u7f6e");
                menu1.add(checkBoxMenuItem1);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u8bbe\u7f6e");
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u5e2e\u52a9");
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);

        //======== panel3 ========
        {
            panel3.setLayout(new FormLayout(
                "default, $lcgap, 44dlu, $lcgap, 42dlu, $lcgap, 10dlu, $lcgap, 22dlu, $lcgap, 34dlu",
                "11*(default, $lgap), 46dlu, $lgap, 1dlu, $lgap, default"));
            panel3.add(separator1, cc.xywh(1, 1, 11, 1));

            //---- checkBox1 ----
            checkBox1.setText("\u5f00\u542fAR\u6a21\u5f0f");
            panel3.add(checkBox1, cc.xy(1, 3));

            //---- checkBox4 ----
            checkBox4.setText("Swagger2\u6ce8\u89e3");
            panel3.add(checkBox4, cc.xy(1, 5));

            //---- checkBox3 ----
            checkBox3.setText("\u751f\u6210resultMap");
            panel3.add(checkBox3, cc.xy(1, 7));

            //---- checkBox2 ----
            checkBox2.setText("\u8986\u76d6");
            panel3.add(checkBox2, cc.xy(1, 9));

            //---- label6 ----
            label6.setText("\u4f5c\u8005:");
            panel3.add(label6, cc.xy(1, 11));
            panel3.add(textField5, cc.xywh(3, 11, 3, 1));

            //---- label8 ----
            label8.setText("\u9879\u76ee\u8def\u5f84:");
            panel3.add(label8, cc.xy(1, 13));
            panel3.add(textField16, cc.xywh(3, 13, 3, 1));

            //---- label7 ----
            label7.setText("+");
            panel3.add(label7, cc.xy(7, 13));
            panel3.add(textField3, cc.xywh(9, 13, 3, 1));
            panel3.add(separator4, cc.xywh(1, 15, 11, 1));

            //---- label14 ----
            label14.setText("\u5305\u7684\u57fa\u7840\u8def\u5f84:");
            panel3.add(label14, cc.xy(1, 17));
            panel3.add(textField14, cc.xywh(3, 17, 3, 1));
            panel3.add(separator5, cc.xywh(1, 19, 11, 1));

            //---- label18 ----
            label18.setText("\u6570\u636e\u5e93:");
            panel3.add(label18, cc.xy(1, 21));
            panel3.add(comboBox2, cc.xywh(3, 21, 3, 1));

            //---- toggleButton1 ----
            toggleButton1.setText("+");
            panel3.add(toggleButton1, cc.xy(9, 21));

            //---- label15 ----
            label15.setText("\u8868\u540d:");
            panel3.add(label15, cc.xywh(1, 23, 1, 3));

            //======== scrollPane4 ========
            {

                //---- textArea2 ----
                textArea2.setWrapStyleWord(true);
                textArea2.setLineWrap(true);
                scrollPane4.setViewportView(textArea2);
            }
            panel3.add(scrollPane4, cc.xywh(3, 23, 6, 3));

            //---- button2 ----
            button2.setText("\u5f00\u59cb");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel3.add(button2, cc.xy(11, 27));
        }
        contentPane.add(panel3);
        pack();
        setLocationRelativeTo(getOwner());

        //======== settings ========
        {
            settings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            settings.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    settingsWindowClosed(e);
                }
            });
            settings.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    settingsComponentShown(e);
                }
            });
            Container settingsContentPane = settings.getContentPane();
            settingsContentPane.setLayout(new FlowLayout());

            //======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                    "right:default, $lcgap, [84dlu,default,200dlu], $lcgap, right:29dlu, $lcgap, 27dlu",
                    "3*(default, $lgap), 16dlu, 2*($lgap, default), $lgap, fill:default, 5*($lgap, default)"));
                panel2.add(separator6, cc.xywh(1, 1, 7, 1));

                //---- label4 ----
                label4.setText("\u540d\u79f0\uff1a");
                panel2.add(label4, cc.xy(1, 3));
                panel2.add(textField4, cc.xywh(3, 3, 3, 1));

                //---- label5 ----
                label5.setText("\u8bf4\u660e\uff1a");
                panel2.add(label5, cc.xy(1, 5));

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setColumns(5);
                    scrollPane1.setViewportView(textArea1);
                }
                panel2.add(scrollPane1, cc.xywh(3, 5, 3, 3));
                panel2.add(separator2, cc.xywh(1, 9, 7, 1));

                //---- label17 ----
                label17.setText("\u6570\u636e\u6e90\uff1a");
                panel2.add(label17, cc.xy(1, 11));

                //---- comboBox1 ----
                comboBox1.setPrototypeDisplayValue("aasasa");
                panel2.add(comboBox1, cc.xywh(3, 11, 3, 1));

                //---- label1 ----
                label1.setText("\u5730\u5740\uff1a");
                panel2.add(label1, cc.xy(1, 13));
                panel2.add(textField1, cc.xywh(3, 13, 3, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));

                //---- label2 ----
                label2.setText("\u7528\u6237\u540d\uff1a");
                panel2.add(label2, cc.xy(1, 15));
                panel2.add(textField2, cc.xywh(3, 15, 3, 1));

                //---- label3 ----
                label3.setText("\u5bc6\u7801\uff1a");
                panel2.add(label3, cc.xy(1, 17));
                panel2.add(passwordField1, cc.xywh(3, 17, 3, 1));

                //---- button1 ----
                button1.setText("\u6d4b\u8bd5\u8fde\u63a5");
                panel2.add(button1, cc.xy(3, 19));

                //---- ok ----
                ok.setText("\u786e\u5b9a");
                ok.addActionListener(e -> okActionPerformed(e));
                panel2.add(ok, cc.xy(5, 21));

                //---- apply ----
                apply.setText("\u4fdd\u5b58");
                apply.addActionListener(e -> applyActionPerformed(e));
                panel2.add(apply, cc.xy(7, 21));
            }
            settingsContentPane.add(panel2);
            settings.pack();
            settings.setLocationRelativeTo(settings.getOwner());
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("name"),
            textField4, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("description"),
            textArea1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("selectDriverType"),
            comboBox1, BeanProperty.create("selectedItem")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("url"),
            textField1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("username"),
            textField2, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dataSourceInfo1, BeanProperty.create("password"),
            passwordField1, BeanProperty.create("text")));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            datasourceSelect1, (BeanProperty) BeanProperty.create("driverTypeSelect"), comboBox1));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            datasourceSelect1, (BeanProperty) BeanProperty.create("dataSourceInfosSelect"), comboBox2));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            settings, BeanProperty.create("visible"),
            toggleButton1, BeanProperty.create("selected")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("activeRecord"),
            checkBox1, BeanProperty.create("selected")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("swagger2"),
            checkBox4, BeanProperty.create("selected")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("baseResultMap"),
            checkBox3, BeanProperty.create("selected")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("fileOverride"),
            checkBox2, BeanProperty.create("selected")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("projectPath"),
            textField16, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("parent"),
            textField14, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("dataSource"),
            comboBox2, BeanProperty.create("selectedItem")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("tableText"),
            textArea2, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("buildPath"),
            textField3, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            generatorFrom1, BeanProperty.create("author"),
            textField5, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JCheckBoxMenuItem checkBoxMenuItem1;
    private JMenu menu2;
    private JMenu menu3;
    private JPanel panel3;
    private JComponent separator1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox4;
    private JCheckBox checkBox3;
    private JCheckBox checkBox2;
    private JLabel label6;
    private JTextField textField5;
    private JLabel label8;
    private JTextField textField16;
    private JLabel label7;
    private JTextField textField3;
    private JComponent separator4;
    private JLabel label14;
    private JTextField textField14;
    private JComponent separator5;
    private JLabel label18;
    private JComboBox comboBox2;
    private JToggleButton toggleButton1;
    private JLabel label15;
    private JScrollPane scrollPane4;
    private JTextArea textArea2;
    private JButton button2;
    private JFrame settings;
    private JPanel panel2;
    private JComponent separator6;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JComponent separator2;
    private JLabel label17;
    private JComboBox comboBox1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton ok;
    private JButton apply;
    private DataSourceInfo dataSourceInfo1;
    private DatasourceSelect datasourceSelect1;
    private GeneratorFrom generatorFrom1;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
