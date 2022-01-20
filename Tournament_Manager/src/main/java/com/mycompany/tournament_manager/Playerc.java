/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tournament_manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
/**
 *
 * @author Rahul
 */
public class Playerc {
    
    public void update(String name, String phn, String email, int id) {
        
        int pid = 0;
        String sql = "Select PlayerID from user where UserID='"+id+"'";
        String sql1 = "Update user SET Name = ?, Email = ? WHERE UserID = ?";
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            PreparedStatement preparedStmt = connect.prepareStatement(sql1);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, email);
            preparedStmt.setInt(3, id);
            while (resultSet.next()) {
                pid = resultSet.getInt("PlayerID");
            }
            preparedStmt.executeUpdate();
            String sql2 = "Update player SET PlayerName = ? , PlayerPhone = ? WHERE PlayerID = ?";
            PreparedStatement preparedStmt1 = connect.prepareStatement(sql2);
            preparedStmt1.setString(1, name);
            preparedStmt1.setString(2, phn);
            preparedStmt1.setInt(3, pid);
            preparedStmt1.executeUpdate();
            resultSet.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
        }
        
        
        
    }
    
}
