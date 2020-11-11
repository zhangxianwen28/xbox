package com.xw.swing.generator.util;

public class XUtils {

    public static  String getDriverName(String DriverName) {
        switch (DriverName) {
            case "mysql":
                return "";
            case "oracle":
                return "oracle.jdbc.driver.OracleDriver";
        }
        return "";
    }
}
