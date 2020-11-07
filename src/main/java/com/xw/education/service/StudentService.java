package com.xw.education.service;

import com.xw.education.domain.dto.PageWrapper;
import com.xw.education.domain.dto.StudentQuery;
import com.xw.education.domain.vo.table.StudentVO;

public interface StudentService {

    PageWrapper<StudentVO> page(StudentQuery studentQuery);

    Integer count();

    String saveStudent(StudentVO studentVO);

    StudentVO getById(String id);

    void updateStudent(StudentVO studentFrom);

    void removeStudent(String id);

    StudentVO selectRandomStudent();
}
