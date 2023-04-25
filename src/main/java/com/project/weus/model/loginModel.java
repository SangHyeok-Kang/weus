/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.weus.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author host
 */
@Slf4j
public class loginModel {

    private String mysqlServerIp;
    private String mysqlServerPort;
    private String userName;
    private String pass;
    private String jdbcDriver;

    public loginModel(String mysqlServerIp, String mysqlServerPort, String userName, String pass, String jdbcDriver) {
        this.mysqlServerIp = mysqlServerIp;
        this.mysqlServerPort = mysqlServerPort;
        this.userName = userName;
        this.pass = pass;
        this.jdbcDriver = jdbcDriver;
        log.debug("AddrBookManager(): mysqlServerIp = {}, jdbvDriver = {}", mysqlServerIp, jdbcDriver);
    }

    public boolean loginResult(String userid, String password) {

        final String JDBC_URL = String.format("jdbc:mysql://%s:%s/weus?serverTimezone=Asia/Seoul", this.mysqlServerIp, this.mysqlServerPort);
        log.debug("JDBC_URL = {}, mysqlServerIp = {}, jdbcDriver = {}", JDBC_URL, mysqlServerIp, jdbcDriver);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(JDBC_URL, userName, pass);
            stmt = conn.createStatement();
            String sql = "SELECT userid, password FROM userinfo WHERE userid= '" + userid + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String user = rs.getString("userid");
                System.out.println(user);
                return true;
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            log.error("오류가 발생했습니다. (발생 오류: {})", ex.getMessage());
        }
        return false;
    }
}
