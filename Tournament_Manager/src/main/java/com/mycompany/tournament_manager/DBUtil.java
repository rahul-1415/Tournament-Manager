/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tournament_manager;
/**
 *
 * @author Rahul
 */

import java.sql.*;


public class DBUtil {

    public static Connection getConnection() {
        Connection connect = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/steps?characterEncoding=latin1&useConfigs=maxPerformance", "root", "System007");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connect;
    }
}
