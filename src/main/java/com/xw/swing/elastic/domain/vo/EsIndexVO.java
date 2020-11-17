package com.xw.swing.elastic.domain.vo;

import com.xw.swing.education.util.AbstractModelObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;


@Data
@EqualsAndHashCode(callSuper = false)
public class EsIndexVO extends AbstractModelObject {
    private String id;
    private String indexName;
    private String indexAlia;
    private String status;
    private Integer number_of_shards = 0;
    private Integer number_of_replicas = 0;
    private Integer max_result_window = 0;
    private Boolean dynamic = false;
}
