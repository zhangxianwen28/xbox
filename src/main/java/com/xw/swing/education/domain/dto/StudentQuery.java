package com.xw.swing.education.domain.dto;

import com.xw.swing.education.domain.vo.table.StudentVO;
import lombok.Data;

@Data
public class StudentQuery {
    private String name;
    private String address;
    private String phone;
    private StudentVO.Sex sex;
    private Integer pageSize = 15;
    private Integer pageNum = 1;
}
