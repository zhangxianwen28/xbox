package com.xw.generator.bean;

import java.util.HashMap;
import java.util.Map;

public class DataSource {
    private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

    public static Map<String, DataSourceInfo> dataSourceInfoMap = new HashMap<>();

    static {
        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        dataSourceInfo.setSelectDriverType("oracle");
        dataSourceInfo.setName("研发数据库");
        dataSourceInfo.setDescription("crdms_publish-36");
        dataSourceInfo.setUrl("jdbc:oracle:thin:@10.10.16.36:1521:orcl");
        dataSourceInfo.setUsername("crdms_publish");
        dataSourceInfo.setPassword("crdms_publish");
        dataSourceInfo.setDriverName(DRIVER_NAME);
        dataSourceInfoMap.put(dataSourceInfo.getName(), dataSourceInfo);
    }
}
