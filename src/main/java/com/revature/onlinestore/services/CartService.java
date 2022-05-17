package com.revature.onlinestore.services;

import com.revature.onlinestore.daos.CartDAO;

public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) { this.cartDAO = cartDAO; }

    public CartDAO getCartDAO() { return cartDAO; }
}