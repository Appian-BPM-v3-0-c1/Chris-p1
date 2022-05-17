package com.revature.onlinestore.ui;


import com.revature.onlinestore.daos.CartDAO;
import com.revature.onlinestore.daos.ItemDAO;
import com.revature.onlinestore.daos.TransactionDAO;
import com.revature.onlinestore.daos.UserDAO;
import com.revature.onlinestore.models.Cart;
import com.revature.onlinestore.models.User;
import com.revature.onlinestore.services.CartService;
import com.revature.onlinestore.services.ItemService;
import com.revature.onlinestore.services.TransactionService;
import com.revature.onlinestore.services.UserService;

import java.util.Scanner;

public class MainMenu implements IMenu {
    private Cart cart = null;
    private ItemService itemService = null;
    private final User user;

    Scanner scan = new Scanner(System.in);

    public MainMenu(User user) {
        this.user = user;
        this.itemService = itemService;
        this.cart = cart;

    }

    @Override
    public void start() {
        char input;

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to Legion Arms and Armour!");
                System.out.println("\n[1] Go to Item Menu");
                System.out.println("\n[2] View Transactions");
                System.out.println("\n[x] Log out");
                System.out.print("Enter: ");
                input = scan.next().charAt(0);

                switch (input) {
                    case '1':
                        new ItemMenu(new Cart(), new ItemService(new ItemDAO()), new UserService(new UserDAO()), user, new CartService(new CartDAO())).start();
                        break;
                    case '2':
                        new TransactionMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO())).start();
                        break;
                    case 'x':
                        new LoginMenu(new UserService(new UserDAO())).start();
                        break exit;

                    default:
                        System.out.println("\nInvalid input!");

                }
            }
        }
    }
}