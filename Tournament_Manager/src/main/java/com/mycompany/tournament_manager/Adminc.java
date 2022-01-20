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
public class Adminc {
    
    public int createsport(String s, String l, String t, int gmit, int gmxt, int teamsdiv, int gmits, int gmxts, int aid){
    
        int priv = 1;
        String sql = "INSERT INTO Sport (SportName,MinTeams,MaxTeams,DivisionTeams,MaxTeamSize,MinTeamSize,GameRules,IndoorOutdoor) VALUES "
                + "('"+s+"','"+gmit+"','"+gmxt+"','"+teamsdiv+"','"+gmits+"','"+gmxts+"','"+t+"','"+l+"')";
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
            priv = 0;
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return priv;
    }
    
        public int createdivision(String s, String l, String t, int dmxp, int dmxt, int sdid, int aid){
    
        int priv = 0;
        String sql = "INSERT INTO Division (DivisionName,IndoorOutdoor,TeamMax,PlayerMax,SeasonEndDate,SportID) VALUES "
                + "('"+s+"','"+l+"','"+dmxt+"','"+dmxp+"','"+t+"','"+sdid+"')";
        String sql1 = "Select Count(*) AS total from Sport WHERE SportID = '"+sdid+"'";
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement1 = connect.createStatement();
            ResultSet rs = statement1.executeQuery(sql1);
            
            while(rs.next()){
            priv = rs.getInt("total");
            if(priv == 1) {
                        Statement statement = connect.createStatement();
                        statement.executeUpdate(sql);
                        statement.close();
            }
            }
            
            statement1.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return priv;
    }
        
        public int createbracket(String s, String l, String t, int toid, int roid, int goid, int divid1, int divid2, int aid){
    
        int priv = 0;
        int priv1 = 0;
        int priv2 = 0;
        int priv3 = 1;
        String sql = "INSERT INTO tournamentbracket (TournamentID,RoundID,GameID,Venue,GameDate,Address,WinnerTeam1,WinnerTeam2) VALUES "
                + "('"+toid+"','"+roid+"','"+goid+"','"+s+"','"+l+"','"+t+"','"+divid1+"','"+divid2+"')";
        String sql1 = "Select Count(*) AS total from Tournament WHERE TournamentID = '"+toid+"'";
        String sql2 = "Select Count(*) AS total2 from Division WHERE DivisionID = '"+divid1+"'";
        String sql3 = "Select Count(*) AS total3 from Division WHERE DivisionID = '"+divid2+"'";
        if (divid1 != divid2){
        try {
            Connection connect = DBUtil.getConnection();
            Statement statement1 = connect.createStatement();
            ResultSet rs = statement1.executeQuery(sql1);
            Statement statement2 = connect.createStatement();
            ResultSet rs1 = statement2.executeQuery(sql2);
            Statement statement3 = connect.createStatement();
            ResultSet rs2 = statement3.executeQuery(sql3);            
            while(rs.next()){
            priv = rs.getInt("total");
            if(priv==0) priv3 = 0;
            }
            while(rs1.next()){
            priv1 = rs1.getInt("total2");
            if(priv1==0) priv3 = 0;
            }
            while(rs2.next()){
            priv2 = rs2.getInt("total3");
            if(priv2==0) priv3 = 0;
            }
            if(priv3 != 0){
                        Statement statement = connect.createStatement();
                        statement.executeUpdate(sql);
                        statement.close();
                        priv3 = 1;
            }
            rs.close();
            statement1.close();
            rs1.close();
            statement2.close();
            rs2.close();
            statement3.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      }else {
        priv3 = 4;
        }
        return priv3;
    } 
        
        public int deletebracket(int tour, int round, int game){
            
            int priv = 0;
            String sql = "DELETE from TournamentBracket WHERE TournamentID='"+tour+"' And RoundID = '"+round+"' And GameID = '"+game+"'";
            String sql1 = "SELECT Count(*) AS total from TournamentBracket WHERE TournamentID='"+tour+"' And RoundID = '"+round+"' And GameID = '"+game+"'";
            try {
                Connection connect = DBUtil.getConnection();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql1);
                while(rs.next()){
                priv = rs.getInt("total");
                }
                if(priv != 0){
                Statement statement1 = connect.createStatement();
                statement1.executeUpdate(sql);
                statement1.close();
                }
                statement.close();
                connect.close();
             } catch (Exception e) {
               e.printStackTrace();
            }   
            
            return priv;
        }
    
            public int deletedivision(int dvid){
            
            int priv = 0;
            String sql = "DELETE from Division WHERE DivisionID='"+dvid+"'";
            String sql1 = "SELECT Count(*) AS total from Division WHERE DivisionID='"+dvid+"'";
            String sql2 = "DELETE from TournamentBracket WHERE WinnerTeam1 = '"+dvid+"' OR WinnerTeam2 = '"+dvid+"'";
            String sql3 = "UPDATE Team SET DivisionID = NULL WHERE DivisionID = '"+dvid+"'";
            try {
                Connection connect = DBUtil.getConnection();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql1);
                while(rs.next()){
                priv = rs.getInt("total");
                }
                if(priv != 0){
                
                Statement statement3 = connect.createStatement();
                statement3.executeUpdate(sql3);    
                Statement statement1 = connect.createStatement();
                statement1.executeUpdate(sql2);
                Statement statement2 = connect.createStatement();
                statement2.executeUpdate(sql);
                statement3.close();
                statement2.close();
                statement1.close();
                }

                statement.close();
                connect.close();
             } catch (Exception e) {
               e.printStackTrace();
            }   
            
            return priv;
        }
            
            public int deletesport(int dvid){
            
            int priv = 0;
            int divisionid  = 0;
            String sql = "DELETE from Sport WHERE SportID='"+dvid+"'";
            String sql1 = "SELECT Count(*) AS total from Sport WHERE SportID='"+dvid+"'";
            String sql2 = "DELETE from Tournament WHERE SportID = '"+dvid+"'";
            String sql4 = "DELETE from TournamentBracket WHERE TournamentID = (SELECT TournamentID from Tournament Where SportID ='"+dvid+"')";
            String sql5 = "DELETE from Division WHERE SportID='"+dvid+"'";

            try {
                Connection connect = DBUtil.getConnection();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql1);
                while(rs.next()){
                priv = rs.getInt("total");
                }
                if(priv != 0){
                String sql6 = "SELECT DivisionID from Division Where SportID ='"+dvid+"'";    
                Statement statement4 = connect.createStatement();
                ResultSet rs1 = statement4.executeQuery(sql6);
                Statement statement5 = connect.createStatement();
                while(rs1.next()){
                divisionid = rs1.getInt("DivisionID");
                if(divisionid != 0){
                String sql3 = "UPDATE Team SET DivisionID = NULL WHERE DivisionID = '"+divisionid+"'";
                statement5.executeUpdate(sql3);

                    }
                }  
                statement5.close();
                statement4.close();
                Statement statement3 = connect.createStatement();
                statement3.executeUpdate(sql4);    
                Statement statement1 = connect.createStatement();
                statement1.executeUpdate(sql5);
                Statement statement2 = connect.createStatement();
                statement2.executeUpdate(sql2);
                Statement statement6 = connect.createStatement();
                statement6.executeUpdate(sql);                
                
                rs1.close();
                statement6.close();
                statement3.close();
                statement2.close();
                statement1.close();
                }
                rs.close();
                statement.close();
                connect.close();
             } catch (Exception e) {
               e.printStackTrace();
            }   
            
            return priv;
        }            
}
