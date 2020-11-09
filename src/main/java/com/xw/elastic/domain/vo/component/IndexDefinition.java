package com.xw.elastic.domain.vo.component;

import com.xw.education.util.AbstractModelObject;
import com.xw.elastic.domain.DocType;
import lombok.Data;
import lombok.EqualsAndHashCode;


public class IndexDefinition extends AbstractModelObject {
    // ID
    private String id;
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

    public void setFields(boolean isFields) {
        boolean oldFields = this.isFields;
        this.isFields = isFields;
        changeSupport.firePropertyChange("isFields", oldFields, isFields);
    }


}
