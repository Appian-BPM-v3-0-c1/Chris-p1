package com.revature.onlinestore.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    /*instantiating Connection object*/
    private static Connection con = null;

    /*Instantiating Properties object*/
    private static final Properties prop = new Properties();

    static {
        try {
            /*Importing JDBC-jar file */
            Class.forName("org.postgresql.Driver");

            /* using prop object to load url, username, password */
            prop.load(new FileReader("C:\\Users\\Chris\\Chris-p1\\OnlineStore\\src\\main\\resources\\db.properties"));

            /* getting this connection */
            con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));

            /*throw Exception if connection not successful */
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
        /*getter for connection*/
    public static Connection getCon() {
        return con;
    }
}
