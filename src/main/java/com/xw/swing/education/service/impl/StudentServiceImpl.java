package com.xw.swing.education.service.impl;


import com.xw.swing.education.dao.StudentDAO;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.education.domain.dto.StudentQuery;
import com.xw.swing.education.domain.entity.Student;
import com.xw.swing.education.domain.vo.table.StudentVO;
import com.xw.swing.education.service.StudentService;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private StudentDAO dao = new StudentDAO();

    @Override
    public PageWrapper<StudentVO> page(StudentQuery studentQuery) {
        PageWrapper<StudentVO> pageWrapper = new PageWrapper<>();
        List<Student> students = dao.selectStudentPageByCondition(studentQuery);
        List<StudentVO> collect = students.stream().map(x -> {
            StudentVO vo = new StudentVO();
            vo.setId(x.getId());
            vo.setName(x.getName());
            vo.setAddress(x.getAddress());
            vo.setPhone(x.getPhone());
            vo.setSex(StudentVO.Sex.getByCode(x.getSex()));
            return vo;
        }).collect(Collectors.toList());
        pageWrapper.setCurrent(studentQuery.getPageNum());
        pageWrapper.setData(collect);
        pageWrapper.setSize(studentQuery.getPageSize());
        pageWrapper.setTotalNum(count());
        return pageWrapper;
    }


    @Override
    public Integer count() {
        return dao.countStudent();
    }

    @Override
    public String saveStudent(StudentVO studentVO) {
        Student student = new Student();
        student.setName(studentVO.getName());
        student.setAddress(studentVO.getAddress());
        student.setPhone(studentVO.getPhone());
        student.setSex(studentVO.getSex().getCode());
        return dao.insertStudent(student);
    }

    @Override
    public StudentVO getById(String id) {
        Student student = dao.selectStudentById(id);
        StudentVO studentVO = new StudentVO();
        studentVO.setId(student.getId());
        studentVO.setName(student.getName());
        studentVO.setAddress(student.getAddress());
        studentVO.setPhone(student.getPhone());
        studentVO.setSex(StudentVO.Sex.getByCode(student.getSex()));
        return studentVO;
    }

    @Override
    public void updateStudent(StudentVO studentFrom) {
        Student student = new Student();
        student.setId(studentFrom.getId());
        student.setName(studentFrom.getName());
        student.setAddress(studentFrom.getAddress());
        student.setPhone(studentFrom.getPhone());
        student.setSex(studentFrom.getSex().getCode());
        dao.updateStudent(student);
    }

    @Override
    public void removeStudent(String id) {
        dao.deleteStudentById(id);
    }

    @Override
    public StudentVO selectRandomStudent() {
        Student student = dao.selectRandomStudent();
        StudentVO vo = new StudentVO();
        vo.setId(student.getId());
        vo.setName(student.getName());
        vo.setAddress(student.getAddress());
        vo.setPhone(student.getPhone());
        vo.setSex(StudentVO.Sex.getByCode(student.getSex()));
        return vo;
    }

}
