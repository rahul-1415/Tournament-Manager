/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tournament_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Rahul
 */
public class Coachc {
    
    public int createteam(String tname, String stat, int pc, int r, int w, int l, int cid) {
        
        String sql = "Select TeamID from Coach WHERE CoachID='"+cid+"'";
        int priv = 0;
        int priv2 = 0;
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            String sql1 = "Select TeamID from Team WHERE TeamName='"+tname+"' And Rank='"+r+"'";
            Statement statement1 = connect.createStatement();

            while (resultSet.next()) {
                priv = resultSet.getInt("TeamID");
                if(priv == 0){
                String sql2 = "INSERT INTO Team (TeamName,PlayerCount,Active,Rank,Won,lost,DivisionID) VALUES (?,?,?,?,?,?,NULL)";
            PreparedStatement preparedStmt1 = connect.prepareStatement(sql2);
            preparedStmt1.setString(1, tname);
            preparedStmt1.setInt(2, pc);
            preparedStmt1.setString(3, stat);
            preparedStmt1.setInt(4, r);
            preparedStmt1.setInt(5, w);
            preparedStmt1.setInt(6, l);
            preparedStmt1.executeUpdate();
                }
            }
            if(priv == 0){
             ResultSet resultSet1 = statement1.executeQuery(sql1);
             while (resultSet1.next()) {
            priv2 = resultSet1.getInt("TeamID");
            String sql3 = "Update coach SET TeamID = ? WHERE CoachID = ?";
            PreparedStatement preparedStmt2 = connect.prepareStatement(sql3);
            preparedStmt2.setInt(1, priv2);
            preparedStmt2.setInt(2, cid);
            preparedStmt2.executeUpdate();
             }
             resultSet1.close();
            }
            resultSet.close();
            
            statement1.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
        }
        
        return priv;
        
        //return 0;
    }
    
    public int addplayer(String pname, int pid, int cid){
    
 
        int priv = 0;
        int priv1 = 0;
        try{
                
                String sql1 = "Select TeamID from Player WHERE PlayerID = '"+pid+"'";
                
            Connection connect = DBUtil.getConnection();
            Statement statement1 = connect.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            
            while(resultSet1.next()){
                priv = resultSet1.getInt("TeamID");
                if(priv == 0){
            String sql = "Select TeamID from Coach WHERE CoachID='"+cid+"'";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                priv1 = resultSet.getInt("TeamID");
            }
            resultSet.close();
            String sql2 = "Update Player SET TeamID = ? WHERE PlayerID = ? And PlayerName=?";
            PreparedStatement preparedStmt1 = connect.prepareStatement(sql2);
            preparedStmt1.setInt(1, priv1);
            preparedStmt1.setInt(2, pid);
            preparedStmt1.setString(3, pname);
            preparedStmt1.executeUpdate();
            statement.close();
            }
                
          }
            
            resultSet1.close();
            statement1.close();
            connect.close();
        }  catch (Exception e) {
        }   
        return priv;
    }
    
    public int deleteplayer(String pname, int pid, int cid){
    
 
        int priv = 0;
        int priv1 = 0;
        int Teamp= 0;
        int Teamc = 0;
        try{
                
                String sql1 = "Select TeamID from Player WHERE PlayerID = '"+pid+"'";
                String sql = "Select TeamID from Coach WHERE CoachID='"+cid+"'";
                
            Connection connect = DBUtil.getConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Statement statement1 = connect.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            
            while(resultSet1.next()){
                Teamp = resultSet1.getInt("TeamID");
                if(Teamp == 0){
                
                    priv = Teamp;
                }else {
                    while(resultSet.next()){
                    
                        Teamc = resultSet.getInt("TeamID");
                        
                        if(Teamp == Teamc)
                        {   
                             String sql2 = "Update Player SET TeamID = NULL WHERE PlayerID = ? And PlayerName=?";
                             PreparedStatement preparedStmt1 = connect.prepareStatement(sql2);
                             preparedStmt1.setInt(1, pid);
                             preparedStmt1.setString(2, pname);
                             preparedStmt1.executeUpdate();
                            priv = 1;
                        }else {
                            
                            priv = 2;
                        }
                    
                    }
                }
          }
            resultSet.close();
            resultSet1.close();
            statement.close();
            statement1.close();
            connect.close();
        }  catch (Exception e) {
        }   
        
        
        return priv;
    }        
        
    
}
