package com.xw.education.dao;

import com.xw.education.domain.dto.StudentQuery;
import com.xw.education.domain.entity.Student;
import com.xw.education.util.SnowFlakeUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDAO {
    public boolean createStudentTable() {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = h2DB.getConn();
            String sql = "create table t_student (id VARCHAR(30) PRIMARY KEY,name VARCHAR(100),address VARCHAR(500),phone VARCHAR(500),sex VARCHAR(50))";
            ps = conn.prepareStatement(sql);
            boolean execute = ps.execute();
            conn.commit();
            return execute;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return false;
    }

    public boolean dropStudentTable() {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = h2DB.getConn();
            String sql = "drop table t_student";
            ps = conn.prepareStatement(sql);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return false;
    }

    public String insertStudent(Student student) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());;
        try {
            conn = h2DB.getConn();
            String sql = "insert into t_student values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, student.getName());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getSex());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return id;
    }

    public boolean updateStudent(Student student) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = h2DB.getConn();
            String sql = "update  t_student set  name = ?,address = ?,phone =?,sex =? where id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getSex());
            ps.setString(5, student.getId());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return false;
    }

    public boolean deleteStudentById(String id) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = h2DB.getConn();
            String sql = "delete  from t_student where id =? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return false;
    }

    public Student selectRandomStudent() {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = h2DB.getConn();
            String sql = "select * from t_student ORDER BY RAND() limit 1";
            ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String sex = resultSet.getString(5);
                Student student = new Student();
                student.setId(sid);
                student.setName(name);
                student.setAddress(address);
                student.setPhone(phone);
                student.setSex(sex);
                return student;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return null;

    }

    public Student selectStudentById(String id) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = h2DB.getConn();
            String sql = "select * from t_student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String sex = resultSet.getString(5);
                Student student = new Student();
                student.setId(sid);
                student.setName(name);
                student.setAddress(address);
                student.setPhone(phone);
                student.setSex(sex);
                return student;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return null;

    }

    public List<Student> selectStudentByCondition(StudentQuery query) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = h2DB.getConn();
            List<String> list = new LinkedList<>();
            StringBuilder sql = new StringBuilder("select * from t_student where 1 =1 ");
            if (query.getName() != null && !"".equals(query.getName())) {
                sql.append("and name = ?");
                list.add(query.getName());
            }
            if (query.getAddress() != null && !"".equals(query.getAddress())) {
                sql.append("and address = ?");
                list.add(query.getAddress());
            }
            if (query.getPhone() != null && !"".equals(query.getPhone())) {
                sql.append("and phone = ?");
                list.add(query.getPhone());
            }
            if (query.getSex() != null && !"".equals(query.getSex())) {
                sql.append("and sex = ?");
                list.add(query.getSex().getCode());
            }
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < list.size(); i++) {
                ps.setString(i + 1, list.get(i));
            }
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String sex = resultSet.getString(5);

                Student student = new Student();
                student.setId(sid);
                student.setName(name);
                student.setAddress(address);
                student.setPhone(phone);
                student.setSex(sex);

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return students;

    }

    public Integer countStudent() {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = h2DB.getConn();
            StringBuilder sql = new StringBuilder("select count(1) from t_student");
            ps = conn.prepareStatement(sql.toString());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
        } finally {
            H2DB.closeConn(ps, conn);
        }
        return -1;

    }

    public List<Student> selectStudentPageByCondition(StudentQuery query) {
        H2DB h2DB = H2DB.getInstant();
        PreparedStatement ps = null;
        Connection conn = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = h2DB.getConn();


            StringBuilder sql = new StringBuilder("select a.* from t_student a where 1 =1 ");
            if (query.getName() != null && !"".equals(query.getName())) {
                sql.append("and a.name = ?");

            }
            if (query.getAddress() != null && !"".equals(query.getAddress())) {
                sql.append("and a.address = ?");

            }
            if (query.getPhone() != null && !"".equals(query.getPhone())) {
                sql.append("and a.phone = ?");

            }
            if (query.getSex() != null && !"".equals(query.getSex())) {
                sql.append("and a.sex = ?");
            }

            sql.append(" limit ? , ?");

            ps = conn.prepareStatement(sql.toString());
            System.out.println("selectStudentPageByCondition: " + sql.toString());

            int i = 0;
            if (query.getName() != null && !"".equals(query.getName())) {
                ps.setString(++i, query.getName());
            }
            if (query.getAddress() != null && !"".equals(query.getAddress())) {
                ps.setString(++i, query.getAddress());

            }
            if (query.getPhone() != null && !"".equals(query.getPhone())) {
                ps.setString(++i, query.getPhone());

            }
            if (query.getSex() != null && !"".equals(query.getSex())) {
                ps.setString(++i, query.getSex().getCode());
            }
            ps.setInt(++i, (query.getPageNum() - 1) * query.getPageSize());
            ps.setInt(++i, query.getPageSize());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String sid = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String sex = resultSet.getString(5);

                Student student = new Student();
                student.setId(sid);
                student.setName(name);
                student.setAddress(address);
                student.setPhone(phone);
                student.setSex(sex);

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DB.closeConn(ps, conn);
        }

        return students;

    }

}
