package com.xw.bean.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
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
        List<Tree<DocProperties>> list =  new ArrayList<>();
        DocProperties docProperties= new DocProperties();
        docProperties.setName("school");
        docProperties.setDocType(DocType.KEYWORD);
        docProperties.setComment("ASA");
        docProperties.setCopyTo("11");
        docProperties.setAnalyzer("AA");
        docProperties.setFields("AA");
        docProperties.setPid("0");
        docProperties.setId("1");


        DocProperties docProperties2= new DocProperties();
        docProperties2.setId("3");
        docProperties2.setPid("1");
        docProperties2.setName("2222");
        docProperties2.setDocType(DocType.KEYWORD);
        docProperties2.setComment("222");
        docProperties2.setCopyTo("22");
        docProperties2.setAnalyzer("22");
        docProperties2.setFields("2222");

        list.add(docProperties);
        list.add(docProperties2);
        List<Tree<DocProperties>> trees = Tree.buildTree(list);
        trees.forEach(System.out::println);
    }


}
