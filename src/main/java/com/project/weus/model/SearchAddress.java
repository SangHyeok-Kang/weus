/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.weus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author host
 */
@Slf4j
public class SearchAddress {
    private String mysqlServerIp;
    private String mysqlServerPort;
    private String userName;
    private String pass;
    private String jdbcDriver;
    
    public String juso;
    
     public SearchAddress() {
        log.debug("AddrBookManager(): mysqlServerIp = {}, jdbvDriver = {}", mysqlServerIp, jdbcDriver);
    }

    public SearchAddress(String mysqlServerIp, String mysqlServerPort, String userName, String pass, String jdbcDriver) {
        this.mysqlServerIp = mysqlServerIp;
        this.mysqlServerPort = mysqlServerPort;
        this.userName = userName;
        this.pass = pass;
        this.jdbcDriver = jdbcDriver;
        log.debug("AddrBookManager(): mysqlServerIp = {}, jdbvDriver = {}", mysqlServerIp, jdbcDriver);
    }

    public String checkAddress(String host) {      
        final String JDBC_URL = String.format("jdbc:mysql://%s:%s/weus?serverTimezone=Asia/Seoul", this.mysqlServerIp, this.mysqlServerPort);
        log.debug("JDBC_URL = {}, mysqlServerIp = {}, jdbcDriver = {}", JDBC_URL, mysqlServerIp, jdbcDriver);

        String postcode = "";
        String address = "";
        String detail = "";
        String extra = "";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(JDBC_URL, userName, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM useraddress WHERE userid= '" + host + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                address = rs.getString("address");
                detail = rs.getString("detail");
                extra = rs.getString("extra");
            }
            
            juso = address + " " + detail + extra;
            
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            log.error("오류가 발생했습니다. (발생 오류: {})", ex.getMessage());
        }
        return juso;
    }
}
