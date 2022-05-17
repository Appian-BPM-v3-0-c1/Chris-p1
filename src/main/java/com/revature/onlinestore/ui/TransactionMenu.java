package com.revature.onlinestore.ui;

import com.revature.onlinestore.models.Item;
import com.revature.onlinestore.models.Transaction;
import com.revature.onlinestore.models.User;
import com.revature.onlinestore.services.CartService;
import com.revature.onlinestore.services.ItemService;
import com.revature.onlinestore.services.TransactionService;
import com.revature.onlinestore.services.UserService;

import java.util.List;
import java.util.Scanner;

public class TransactionMenu implements IMenu {
    Scanner scan = new Scanner(System.in);

    private final User user;
    private final UserService userService;
    private final ItemService itemService;
    private final CartService cartService;
    private final TransactionService transactionService;

    public TransactionMenu(User user, UserService userService, ItemService itemService, CartService cartService, TransactionService transactionService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
        this.transactionService = transactionService;
    }

    @Override
    public void start() {
        char input;
        exit:
        {
            System.out.println("\nPurchases");

            System.out.println("[1] Sort purchases by most recent");
            System.out.println("[2] Sort purchases by oldest");
            System.out.println("[x] Return to main menu");

            input = scan.next().charAt(0);

            switch (input) {
                case '1':
                    List<Transaction> orderListAsc = transactionService.getTransactionDAO().sortDateAsc(user.getId());
                    if (orderListAsc.size() == 0) {
                        System.out.println("\nNo purchases yet!");
                    } else {
                        for (int i = 0; i < orderListAsc.size(); i++) {
                            int itemId = orderListAsc.get(i).getItemId();
                            int usersId = orderListAsc.get(i).getUsersId();
                            System.out.print("[" + (i + 1) + "] ");
                            Item item = itemService.getItemDAO().findItemById(itemId);
                            System.out.print(((Item) item).transactionToString());

                        }
                    }
                    break exit;
                case '2':
                    List<Transaction> orderListDesc = transactionService.getTransactionDAO().sortDateDesc(user.getId());
                    if(orderListDesc.size() == 0) {
                        System.out.println("\nNo purchases yet!");
                        break exit;
                    } else {
                        for (int i = 0; i < orderListDesc.size(); i++) {
                            int itemId = orderListDesc.get(i).getItemId();
                            int usersId = orderListDesc.get(i).getUsersId();
                            System.out.print("[" + (i + 1) + "] ");
                            Item item = itemService.getItemDAO().findItemById(itemId);
                            System.out.print(item.transactionToString());

                        }
                    }
                    break exit;
                case 'x':
                    break exit;

                default:
                    System.out.println("\nInvalid input!");
            }
        }
    }
}