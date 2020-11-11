package com.xw.swing.education.dao;

import com.xw.swing.education.domain.dto.StudentQuery;
import com.xw.swing.education.domain.entity.Student;
import com.xw.swing.education.util.RandomStudentUtil;
import org.junit.Test;

import java.util.List;

public class StudentDAOTest {

    @Test
    public void createStudentTable() {
        StudentDAO studentDAO = new StudentDAO();
        System.out.println(studentDAO.createStudentTable());
    }    @Test
    public void random() {
        StudentDAO studentDAO = new StudentDAO();

        System.out.println(studentDAO.selectRandomStudent());

    }
    @Test
    public void dropTable() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.dropStudentTable();
    }
    @Test
    public void insertStudent() {
        StudentDAO studentDAO = new StudentDAO();

        RandomStudentUtil randomStudentUtil = new RandomStudentUtil();
        List<Student> students = randomStudentUtil.randomStudent(1000);
        for (Student student : students) {
            studentDAO.insertStudent(student);
        }
        System.out.println("ok");
    }

    @Test
    public void updateStudent() {
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student();
        student.setId("1");
        student.setName("老师");
        student.setAddress("中国");
        student.setPhone("11200213213");
        student.setSex("男");
        System.out.println(studentDAO.updateStudent(student));
    }

    @Test
    public void deleteStudentById() {
        StudentDAO studentDAO = new StudentDAO();

        System.out.println(studentDAO.deleteStudentById("1"));
    }

    @Test
    public void selectStudentById() {
        StudentDAO studentDAO = new StudentDAO();

        System.out.println(studentDAO.selectStudentById("1"));


    }

    @Test
    public void selectStudentByQueryCondition() {
        StudentDAO studentDAO = new StudentDAO();
        StudentQuery query = new StudentQuery();

        List<Student> students = studentDAO.selectStudentByCondition(query);
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}