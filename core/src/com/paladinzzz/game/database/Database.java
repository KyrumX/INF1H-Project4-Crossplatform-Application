package com.paladinzzz.game.database;

/**
 * Created by Selim on 21/6/2017.
 */


import java.sql.*;

public class Database{
    private final String url = "jdbc:postgresql://37.97.173.46:5432/diggydb";
    private final String user = "postgres";
    private final String password = "kaas123";
//    private String names;
    private Connection conn;
    String[] playernames = new String[100];
    String[] scores = new String[100];

    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        this.conn = conn;
        return conn;
    }

    public void getData(Connection conn){
        try {
            Statement get = conn.createStatement();
            String query = "Select * from player";
            ResultSet rs = get.executeQuery(query);

            while (rs.next()){
                String firstName = rs.getString("name");
                System.out.println(firstName);
                String score = rs.getString("score");
                System.out.println(score);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public String[] getNames(Connection conn) {
        try {
            Statement get = conn.createStatement();
            String query = "Select * from player ORDER BY score DESC";
            ResultSet rs = get.executeQuery(query);

            int counter = 0;
            while (rs.next()){
                if (rs.getString("name") != null) {
                    playernames[counter] = rs.getString("name");
                    counter++;
                }
            }

            for (String s : playernames){
                if (s != null) {
                    System.out.println(s);
                } else {
                    break;
                }
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return playernames;
    }

    public String[] getScores(Connection conn) {
        try {
            Statement get = conn.createStatement();
            String query = "Select * from player ORDER BY score DESC";
            ResultSet rs = get.executeQuery(query);

            int counter = 0;
            while (rs.next()){
                if (rs.getString("name") != null) {
                    scores[counter] = rs.getString("score");
                    counter++;
                }
            }


        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return scores;
    }

}
