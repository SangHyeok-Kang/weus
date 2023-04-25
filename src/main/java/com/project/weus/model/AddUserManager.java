/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.weus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author host
 */
@Slf4j
public class AddUserManager {

    private String mysqlServerIp;
    private String mysqlServerPort;
    private String userName;
    private String pass;
    private String jdbcDriver;

    public AddUserManager() {
        log.debug("AddrBookManager(): mysqlServerIp = {}, jdbvDriver = {}", mysqlServerIp, jdbcDriver);
    }

    public AddUserManager(String mysqlServerIp, String mysqlServerPort, String userName, String pass, String jdbcDriver) {
        this.mysqlServerIp = mysqlServerIp;
        this.mysqlServerPort = mysqlServerPort;
        this.userName = userName;
        this.pass = pass;
        this.jdbcDriver = jdbcDriver;
        log.debug("AddrBookManager(): mysqlServerIp = {}, jdbvDriver = {}", mysqlServerIp, jdbcDriver);
    }

    public boolean checkId(String userid) {      
        final String JDBC_URL = String.format("jdbc:mysql://%s:%s/weus?serverTimezone=Asia/Seoul", this.mysqlServerIp, this.mysqlServerPort);
        log.debug("JDBC_URL = {}, mysqlServerIp = {}, jdbcDriver = {}", JDBC_URL, mysqlServerIp, jdbcDriver);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(JDBC_URL, userName, pass);
            stmt = conn.createStatement();
            String sql = "SELECT userid FROM userinfo WHERE userid= '" + userid + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String user = rs.getString("userid");
                System.out.println(user);
                return false;
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            log.error("오류가 발생했습니다. (발생 오류: {})", ex.getMessage());
        }
        return true;
    }

    public void addRow(String userid, String password, String name, String phone, String birthdate, String schoolinfo, String interest, String city, String district, String street, String detail, String gender) {
        final String JDBC_URL = String.format("jdbc:mysql://%s:%s/weus?serverTimezone=Asia/Seoul", mysqlServerIp, mysqlServerPort);

        log.debug("JDBC_URL = {}", JDBC_URL);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(jdbcDriver);

            conn = DriverManager.getConnection(JDBC_URL, this.userName, this.pass);
            String sql = "INSERT INTO userinfo VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userid);
            pstmt.setString(2, "학생");
            pstmt.setString(3, password);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);
            pstmt.setString(6, birthdate);
            pstmt.setString(7, schoolinfo);
            pstmt.setString(8, interest);
            pstmt.setString(9, gender);

            pstmt.executeUpdate();
            
            sql = "INSERT INTO useraddress VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userid);
            pstmt.setString(2, city);
            pstmt.setString(3, district);
            pstmt.setString(4, street);
            pstmt.setString(5, detail);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            

        } catch (Exception ex) {
            log.error("오류가 발생했습니다. (발생오류: {})", ex.getMessage());
        }
    }

    public void addRow(String userid, String password, String name, String phone, String birthdate, String major, String city, String district, String street, String detail, String gender) {
       final String JDBC_URL = String.format("jdbc:mysql://%s:%s/weus?serverTimezone=Asia/Seoul", mysqlServerIp, mysqlServerPort);

        log.debug("JDBC_URL = {}", JDBC_URL);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(jdbcDriver);

            conn = DriverManager.getConnection(JDBC_URL, this.userName, this.pass);
            String sql = "INSERT INTO userinfo VALUES(?,?,?,?,?,?,DEFAULT,DEFAULT,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userid);
            pstmt.setString(2, "일반");
            pstmt.setString(3, password);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);
            pstmt.setString(6, birthdate);
            pstmt.setString(7, gender);
            pstmt.setString(8, major);

            pstmt.executeUpdate();
            
            sql = "INSERT INTO useraddress VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userid);
            pstmt.setString(2, city);
            pstmt.setString(3, district);
            pstmt.setString(4, street);
            pstmt.setString(5, detail);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            

        } catch (Exception ex) {
            log.error("오류가 발생했습니다. (발생오류: {})", ex.getMessage());
        }
    }
}
