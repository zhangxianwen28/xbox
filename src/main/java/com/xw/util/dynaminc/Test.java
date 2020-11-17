package com.xw.util.dynaminc;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Daisy");
        System.out.println("User："+ JSON.toJSONString(user));
        Map<String,Object> propertiesMap = new HashMap<String,Object>();
        propertiesMap.put("age", 18);
        Object obj = ReflectUtil.getObject(user, propertiesMap);
        System.out.println("动态为User添加age之后，User："+JSON.toJSONString(obj));
    }

}
