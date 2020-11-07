package com.xw.bean.vo;

import lombok.Data;

@Data
public class DocProperties extends Tree<DocProperties>{
    // 字段名称
    private String name;
    // 字段类型
    private DocType docType;
    // 字段注释
    private String comment;
    // 复制到
    private String copyTo;
    // 分析
    private String analyzer;
    // 领域
    private String fields;

    public static void main(String[] args) {
        DocProperties docProperties= new DocProperties();
        docProperties.setName("school");
        docProperties.setDocType(DocType.KEYWORD);
        docProperties.setComment("ASA");
        docProperties.setCopyTo("11");
        docProperties.setAnalyzer("AA");
        docProperties.setFields("AA");


    }


}
