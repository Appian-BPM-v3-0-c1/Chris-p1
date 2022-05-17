package com.revature.onlinestore.ui;

import com.revature.onlinestore.models.Cart;
import com.revature.onlinestore.models.Item;
import com.revature.onlinestore.models.Transaction;
import com.revature.onlinestore.models.User;
import com.revature.onlinestore.services.CartService;
import com.revature.onlinestore.services.ItemService;
import com.revature.onlinestore.services.TransactionService;
import com.revature.onlinestore.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CartMenu implements IMenu {
    Scanner scan = new Scanner(System.in);
    DecimalFormat twoDForm = new DecimalFormat("#.00");
    char input;

    private final ItemService itemService;
    private final CartService cartService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final User user;

    public CartMenu(ItemService itemService, CartService cartService, TransactionService transactionService, UserService userService, User user) {
        this.itemService = itemService;
        this.cartService = cartService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.user = user;
    }

    @Override
    public void start() {
        boolean exit = false;
        double total = 0;
        exit:

        {
            while (true) {
                List<Item> itemList = itemService.getItemDAO().findAll();
                List<Cart> cartList = cartService.getCartDAO().viewCart(user.getId());
                if (cartList.size() > 0) {
                    for (int i = 0; i < cartList.size(); i++) {
                        System.out.print("\n[" + (i + 1) + "]" + itemService.getItemDAO().findAllById(cartList.get(i).getItemId()).toString().replace("[", "").replace("]", ""));

                    }
                } else {
                    System.out.println("\nNo Items in your cart");
                    break exit;
                }

                while (!exit) {
                    System.out.println("\n[1] Checkout");
                    System.out.println("\n[x] Go back");
                    input = scan.next().charAt(0);
                    switch (input) {
                        case '1':
                            for (int i = 0; i < cartList.size(); i++) {
                                Transaction transaction = new Transaction();
                                transaction.setItemId(cartList.get(i).getItemId());
                                transaction.setUsersId(user.getId());
                                transactionService.getTransactionDAO().save(transaction);

                            }

                            System.out.println("Thank you for your purchase!");
                            break exit;

                        case 'x':
                            break exit;
                        default:
                            System.out.println("\nInvalid input!");
                            break;
                    }
                }
            }

        }
    }

}