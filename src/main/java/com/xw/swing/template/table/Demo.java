package com.xw.swing.template.table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Demo {
    private String id;
    private String name;

    public Demo(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
