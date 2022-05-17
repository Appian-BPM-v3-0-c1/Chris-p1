package com.revature.onlinestore.ui;

import com.revature.onlinestore.daos.CartDAO;
import com.revature.onlinestore.daos.ItemDAO;
import com.revature.onlinestore.daos.TransactionDAO;
import com.revature.onlinestore.daos.UserDAO;
import com.revature.onlinestore.models.Cart;
import com.revature.onlinestore.models.Item;
import com.revature.onlinestore.models.User;
import com.revature.onlinestore.services.CartService;
import com.revature.onlinestore.services.ItemService;
import com.revature.onlinestore.services.TransactionService;
import com.revature.onlinestore.services.UserService;

import java.util.List;
import java.util.Scanner;

public class ItemMenu implements IMenu {
    private final Cart cart;
    private final User user;
    private final ItemService itemService;
    private final UserService userService;
    private final CartService cartService;

    Scanner scan = new Scanner(System.in);


    public ItemMenu(Cart cart, ItemService itemService, UserService userService, User user, CartService cartService) {
        this.cart = cart;
        this.itemService = itemService;
        this.userService = userService;
        this.user = user;
        this.cartService = cartService;

    }

    public ItemMenu(Cart cart, User user, ItemService itemService, UserService userService, CartService cartService) {
        this.cart = cart;
        this.user = user;
        this.itemService = itemService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public void start() {
        Item item;
        char input;
        exit:
        {
            while (true) {
                System.out.println("\n Welcome to Legion Arms and Armour!");
                System.out.println("\n[1] View all items");
                System.out.println("\n[2] View my cart");
                System.out.println("\n[x] Go back");
                System.out.print("Enter: ");

                input = scan.next().charAt(0);
                scan.nextLine();

                switch (input) {
                    case '1':
                        System.out.println("\nPlease view our excellent collection of ancient and Medieval armour and weapons!");
                        viewAllItems();
                        break;
                    case '2':
                        new CartMenu(new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO()), new UserService(new UserDAO()), user).start();
                        break;
                    case 'x':
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewAllItems() {
        Item item = new Item();
        boolean exit = false;
        char input;
        List<Item> itemList = itemService.getItemDAO().findAllInStock(user.getId());
        exit:{
            if(itemList.size() == 0){
                System.out.println("Nothing ");
                break exit;
            } else {
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i).getStock() == 0) {
                        i++;
                    }
                    System.out.println("[" + (i + 1) + "] " + itemList.get(i).getName());
                }

            }
            while (true) {
                System.out.println("\n[1] Add to cart");
                System.out.println("\n[x] Go back");
                input = scan.next().charAt(0);

                switch (input) {
                    case '1':
                        addToCart(item);
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

    private void addToCart(Item item) {
        int count = 0;
        List<Cart> cartList = cartService.getCartDAO().viewCart(user.getId());
        for (Cart value : cartList) {
            if (value.getItemId() == item.getId()) {
                count++;
            }
        }
        if (count == 0) {
            Cart cart = new Cart();
            cart.setUsersId(user.getId());
            cart.setItemId(item.getId());
            System.out.println("\nSaved to cart");
            cartService.getCartDAO().save(cart);

        }
    }
}