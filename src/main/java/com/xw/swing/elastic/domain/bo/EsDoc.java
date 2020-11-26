package com.xw.swing.elastic.domain.bo;

import com.alibaba.fastjson.JSONObject;

public class EsDoc {
    private JSONObject settings;
    private  JSONObject mappings;

    public JSONObject getSettings() {
        return settings;
    }

    public void setSettings(JSONObject settings) {
        this.settings = settings;
    }

    public JSONObject getMappings() {
        return mappings;
    }

    public void setMappings(JSONObject mappings) {
        this.mappings = mappings;
    }
}
