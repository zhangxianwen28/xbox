package com.xw.education.util;

import com.xw.education.domain.entity.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomStudentUtil {

    public static void main(String[] args) {
        System.out.println(getRandomPhone());
    }

    public List<Student> randomStudent(int len) {
        List<Student> list = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            Student student = new Student();
            student.setName(RandomNameUtil.randomName(true, 3));
            student.setAddress("北京");
            student.setPhone(getRandomPhone());
            student.setSex(getRandomSex());
            list.add(student);
        }
        return list;
    }


    public static String getRandomPhone() {
        Random random = new Random();
        String l = Math.abs(random.nextLong()) + "";
        if (l.length() > 9) {
            l = l.substring(0, 8);
        }
        return "131" + l;
    }

    public String getRandomSex() {
        Random random = new Random();
        int i = random.nextInt(1);
        if (i == 0) {
            return "女";
        } else {
            return "男";
        }
    }


}
