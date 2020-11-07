package com.xw.bean.vo;

public enum DocType {
    TEXT("文本"),
    KEYWORD("关键字"),
    NUMERIC("数字"),
    DATE("日期"),
    DATE_NANOSECONDS("日期纳秒"),
    BOOLEAN("布尔"),
    BINARY("二进制"),
    OBJECT("对象"),
    NESTED("嵌套");
    private String type;

    DocType(String typeName) {
        this.type = typeName;
    }

    public String getType() {
        return type;
    }

}
