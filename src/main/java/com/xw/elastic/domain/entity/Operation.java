package com.xw.elastic.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class Operation {
    @Column(name="createBy")
    private  String createBy;
    @Column(name="createDate")
    private  String createDate;
    @Column(name="modifyBy")
    private LocalDateTime modifyBy;
    @Column(name="modifyDate")
    private  LocalDateTime modifyDate;
}
