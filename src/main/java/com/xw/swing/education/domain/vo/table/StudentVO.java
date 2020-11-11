package com.xw.swing.education.domain.vo.table;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xw.swing.education.util.AbstractModelObject;


public class StudentVO extends AbstractModelObject {
    @ExcelProperty("id")
    private String id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("性别")
    private Sex sex;

    public enum Sex {
        女("0"), 男("1"),未知("2");
        private String code;

        Sex(String s) {
            this.code = s;
        }

        public String getCode() {
            return code;
        }

        public static Sex getByCode(String code) {
            if ("1".equals(code)) {
                return Sex.男;
            }
            if ("0".equals(code)) {
                return Sex.女;
            }
            return Sex.未知;
        }
    }

    public StudentVO() {
    }

    public StudentVO(String id, String name, String address, String phone, Sex sex) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
    }

    public StudentVO(StudentVO studentVO) {
        this.id = studentVO.getId();
        this.name = studentVO.getName();
        this.address = studentVO.getAddress();
        this.phone = studentVO.getPhone();
        this.sex = studentVO.getSex();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Sex getSex() {
        return sex;
    }

    public void setId(String id) {
        String oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public void setPhone(String phone) {
        String oldPhone = this.phone;
        this.phone = phone;
        changeSupport.firePropertyChange("phone", oldPhone, phone);
    }

    public void setSex(Sex sex) {
        Sex oldSex = this.sex;
        this.sex = sex;
        changeSupport.firePropertyChange("sex", oldSex, sex);
    }


}
