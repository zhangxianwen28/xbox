/*
 * Created by JFormDesigner on Tue Oct 20 14:19:34 CST 2020
 */

package com.xw.education.domain.vo.call;

import java.awt.event.*;
import com.xw.education.domain.vo.table.StudentVO;
import com.xw.education.service.StudentService;
import com.xw.education.service.impl.StudentServiceImpl;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.observablecollections.ObservableCollections;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.jdesktop.swingbinding.*;

/**
 * @author Brainrain
 */
public class StudentCall extends JPanel {
    private StudentService studentService = new StudentServiceImpl();

    private java.util.List<String> studentList = ObservableCollections.observableList(new ArrayList<>());;


    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        List<String> oldStudentList = studentList;
        this.studentList = studentList;
        firePropertyChange("studentList",oldStudentList,studentList);
    }

    public StudentCall() {
        initComponents();
    }

    private void randomStudent(ActionEvent e) {
        studentList.clear();
        StudentVO studentVO = studentService.selectRandomStudent();
        studentList.add("姓名: "+studentVO.getName());
        studentList.add("地址: "+studentVO.getAddress());
        studentList.add("电话: "+studentVO.getPhone());
        studentList.add("性别: "+studentVO.getSex().name());

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new JPanel();
        button1 = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());

            //---- button1 ----
            button1.setText("\u70b9\u540d");
            button1.addActionListener(e -> randomStudent(e));
            panel1.add(button1);
        }
        add(panel1, BorderLayout.NORTH);

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(SwingBindings.createJListBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("studentList"), list1));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList list1;
    private JPanel panel1;
    private JButton button1;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
