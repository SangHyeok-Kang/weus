package com.project.weus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//데이터베이스의 연결 소스를 클래스로 지정하여 하나의 라이브러리처럼 사용
public class DBConnection {

    public static final DBConnection dbconnection = new DBConnection();

    static Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    static private Connection con = null;
    static private Statement st = null;
    private ResultSet rs = null;

    public static DBConnection getInstance() {
        return dbconnection;
    }

    public static Statement getStatement() {
        return st;
    }

    public static Connection getConnection() {
        System.out.println(con);
        return con;

    }

    public DBConnection() {

    }

    public boolean Initailize() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost/weus?characterEncoding=UTF-8&serverTimezone=UTC", "root", "1q2w3e4r");

            st = con.createStatement();
            System.out.println("Connection Success");

            return true;

        } catch (SQLException e) {
            System.out.println("데이터 베이스 연결 오류 : " + e.getMessage());
        }
        return false;
    }

}
