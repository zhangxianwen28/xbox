package com.xw.swing.education.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageWrapper<T> {
    private Integer current;
    private Integer size;
    private List<T> data;
    private Integer totalNum;
}
