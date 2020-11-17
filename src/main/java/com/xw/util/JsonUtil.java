package com.xw.util;

import com.alibaba.fastjson.JSONObject;
import com.xw.swing.elastic.domain.bo.EsType;

import java.util.List;

public class JsonUtil {

    public static  JSONObject createEsType(String name,String type){
        EsType esType = new EsType(type, true);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name,esType);
        return jsonObject;
    }


    public static  JSONObject createEsProperties(String name, List<JSONObject> jsonObjects){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject.put("properties",jsonObjects);
        jsonObject2.put(name,jsonObject);
        return jsonObject2;
    }

}
