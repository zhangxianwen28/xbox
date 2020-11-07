package com.xw.education.domain.vo.table;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentExcel {
    @ExcelProperty("id")
    private String id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("性别")
    private String sex;
}
