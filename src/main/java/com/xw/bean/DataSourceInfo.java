package com.xw.bean;

public class DataSourceInfo {
    private String name = "";
    private String description = "";
    private String url = "";
    private String username = "";
    private String password = "";
    private String selectDriverType = "oracle";
    private String driverName ;
    public String getSelectDriverType() {
        return selectDriverType;
    }

    public void setSelectDriverType(String selectDriverType) {
        this.selectDriverType = selectDriverType;
    }

    public DataSourceInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}