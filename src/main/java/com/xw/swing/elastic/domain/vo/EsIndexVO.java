package com.xw.swing.elastic.domain.vo;

import com.xw.swing.education.util.AbstractModelObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class EsIndexVO extends AbstractModelObject {
    private  String id;
    private  String indexName;
    private  String indexAlia;
    private  String status;

}
