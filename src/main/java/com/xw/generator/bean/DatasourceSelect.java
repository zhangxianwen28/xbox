package com.xw.generator.bean;

import java.util.*;

public class DatasourceSelect {
    private List<String> driverTypeSelect = new ArrayList<>(Arrays.asList("mysql", "oracle"));
    private List<String> dataSourceInfosSelect =new ArrayList<>();

    public List<String> getDriverTypeSelect() {
        return driverTypeSelect;
    }

    public void setDriverTypeSelect(List<String> driverTypeSelect) {
        this.driverTypeSelect = driverTypeSelect;
    }

    public List<String> getDataSourceInfosSelect() {
        return dataSourceInfosSelect;
    }

    public void setDataSourceInfosSelect(List<String> dataSourceInfosSelect) {
        this.dataSourceInfosSelect = dataSourceInfosSelect;
    }

}
