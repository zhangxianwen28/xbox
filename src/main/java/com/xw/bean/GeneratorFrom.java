package com.xw.bean;

import lombok.Data;

@Data
public class GeneratorFrom {
    private String projectPath = System.getProperty("user.dir");
    // 全局配置
    private String buildPath = "/src/main/java";
    private boolean fileOverride;
    private String author ="zxw";
    private boolean swagger2;
    private boolean activeRecord;
    private boolean baseResultMap;
    private boolean baseColumnList;

    // 数据源配置
    private String dataSource;

    // 包配置
    private String parent = "com.xw";
    private String moduleName;
    private String tableText;
    private String[] tables;


    public String[] getTables() {
        if(this.tableText ==null || "".equals(this.tableText) ){
            return null;
        }
        return tableText.split("[,，]");
    }
}
