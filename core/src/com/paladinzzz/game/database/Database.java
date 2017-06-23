package com.paladinzzz.game.database;

/**
 * Created by Selim on 21/6/2017.
 */


import java.sql.*;

public class Database{
    private final String url = "jdbc:postgresql://37.97.173.46:5432/diggydb";
    private final String user = "postgres";
    private final String password = "kaas123";
    private Connection conn;

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

}
