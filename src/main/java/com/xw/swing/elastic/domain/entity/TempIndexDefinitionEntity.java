package com.xw.swing.elastic.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name="TEMP_INDEX_DEFINITION")
public class TempIndexDefinitionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id	//主键id
    @Column(name="id")//数据库字段名
    private String id;

    @Column(name = "PID")
    private String pid;

    @Column(name = "indexId")
    private String indexId;

    @Column(name = "FIELD_NAME")
    private String fieldName;

    @Column(name = "FIELD_TYPE")
    private String fieldType;

    @Column(name = "FIELD_COMMENT")
    private String fieldComment;

    @Column(name = "COPYTO")
    private String copyto;

    @Column(name = "ANALYZER")
    private String analyzer;

    @Column(name = "IS_FIELDS")
    private Boolean isFields;


}
