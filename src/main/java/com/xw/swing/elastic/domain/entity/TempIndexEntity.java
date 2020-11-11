package com.xw.swing.elastic.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="TEMP_INDEX")
public class TempIndexEntity extends BaseEntity {
    @Id
    @Column(name="id")
    private  String id;
    @Column(name="indexName")
    private  String indexName;
    @Column(name="indexAlia")
    private  String indexAlia;
    @Column(name="status")
    private  String status;
}
