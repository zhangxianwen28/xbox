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

    public void setId(String id) {
        String old = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", old, id);
    }

    public void setIndexName(String indexName) {
        String old = this.indexName;
        this.indexName = indexName;
        changeSupport.firePropertyChange("indexName", old, indexName);

    }

    public void setIndexAlia(String indexAlia) {
        String old = this.indexAlia;
        this.indexAlia = indexAlia;
        changeSupport.firePropertyChange("indexAlia", old, indexAlia);

    }

    public void setStatus(String status) {
        String old = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", old, status);

    }
}
