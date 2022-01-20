/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tournament_manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Rahul
 */
public class Loginc {
    
    public int validate(String login, String password) {
        
        String sql = "Select priveleges from user where Name='"+login+"' And Password='"+password+"'";
        int priv = 0;
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                priv = resultSet.getInt("priveleges");
            }
            resultSet.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
        }
        
        return priv;
    }
    
}
