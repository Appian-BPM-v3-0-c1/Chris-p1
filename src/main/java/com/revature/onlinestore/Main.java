package com.revature.onlinestore;

import com.revature.onlinestore.connection.DatabaseConnection;
import com.revature.onlinestore.daos.UserDAO;
import com.revature.onlinestore.services.UserService;
import com.revature.onlinestore.ui.LoginMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        new LoginMenu(new UserService(new UserDAO())).start();

        Connection con = DatabaseConnection.getCon();

        System.out.println(con);
    }

}

