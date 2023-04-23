package com.limoges.imageedit.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDatabase {

    private SetUpDatabase(){} //hide constructor cuz all the methods is static


    public static void setUp(Connection conn) throws SQLException{
        setUpUser(conn);
    }
    public static void setUpUser(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String query = "CREATE TABLE users( "
                    + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
                    + "username VARCHAR(255), "
                    + "password VARCHAR(255))";
            stmt.executeUpdate(query);
        }
    }
}
