package com.limoges.imageedit;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    private static Connection connectDb(){
        Connection con = null;
        String userName="";
        String password="";
        String hostURL="jdbc:derby://localhost:1527/imageedit";

        try {
            con = DriverManager.getConnection(hostURL,userName,password);
            return  con;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return null;
    }


}
