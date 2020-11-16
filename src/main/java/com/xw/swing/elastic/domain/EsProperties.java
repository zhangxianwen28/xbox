package com.xw.swing.elastic.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class EsProperties {
    public List<JSONObject> properties;

    public EsProperties(List<JSONObject> properties) {
        this.properties = properties;
    }

    public List<JSONObject> getProperties() {
        return properties;
    }

    public void setProperties(List<JSONObject> properties) {
        this.properties = properties;
    }
}
