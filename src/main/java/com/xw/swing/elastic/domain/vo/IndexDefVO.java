package com.xw.swing.elastic.domain.vo;

import com.xw.swing.education.util.AbstractModelObject;
import com.xw.swing.elastic.domain.DocType;
import lombok.Data;

import javax.persistence.Column;


public class IndexDefVO extends AbstractModelObject {
    // ID
    private String id;

    private String indexId;

    // 父节点
    private String pid;
    // 字段名称
    private String fieldName;
    // 字段类型
    private DocType fieldType;
    // 字段注释
    private String fieldComment;
    // 复制到
    private String copyTo;
    // 分析
    private String analyzer;
    // 领域
    private Boolean isFields;

    public void setId(String id) {
        String oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);

    }

    public void setPid(String pid) {
        String oldPid = this.pid;
        this.pid = pid;
        changeSupport.firePropertyChange("pid", oldPid, pid);
    }

    public void setName(String fieldName) {
        String oldDocName = this.fieldName;
        this.fieldName = fieldName;
        changeSupport.firePropertyChange("fieldName", oldDocName, fieldName);
    }

    public void setDocType(DocType fieldType) {
        DocType oldDocType = this.fieldType;
        this.fieldType = fieldType;
        changeSupport.firePropertyChange("fieldType", oldDocType, fieldType);
    }

    public void setComment(String fieldComment) {
        String oldComment = this.fieldComment;
        this.fieldComment = fieldComment;
        changeSupport.firePropertyChange("fieldComment", oldComment, fieldComment);
    }

    public void setCopyTo(String copyTo) {
        String oldCopyTo = this.copyTo;
        this.copyTo = copyTo;
        changeSupport.firePropertyChange("copyTo", oldCopyTo, copyTo);
    }

    public void setAnalyzer(String analyzer) {
        String oldAnalyzer = this.analyzer;
        this.analyzer = analyzer;
        changeSupport.firePropertyChange("analyzer", oldAnalyzer, analyzer);
    }



    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(DocType fieldType) {
        this.fieldType = fieldType;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }


    public String getId() {
        return id;
    }

    public String getPid() {
        return pid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DocType getFieldType() {
        return fieldType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getCopyTo() {
        return copyTo;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public Boolean getFields() {
        return isFields;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public void setFields(Boolean fields) {
        Boolean oldFields = this.isFields;
        this.isFields = fields;
        changeSupport.firePropertyChange("isFields", oldFields, fields);
    }

    @Override
    public String toString() {
        return this.fieldName;
    }
}
