package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JTest {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/springdata";
        String username = "mike";
        String password = "pass";

        try(Connection conn = DriverManager.getConnection(url, username,password)){

            System.out.println("created " + conn);
            //String sql = "CREATE TABLE ACCOUNT ( id int, username varchar(255), password varchar(255));";
            String sql2 = "insert into ACCOUNT values(1, 'mike', 'pass')";

            try(PreparedStatement ps = conn.prepareStatement(sql2)){
                ps.execute();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }

}
