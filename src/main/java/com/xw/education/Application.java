package com.xw.education;

import com.formdev.flatlaf.FlatDarkLaf;
import com.xw.education.dao.StudentDAO;
import com.xw.education.domain.entity.Student;
import com.xw.education.domain.vo.Education;

import javax.swing.*;



public class Application {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        start();

    }


    private static  void init (){
        StudentDAO studentDAO = new StudentDAO();
        Integer integer = studentDAO.countStudent();
        if(integer==-1){
            studentDAO.createStudentTable();
            Student student = new Student();
            student.setName("测试");
            student.setAddress("北京");
            student.setPhone("1581111111");
            student.setSex("男");
            studentDAO.insertStudent(student);
        }

    }
    private static void start() {
        init();
        Education box = new Education();
        box.setVisible(true);
    }
}
