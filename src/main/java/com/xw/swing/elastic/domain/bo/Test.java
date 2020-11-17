package com.xw.swing.elastic.domain.bo;

import com.alibaba.fastjson.JSON;
import com.xw.util.dynaminc.ReflectUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        EsDoc esDoc = new EsDoc();
        Map<String,Object> propertiesMap = new HashMap<>();
        propertiesMap.put("age", new EsType("text",true));
        propertiesMap.put("name", new EsType("text",true));
        Object obj = ReflectUtil.getObject(new Object(), propertiesMap);
        esDoc.setMappings(obj);
        esDoc.setSettings(new Object());

        System.out.println("动态为EsDoc添加 Mapping ："+ JSON.toJSONString(esDoc));
    }

}
