/*
 * Created by JFormDesigner on Mon Oct 19 11:25:59 CST 2020
 */

package com.xw.swing.education.domain.vo.table;

import com.alibaba.excel.EasyExcel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.education.domain.dto.StudentQuery;
import com.xw.swing.education.domain.vo.dialog.StudentDialog;
import com.xw.swing.education.service.StudentService;
import com.xw.swing.education.service.impl.StudentServiceImpl;
import com.xw.swing.education.util.SwingUtil;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brainrain
 */
public class StudentTableView extends JPanel {
    private List<StudentVO> students = ObservableCollections.observableList(new ArrayList<>());
    private Integer totalNum = 0;
    private StudentService studentService = new StudentServiceImpl();
    private StudentQuery studentQuery = new StudentQuery();
    private java.util.List<StudentVO.Sex> sex = Arrays.asList(StudentVO.Sex.values());


    public StudentTableView() {
        PageWrapper<StudentVO> page = studentService.page(this.studentQuery);
        this.students.addAll(page.getData());
        this.totalNum = page.getTotalNum();
        initComponents();
        SwingUtil.fitTableColumns(this.studentTable, 0);
    }

    private void pageStateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        //JOptionPane.showMessageDialog(null, "page changed");
        students.clear();
        StudentQuery studentQuery = new StudentQuery();
        studentQuery.setPageNum(Integer.parseInt(String.valueOf(source.getValue())));
        PageWrapper<StudentVO> page = studentService.page(studentQuery);
        students.addAll(page.getData());
        totalNum = page.getTotalNum();
    }

    private void search(ActionEvent e) {
        PageWrapper<StudentVO> page = studentService.page(this.studentQuery);
        students.clear();
        students.addAll(page.getData());
        totalNum = page.getTotalNum();
    }

    private void newStudent(ActionEvent e) {
        StudentVO studentFrom = showTaskDialog(new StudentVO(), "新增");
        if (studentFrom == null) {
            return;
        }
        String id = studentService.saveStudent(studentFrom);
        StudentVO studentVO = studentService.getById(id);
        students.add(studentVO);
        //System.out.println( );;
        int row = students.size() - 1;
        this.studentTable.setRowSelectionInterval(row, row);
        this.studentTable.scrollRectToVisible(this.studentTable.getCellRect(row, 0, true));
    }

    private StudentVO showTaskDialog(StudentVO studentVO, String title) {
        StudentDialog studentDialog = new StudentDialog();
        studentDialog.setStudentVO(studentVO);
        JOptionPane optionPane = new JOptionPane(studentDialog, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, title);
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue())) {
            return null;
        }
        return studentDialog.getStudentVO();
    }


    private void removeStudent(ActionEvent e) {
        Object id = this.studentTable.getValueAt(this.studentTable.getSelectedRow(), 0);
        Object name = this.studentTable.getValueAt(this.studentTable.getSelectedRow(), 1);
        if (id == null) {
            JOptionPane.showMessageDialog(null, "未发现选中的学生");
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "确定要删除 学生【" + name + "】?");
        // 确定
        if (i == 0) {
            studentService.removeStudent(String.valueOf(id));
            // 刷新Table
            PageWrapper<StudentVO> page = studentService.page(studentQuery);
            this.students.clear();
            this.students.addAll(page.getData());
            this.totalNum = page.getTotalNum();
        }
    }

    private void updateStudent(ActionEvent e) {
        int selectedRow = this.studentTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        StudentVO studentVO = students.get(selectedRow);
        StudentVO newStudent = showTaskDialog(new StudentVO(studentVO), "更新学生信息");
        if (newStudent == null) {
            return;
        }
        studentService.updateStudent(newStudent);
        students.set(selectedRow, newStudent);
    }

    private void exportStudent(ActionEvent e) {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        String fileName = desktopPath + "/学生信息" + System.currentTimeMillis() + ".xlsx";
        List<StudentExcel> data = this.students.stream().map(x -> {
            StudentExcel studentExcel = new StudentExcel();
            studentExcel.setId(x.getId());
            studentExcel.setName(x.getName());
            studentExcel.setAddress(x.getAddress());
            studentExcel.setPhone(x.getPhone());
            studentExcel.setSex(x.getSex().name());
            return studentExcel;
        }).collect(Collectors.toList());
        JOptionPane.showMessageDialog(null, "导出学生信息至["+desktopPath+"] 共:" +data.size()+"条");
        EasyExcel.write(fileName, StudentExcel.class).sheet("学生信息").doWrite(data);
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        Integer lodTotalNum = totalNum;
        this.totalNum = totalNum;
        firePropertyChange("totalNum", lodTotalNum, totalNum);
    }

    public List<StudentVO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentVO> students) {
        List<StudentVO> oldStudents = students;
        this.students = students;
        firePropertyChange("students", oldStudents, students);
    }

    public StudentQuery getStudentQuery() {
        return studentQuery;
    }

    public void setStudentQuery(StudentQuery studentQuery) {
        StudentQuery oldStudentQuery = studentQuery;
        this.studentQuery = studentQuery;
        firePropertyChange("studentQuery", oldStudentQuery, studentQuery);
    }

    public List<StudentVO.Sex> getSex() {
        return sex;
    }

    public void setSex(List<StudentVO.Sex> sex) {
        this.sex = sex;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        scrollPane1 = new JScrollPane();
        studentTable = new JTable();
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
        popupMenu1 = new JPopupMenu();
        menuItem3 = new JMenuItem();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();

        //======== this ========
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- studentTable ----
            studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            studentTable.setComponentPopupMenu(popupMenu1);
            studentTable.setPreferredScrollableViewportSize(new Dimension(450, 310));
            studentTable.setBorder(null);
            scrollPane1.setViewportView(studentTable);
        }
        add(scrollPane1, BorderLayout.CENTER);

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
            button10.addActionListener(e -> exportStudent(e));
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
                button1.addActionListener(e -> search(e));
                panel3.add(button1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button9 ----
                button9.setText("\u65b0\u589e");
                button9.addActionListener(e -> newStudent(e));
                panel3.add(button9, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            panel4.add(panel3, BorderLayout.CENTER);
        }
        add(panel4, BorderLayout.EAST);

        //======== popupMenu1 ========
        {

            //---- menuItem3 ----
            menuItem3.setText("\u65b0\u589e");
            menuItem3.setFont(menuItem3.getFont().deriveFont(menuItem3.getFont().getStyle() | Font.BOLD));
            menuItem3.addActionListener(e -> newStudent(e));
            popupMenu1.add(menuItem3);

            //---- menuItem1 ----
            menuItem1.setText("\u4fee\u6539");
            menuItem1.addActionListener(e -> updateStudent(e));
            popupMenu1.add(menuItem1);

            //---- menuItem2 ----
            menuItem2.setText("\u5220\u9664");
            menuItem2.addActionListener(e -> removeStudent(e));
            popupMenu1.add(menuItem2);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        {
            JTableBinding binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                this, (BeanProperty) BeanProperty.create("students"), studentTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("id"))
                .setColumnName("Id")
                .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("name"))
                .setColumnName("Name")
                .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("address"))
                .setColumnName("Address")
                .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("phone"))
                .setColumnName("Phone")
                .setColumnClass(String.class);
            JTableBinding.ColumnBinding columnBinding = binding.addColumnBinding(BeanProperty.create("sex"))
                .setColumnName("Sex")
                .setColumnClass(StudentVO.Sex.class);
            columnBinding.setSourceNullValue("\"\"");
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("pageNum"),
            spinner1, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("totalNum"),
            label3, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("studentQuery.name"),
            textField1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("studentQuery.address"),
            textField2, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("studentQuery.phone"),
            textField3, BeanProperty.create("text")));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("sex"), comboBox1));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("studentQuery.sex"),
            comboBox1, BeanProperty.create("selectedItem")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable studentTable;
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
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
