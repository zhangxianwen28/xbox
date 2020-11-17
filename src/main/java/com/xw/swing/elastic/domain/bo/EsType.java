package com.xw.swing.elastic.domain.bo;

import java.io.Serializable;

public class EsType  implements Serializable {
    private  String type;
    private  Boolean enabled;

    public EsType() {
    }

    public EsType(String type, Boolean enabled) {
        this.type = type;
        this.enabled = enabled;
    }

    public EsType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "EsType{" +
                "type='" + type + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
