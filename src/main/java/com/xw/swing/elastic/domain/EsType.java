package com.xw.swing.elastic.domain;

public class EsType {
    private  String type;
    private  Boolean enabled;

    public EsType(String type, Boolean enabled) {
        this.type = type;
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
