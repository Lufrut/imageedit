package com.limoges.imageedit.models;

import java.sql.*;

public class DataBase {

    /*private Connection connection;
    private ResultSet resultSet;
    private Statement statement; */

    public static Connection connectDb(){
        Connection con = null;
        /*
       String userName="";
       String password="";
         */
        String hostURL="jdbc:derby://localhost:1527/imageedit;create=true";

        try {
            // if you need auth with login and pass it's here
            //con = DriverManager.getConnection(hostURL,userName,password);
            con = DriverManager.getConnection(hostURL);
            return  con;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return null;
    }


}
